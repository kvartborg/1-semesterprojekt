package maga.environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashMap;
import maga.item.Item;
import maga.item.Key;
import org.w3c.dom.Element;
import org.w3c.dom.Document;

public class Room{

    /**
     * The rooms name
     */
    private String name;

    /**
     * Description of the room
     */
    private final String description;

    /**
     * The HashMap is a list of existing rooms
     */
    private final HashMap<String, Room> exits;

    /**
     * The array keeps the items
     */
    private ArrayList<Item> items = new ArrayList<Item>();

    /**
     * Boolean for if the room is locked or not
     */
    private Boolean locked = false;

    /**
     * Attribute for the collum index related to a room.
     */
    private int x;

    /**
     * Attribute for the row index related to a room.
     */
    private int y;

    /**
     * A constructor for new rooms
     * @param name is the name of the new room
     * @param description is the description of the new room
     * @param x
     * @param y
     */
    public Room(String name, String description, int x, int y) {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
        this.x = x;
        this.y = y;
    }

    /**
     * Accessor method for x.
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Accessor method for y.
     * @return y
     */
    public int getY() {
        return y;
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
     * @return String
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
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
     * Accesoor method for the name of the room
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method for the items in the room
     * @return ArrayList
     */
    public ArrayList<Item> getItems() {
        return this.items;
    }

    /**
     * This method adds items to our array and turns the array into an
     * arraylist.
     * @param items
     */
    public void addItems(Item[] items) {
        this.items = new ArrayList<>(Arrays.asList(items));
    }

    /**
     * This method adds items (used for dummy items).
     * @param items
     */
    public void addItem(Item items) {
        this.items.add(items);
    }

    /**
     * Checks if a room is locked
     * @return boolean
     */
    public boolean isLocked() {
        return this.locked;
    }

    /**
     * Locks the room
     */
    public void lock() {
        this.locked = true;
    }

    /**
     * Unlocks the door if the key fits, and the door is locked
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
     * This method unlocks room
     */
    public void unlock() {
        this.locked = false;
    }

    /**
     * Gets the name of the items in the room
     * @return ArrayList of the itemnames as a string
     */
    public ArrayList<String> getNameOfItems(){
        ArrayList<String> list = new ArrayList<>();
        for (Item item : items) {
            list.add(item.getName());
        }
        return list;
    }

    /**
     * This method empties the room
     */
    public void empty() {
        this.items = new ArrayList<Item>();
    }

    /**
     * Serialize the room object to xml
     * @param  doc
     * @return Element
     */
    public Element serialize(Document doc) {
        Element room = doc.createElement("room");
        room.setAttribute("name", this.getName());
        room.setAttribute("locked", Boolean.toString(this.isLocked()));

        Element items = doc.createElement("items");
        room.appendChild(items);
        for (Item item : this.getItems()) {
            Element i = doc.createElement("item");
            i.setAttribute("name", item.getName());
            items.appendChild(i);
        }

        return room;
    }
}
