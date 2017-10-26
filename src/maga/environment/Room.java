package maga.environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashMap;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import maga.item.Item;
import maga.item.Key;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room extends Pane {

    private int x = 0;
    private int y = 0;

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
    private ArrayList<Item> items = new ArrayList<Item>();

    /**
     * Boolean for if the room is locked or not
     */
    private Boolean locked = false;

    /**
     * A constructor for new rooms
     * @param name is the name of the new room
     * @param description is the description of the new room
     */
    public Room(String name, String description, int x, int y) {
        super();
        super.setPrefSize(200, 200);
        GridPane.setConstraints(this, x, y);
        this.name = name;
        this.x = x;
        this.y = y;
        this.description = description;
        this.exits = new HashMap<>();
        this.renderBorder();
        this.renderLabel();
    }

    private void renderLabel () {
        Text text = new Text(this.name);
        text.setX(10);
        text.setY(20);
        this.getChildren().add(text);
    }

    private void renderBorder () {
        Rectangle rect = new Rectangle(0, 0, 200, 200);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);
        this.getChildren().add(rect);
    }

    private void renderDoors (String direction) {
        HashMap<String, int[]> doors = new HashMap<>();
        doors.put("north", new int[] { 85, -5, 30, 5 });
        doors.put("east", new int[] { 200, 85, 5, 30 });
        doors.put("south", new int[] { 85, 200, 30, 5 });
        doors.put("west", new int[] { -5, 85, 5, 30 });

        int[] door = doors.get(direction);

        Rectangle rect = new Rectangle(door[0], door[1], door[2], door[3]);

        rect.setFill(Color.BLACK);
        this.getChildren().add(rect);
    }

    /**
     * The method sets the exits for the current room
     * @param direction is the direction of the other rooms
     * @param neighbor is the name of the other rooms
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
        this.renderDoors(direction);
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

    public String getName() {
        return this.name;
    }

    /**
     * This method is a getter method for the items
     * @return returns the item
     */
    public ArrayList<Item> getItems() {
       return this.items;
    }

    /**
     * This method adds items to our array and turns the array into
     * an arraylist.
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
        if (items.isEmpty()) {
            System.out.println("The room is empty");
        }
        else{
            System.out.println("The room contains: ");
            for(Item item : items){
                System.out.println(item.getName());
            }
        }
    }

    public int getCenterX() {
        return ((this.x + 1) * 210) - 105 + 10;
    }

    public int getCenterY() {
        return ((this.y + 1) * 210) - 105 + 10;
    }
}
