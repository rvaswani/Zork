package com.bayviewglen.ZorkGame;
/*
 * Class Room - a room in an adventure game.
 *
 * Author:  Michael Kolling
 * Version: 1.1
 * Date:    August 2000
 * 
 * This class is part of Zork. Zork is a simple, text based adventure game.
 *
 * "Room" represents one location in the scenery of the game.  It is 
 * connected to at most four other rooms via exits.  The exits are labelled
 * north, east, south, west.  For each direction, the room stores a reference
 * to the neighbouring room, or null if there is no exit in that direction.
 */

import java.io.Serializable;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

class Room  implements Serializable
{
	private String roomName;
    private String description;
    private int monsterCount;					// Stores the amount of monsters in this room
    private HashMap<String, Room> exits;        // stores exits of this room.

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open court yard".
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public Room() {
		// default constructor.
    	roomName = "DEFAULT ROOM";
    	description = "DEFAULT DESCRIPTION";
    	monsterCount = 0;
    	exits = new HashMap<String, Room>();
	}

    public void setExit(char direction, Room r) throws Exception{
    	String dir= "";
    	switch (direction){
    	case 'E': dir = "East";break;
    	case 'W': dir = "West";break;
    	case 'S': dir = "South";break;
    	case 'N': dir = "North";break;
    	case 'U': dir = "Up";break;
    	case 'D': dir = "Down";break;
    	case 'O': dir = "Open";break;
    	case 'L': dir = "Look";break;
    	case 'B': dir = "Back";break;
    	case 'R': dir = "Read";break;
    	case 'F': dir = "Fight";break;
    	default: throw new Exception("Invalid Direction");
    	
    	}
    	
    	exits.put(dir, r);
    }
    
	/**
     * Define the exits of this room.  Every direction either leads to
     * another room or is null (no exit there).
     */
    public void setExits(Room north, Room east, Room south, Room west, Room up, Room down, Room beginning, Room open, Room look, Room read, Room fight, Room back) 
    {
        if(north != null)
            exits.put("North", north);
        if(east != null)
            exits.put("East", east);
        if(south != null)
            exits.put("South", south);
        if(west != null)
            exits.put("West", west);
        if(up != null)
            exits.put("Up", up);
        if(up != null)
            exits.put("Down", down);
        if(open != null)
        	exits.put("Open", open);
        if(look != null)
        	exits.put("Look", look);
        if(beginning != null)
        	exits.put("Break", beginning);
        if(read != null)
        	exits.put("Read", read);
        if(fight != null)
        	exits.put("Fight", fight);
        if(back != null)
        	exits.put("Back", back);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String shortDescription()
    {
    	return "Room: " + roomName +"\n\n" + description;
    }

    /**
     * Return a long description of this room, on the form:
     *     You are in the kitchen.
     *     Exits: north west
     */
    public String longDescription()
    {
    	System.out.println("____________________________________________________________________________________________________________________");
        return "\nFloor: " + roomName.split("\\.")[0].replaceAll("Room ", "") + "\n" + "# of Monsters in this room: " + monsterCount + "\n" + exitString() + "\n\n" + description + "\n";

    }
 
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west ".
     */
    private String exitString()
    {
        String returnString = "Exits:";
		Set keys = exits.keySet();
        for(Iterator iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + iter.next();
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room nextRoom(String direction) 
    {
    	// Allow one word command and lower case command
    	if (direction.substring(0,1).equalsIgnoreCase("N"))
    		direction = "North";
    	if (direction.substring(0,1).equalsIgnoreCase("S"))
    		direction = "South";
    	if (direction.substring(0,1).equalsIgnoreCase("E"))
    		direction = "East";
    	if (direction.substring(0,1).equalsIgnoreCase("W"))
    		direction = "West";
    	if (direction.substring(0,1).equalsIgnoreCase("U"))
    		direction = "Up";
    	if (direction.substring(0,1).equalsIgnoreCase("B"))
    		direction = "Back";
    	if (direction.substring(0,2).equalsIgnoreCase("Beg"))
    		direction = "Beginning";
    	
        return (Room)exits.get(direction);
    }

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getMonsterCount() {
		return monsterCount;
	}
	
	public void setMonsterCount(int monsterCount) {
		this.monsterCount = monsterCount;
	}
}