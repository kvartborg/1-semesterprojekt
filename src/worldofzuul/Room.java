package worldofzuul;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room {

    /**
     * The String "description" is used when describing the room generated with the constructor
     */
    private String description;
    /**
     * The HashMap is a list of existing rooms
     */
    private HashMap<String, Room> exits;

    /**
     * A constructor for new rooms
     * @param description is the description of the new room
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
    }
    
    /**
     * The method sets the exits for the current room
     * @param direction is the direction of the other rooms
     * @param neighbor is the name of the other rooms
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }
    
    /**
     * The method gets the short description of the room
     * @return description (only the name returns)
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * The method gets the long description of the room
     * @return "You are ", the description and the exits of the current room
     */
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }
        /**
         * Gets the exit for the current room
         * @return the exits from the room 
         */
	private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * The method gets the room in the direction described
     * @param direction is the decider of what exit is used
     * @return the room in the decided direction
     */    
    public Room getExit(String direction) {
        return exits.get(direction);
    }
}
