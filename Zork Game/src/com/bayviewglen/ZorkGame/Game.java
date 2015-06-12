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

class Game implements Serializable
{
    private Parser parser;
    private Room currentRoom;
    private Room lastRoom;
    private Room beginningRoom;
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
     */
    public void play() 
    {            
        printWelcome();
        
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to League of Zork!");
        System.out.println("This is the new and best RPG in the world.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.longDescription());
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
        if (commandWord.equals("help")) {
        	printHelp();
        	System.out.println(currentRoom.longDescription());
        }
        else if (commandWord.equals("save")) {
			try {
				save();
				System.out.println(currentRoom.longDescription());
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        else if (commandWord.equals("load")) {
        	Game loadedGame;
			try {
				loadedGame = load();
				loadedGame.play();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
        }
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
        {
            if(command.hasSecondWord())
                System.out.println("Quit what?");
            else
                return true;  // signal that we want to quit
        }
        return false;
    }

    private void printHelp() {
		System.out.println("\nYou have several difference commands you can use to complete game:");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("1. Go + \n    a. North  \n    b. South  \n    c. East  \n    d. West  \n    e. Up  \n    f. Down  \n    g. Open  \n    h. Look  \n    i. Back  \n    j. Beginning  \n    k. Read  \n2. Quit  \n3. Save  \n4. Load\n");
		System.out.println("Find your way to the final level and defeat the final boss. Good luck.");
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
            if (currentRoom.getMonsterCount() > 1) {
            	System.out.println("You have to fight to leave this room!");
            	return;
            }

        	Room nextRoom = currentRoom.nextRoom(direction);
        	
          	if (nextRoom == null)
        		System.out.println("There is nothing here! Search somewhere else.");
        	else {
        		lastRoom = currentRoom; 
        		currentRoom = nextRoom;
        		if (direction.equalsIgnoreCase("Up") || currentRoom.getRoomName().split("\\.")[1].substring(0).equals("1")) 
        			beginningRoom = nextRoom; 
        			currentRoom = nextRoom;        			       		
        		System.out.println(currentRoom.longDescription());
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