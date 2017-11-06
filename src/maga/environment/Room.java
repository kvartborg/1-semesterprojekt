package maga.environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashMap;
import maga.item.Item;
import maga.item.Key;
import maga.inventory.Inventory;


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
     * The String "description" is used when
     * describing the room generated with the constructor
     */
    private final String description;
    /**
     * The HashMap is a list of existing rooms
     */
    private final HashMap<String, Room> exits;

    /**
     * The array keeps the items
     */
    private Inventory inventory = new Inventory();

    /**
     * Boolean for if the room is locked or not
     */
    private Boolean locked = false;

    /**
     * A constructor for new rooms
     * @param name is the name of the new room
     * @param description is the description of the new room
     */
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
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
     * Getter for the inventory instance
     * @return inventory instance
     */
    public Inventory getInventory () {
        return this.inventory;
    }

    /**
     * Getter for the name atrribute
     * @return name of the room
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method adds items to our array and turns the array into
     * an arraylist.
     * @param items
     */
    public void addItems(Item[] items) {
       for (Item item : items) {
           this.inventory.add(item);
       }
    }

    /**
     * This method adds items (used for dummy items).
     * @param item
     */
    public void addItem(Item item) {
        this.inventory.add(item);
    }

    /**
     * Check if a room is locked
     * @return boolean
     */
    public boolean isLocked () {
        return this.locked;
    }

    /**
     * Lock room
     * @return
     */
    public void lock() {
        this.locked = true;
    }

    /**
     * This method checks if the room is locked or not
     * @param key
     * @return returns if the key fits to the room
     */
    public boolean unlock(Key key) {
        if (key.getRoom() == this) {
            this.locked = false;
            return true;
        }

        return false;
    }

    /**
     * Prints the items in the room
     */
    public void printItems(){
        if (this.inventory.isEmpty()) {
            System.out.println("The room is empty");
        }
        else{
            System.out.println("The room contains: ");
            for(Item item : this.inventory){
                System.out.println(item.getName());
            }
        }
    }
}
