package worldofzuul.environment;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import worldofzuul.item.Item;
import worldofzuul.item.Key;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room {

    /**
     * The rooms name
     */
    private String name;

    /**
     * The String "description" is used when describing the room generated with the constructor
     */
    private String description;
    /**
     * The HashMap is a list of existing rooms
     */
    private HashMap<String, Room> exits;

    /**
     * The array keeps the items
     */
    private Item[] items;

    /**
     * Boolean for if the room is locked or not
     */
    private Boolean locked = false;

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

    /**
     * This method is a getter method for the items
     * @return returns the item
     */
    public Item[] getItems() {
       return this.items;
    }

    /**
     * This method adds items to our array
     * @param items
     * @return returns an item into our array
     */
    public void addItems(Item[] items) {
       this.items = items;
    }

    /**
     * This method checks if the room is locked or not
     * @param key
     * @return returns if the key fits to the room
     */
    public boolean unlock(Key key) {
        return (key.getRoom() == this);
    }
}
