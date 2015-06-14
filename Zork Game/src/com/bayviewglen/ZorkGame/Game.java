package com.bayviewglen.ZorkGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.io.Serializable;

/**
 * Class Game - the main class of the "Zork" game.
 *
 * Author:  Michael Kolling
 * Version: 1.1
 * Date:    March 2000
 * 
 *  This class is the main class of the "Zork" application. Zork is a very
 *  simple, text based adventure game.  Users can walk around some scenery.
 *  That's all. It should really be extended to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  routine.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates the
 *  commands that the parser returns.
 */

@SuppressWarnings("serial")
class Game implements Serializable
{
    private Parser parser;
    private Room currentRoom;
    private Room lastRoom;
    private Room beginningRoom;
    private Player player;
    // This is a MASTER object that contains all of the rooms and is easily accessible.
    // The key will be the name of the room -> no spaces (Use all caps and underscore -> Great Room would have a key of GREAT_ROOM
    // In a hashmap keys are case sensitive.
    // masterRoomMap.get("GREAT_ROOM") will return the Room Object that is the Great Room (assuming you have one).
    private HashMap<String, Room> masterRoomMap;
    
    private void initRooms(String fileName) throws Exception{
    	masterRoomMap = new HashMap<String, Room>();
    	Scanner roomScanner;
		try {
			HashMap<String, HashMap<String, String>> exits = new HashMap<String, HashMap<String, String>>();    
			roomScanner = new Scanner(new File(fileName));
			while(roomScanner.hasNext()){
				Room room = new Room();
				// Read the Name
				String roomName = roomScanner.nextLine();
				room.setRoomName(roomName.split(":")[1].trim());
				room.setFloor(Integer.parseInt(roomName.substring(roomName.indexOf(".") - 2, roomName.indexOf(".")).trim()));
				if (Integer.parseInt(roomName.substring(roomName.indexOf(".") + 1)) == 999) {
					room.setKey(true);
					room.setMonsterIndicator(room.getFloor() * room.getFloor());	// Final boss exponentially stronger
				}else{
					room.setMonsterIndicator(room.getFloor());
				}
				// Read items in this room
				String items = roomScanner.nextLine();
				if (!items.equals("")) {
					Item award = new Item(items);
					room.setAward(award);
				}
				// Read the Description
				String roomDescription = roomScanner.nextLine();
				room.setDescription(roomDescription.split(":")[1].replaceAll("<br>", "\n").trim());
				// Read the amount of Monsters in this room
				int monsterCount = Integer.parseInt(roomScanner.nextLine().split(":")[1].trim());
				room.setMonsterCount(monsterCount);
				// Read the Exits
				String roomExits = roomScanner.nextLine();
				// An array of strings in the format E-RoomName
				String[] rooms = roomExits.split(":")[1].split(",");
				HashMap<String, String> temp = new HashMap<String, String>(); 
				for (String s : rooms){
					temp.put(s.split("-")[0].trim(), s.split("-")[1]);
				}
				
				exits.put(roomName.substring(10).trim().toUpperCase().replaceAll(" ",  "_"), temp);
				
				// This puts the room we created (Without the exits in the masterMap)
				masterRoomMap.put(roomName.toUpperCase().substring(10).trim().replaceAll(" ",  "_"), room);
				
				
				
				// Now we better set the exits.
			}	
			
			for (String key : masterRoomMap.keySet()){
				Room roomTemp = masterRoomMap.get(key);
				HashMap<String, String> tempExits = exits.get(key);
				for (String s : tempExits.keySet()){
					// s = direction
					// value is the room.
					
					String roomName2 = tempExits.get(s.trim());
					Room exitRoom = masterRoomMap.get(roomName2.toUpperCase().replaceAll(" ", "_"));
					roomTemp.setExit(s.trim().charAt(0), exitRoom);
					
				}
				
				
			}
    	
			roomScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }    

    /**
     * Create the game and initialize its internal map.
     */
    public Game() {
        try {
			initRooms("Room Data/rooms.dat");
			currentRoom = masterRoomMap.get("ROOM_1.1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        parser = new Parser();
    }

    

    /**
     *  Main play routine.  Loops until end of play.
     * @throws InterruptedException 
     */
    public void play() throws InterruptedException 
    {            
    	Scanner scanner = new Scanner(System.in);
        printWelcome();
        
        // New player
        player = new Player(scanner);
        player.printOptions(scanner);
        player.setCharacter();
        
        System.out.println(currentRoom.longDescription());
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
             
        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    
    /**
     * Print out the opening message for the player.
     * @throws InterruptedException 
     */
    private void printWelcome() throws InterruptedException
    {
        System.out.println();
        System.out.println("Welcome to Zork 2K15!");
        Thread.sleep(1500);
        System.out.println("This is the new and best text-based RPG in the world!");
        Thread.sleep(1500);
        System.out.println("Brought to you by: Coding Master Daniel Yan & CrossFire Master Rishi Vaswani!");
        Thread.sleep(1500);
        System.out.println("Type 'help' if you need help.");
        Thread.sleep(1500);
        System.out.println("Have fun!!!");
        Thread.sleep(1500);
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        if(command.isUnknown())
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        
        // Cheat code
        if (commandWord.equals("GODMODE")) {
        	for (int i = 0; i < 100000; i++) {
        		player.levelUp();        		
        	}
        	return false;
        }
        
        // Command to start a battle with monster in room
        if (commandWord.equalsIgnoreCase("fight")) {
        	if (currentRoom.getMonsterCount() == 0) {
        		System.out.println("There is no monster to fight!");
        		return false;
        	}
        	Monster monster = new Monster(currentRoom.getMonsterIndicator());
        	Battle battle = new Battle(player, monster);
        	battle.fight();
        	if (battle.getResult() == 1) {
        		currentRoom.setMonsterCount(currentRoom.getMonsterCount() - 1);
        		if (currentRoom.getAward() != null) {
        			int x = player.addItem(currentRoom.getAward(), 1);
        			System.out.println("You got " + currentRoom.getAward().getName() + "!");
        			System.out.println("");
        			System.out.println(currentRoom.getAward().getDescription());
        			System.out.println("");
        			if (x == 1) {
        				currentRoom.setAward(null);
        			}
        		}
        		System.out.println(currentRoom.longDescription());
        	} else if (battle.getResult() == 2) {
        		System.out.println(currentRoom.longDescription());
        	} else {
        		currentRoom = beginningRoom;
        		System.out.println(beginningRoom.longDescription());
        	}
        }
        
        if (commandWord.equalsIgnoreCase("help")) {
        	printHelp();
        	System.out.println(currentRoom.longDescription());
        }
        else if (commandWord.equalsIgnoreCase("save")) {
			try {
				save();
				System.out.println(currentRoom.longDescription());
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        else if (commandWord.equalsIgnoreCase("load")) {
        	Game loadedGame;
			try {
				loadedGame = load();
				loadedGame.play();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
        }
        else if (commandWord.equalsIgnoreCase("Pick"))
        {
        	if (currentRoom.getMonsterCount() != 0) {
        		System.out.println("You have beat the monster to pick up anything!");
        	}else{
        		player.addItem(currentRoom.getAward(), 1);
        		System.out.println("You picked up " + currentRoom.getAward().getName() + "!");
        	}
        }
        else if (commandWord.equalsIgnoreCase("go"))
            goRoom(command);
        else if (commandWord.equalsIgnoreCase("Inventory"))
        {
        	if (player.getInventory().isEmpty()) {
            	System.out.println("You don't have anything in your inventory!");
            }else{
            	System.out.println("You have: ");
            	for (String str : player.getInventory().keySet()) {
            		System.out.println(player.getInventory().get(str).getName() + ":");
            		player.getInventory().get(str).printAll();
            	}
            }
        }
        else if (commandWord.equalsIgnoreCase("use"))
        {
        	System.out.println("Use what? How many? (item name, amount)");
        	Scanner input = new Scanner(System.in);
        	try {
        		String nextLine = input.nextLine();
        		String name = nextLine.substring(0, nextLine.indexOf(","));
        		int amount = Integer.parseInt(nextLine.substring(nextLine.indexOf(",") + 1).trim());
        		player.useItem(player.getInventory().get(name), amount);
        	}catch (ArrayIndexOutOfBoundsException ex) {
        		System.out.println("Please enter in proper form (name, amount)");
        	}
        }
        else if (commandWord.equalsIgnoreCase("throw"))
        {
        	System.out.println("Throw what? How many? (item name, amount)");
        	Scanner input = new Scanner(System.in);
        	try {
        		String nextLine = input.nextLine();
        		String name = nextLine.substring(0, nextLine.indexOf(","));
        		int amount = Integer.parseInt(nextLine.substring(nextLine.indexOf(",") + 1).trim());
        		player.throwAwayItem(player.getInventory().get(name), amount);
        	}catch (ArrayIndexOutOfBoundsException ex) {
        		System.out.println("Please enter in proper form (name, amount)");
        	}
        }
        else if (commandWord.equalsIgnoreCase("quit"))
        {
            if(command.hasSecondWord())
                System.out.println("Quit what?");
            else
                return true;  // signal that we want to quit
        }
        else if (commandWord.equalsIgnoreCase("Stats")) {
        	player.checkStats();
        }  
        
        if (currentRoom.getDescription().equals("GAME END")) {
        	System.out.println("Congratulations on completing the game! If you liked it, awesome! You can try a different character! Also, you can get a certificate of completion! Just email zyan@bayviewglen.ca!");
        	System.out.println("Type \"quit\" to quit");
        	return true;
        }
        return false;
       
    }

	private void printHelp() {
    		System.out.println("\nYou have several difference commands you can use to complete game:");
    		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    		System.out.println("1. Go + \n    a. North  \n    b. South  \n    c. East  \n    d. West  \n    e. Up  \n    f. Down  \n    g. Open  \n    h. Look  \n    i. Back  \n    j. Beginning  \n    k. Read\n2. Fight  \n3. Stats  \n4. Pick up  \n5. Inventory + \n    a. Use  \n    b. Throw\n6. Save  \n7. Load  \n8. Quit\n");
    		System.out.println("\nYou are in the headquarters of Evil Monster King. Each floor gets progressively harder with monsters wanting to kill you.");
   			System.out.println("You must fight your way through and collect keys in order to get to the boss round on each floor. Along the way, you will find items ");
   			System.out.println("such as potions, which will be stored in your limited inventory, and if you ever get lost, don't worry, just type \"go back\" to go back ");
   			System.out.println("to your previous room or \" go beginning\" to go to the begining of the floor. ");
    		System.out.println("Now... Find your way to the final level and defeat the final boss. Good luck, and may the odds be ever in your favor.");
    		System.out.println();
		
	}

	// implementations of user commands:

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        
        String direction = command.getSecondWord();

        // Try to leave current room.
        if ((direction.equalsIgnoreCase("back") || direction.equalsIgnoreCase("B")) && lastRoom != null && !currentRoom.getRoomName().split("\\.")[1].substring(0).equals("1")){
        	Room temp = currentRoom;
        	currentRoom = lastRoom;
        	lastRoom = temp;
        	System.out.println(currentRoom.longDescription());
        } else if ((direction.equalsIgnoreCase("beginning") || direction.equalsIgnoreCase("Beg")) && lastRoom != null) {
        	Room newTemp = beginningRoom;
        	currentRoom = beginningRoom;
        	beginningRoom = newTemp;
        	System.out.println(currentRoom.longDescription());
        }else{
            if (currentRoom.getMonsterCount() >= 1) {
            	System.out.println("You have to fight to leave this room!");
            	return;
            }

        	Room nextRoom = currentRoom.nextRoom(direction);
        	
          	if (nextRoom == null)
        		System.out.println("There is nothing here! Search somewhere else.");
        	else {
        		if (nextRoom.getKey()) {
        			Item key = new Item("Key", "Key", 1);
        			if (player.getInventory().containsValue(key)) {
        				player.getInventory().remove(key);
        				System.out.println("You unlocked the door with your key!");
        			} else {
        				System.out.println("You do not have a key to unlock this door!");
        			}
        		} else {
        			lastRoom = currentRoom; 
        			currentRoom = nextRoom;
        			if (direction.equalsIgnoreCase("Up") || currentRoom.getRoomName().split("\\.")[1].substring(0).equals("1")) 
        				beginningRoom = nextRoom; 
        			currentRoom = nextRoom;   
        			if (currentRoom.getRoomName().equals("Room 1.2"))
        				beginningRoom = lastRoom;
        			System.out.println(currentRoom.longDescription());
        		}
        	}
        }
    }
    
    
    public void save () throws Exception {
    	
    	// Write to disk with FileOutputStream
    	FileOutputStream f_out = new
    		FileOutputStream("SavedFile.data");
    	
    	// Write object with ObjectOutputStream
    	ObjectOutputStream obj_out = new
    		ObjectOutputStream (f_out);
    	
    	// Write object out to disk	
    	obj_out.writeObject (this);
    	obj_out.close();
    		
    }
    
    public Game load() throws Exception {
        // Read from disk using FileInputStream
        
        try {
        Game game;
        FileInputStream f_in = new FileInputStream("SavedFile.data");

        // Read object using ObjectInputStream
        ObjectInputStream obj_in = new ObjectInputStream(f_in);

        // Read an object
        game = (Game) obj_in.readObject();
        obj_in.close();
        return game;
        } catch (Exception ex) {
               System.out.println("Cannot load...");
               return null;
        }
 }
 
}