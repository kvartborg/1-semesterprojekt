
package maga.character;

import java.util.ArrayList;
import maga.command.Command;
import maga.environment.Environment;
import maga.item.Item;
import maga.util.GameState;
import acq.ISerializable;
import acq.ILoadable;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class Player extends Character implements ISerializable, ILoadable {

    /**
     * The arraylist stores the items, that the player picks up.
     */
    private ArrayList<Item> items = new ArrayList<Item>();

    /**
     * Creating an attribute that sets the maximum items a player can carry.
     */
    private final int MAX_ITEMS = 2;

    /**
     * Creating an attribute to check if the player has tweeted.
     */
    private boolean tweeted = false;

    /**
     * A constructor for the player class
     */
    public Player() {
        super("Fake news Reporter");
    }

    /**
     * The method adds items to the arraylist.
     *
     * @param command
     * @return returns either false or true if the item is added.
     */
    @Override
    public boolean pickupItems(Command command) {
        if (isInventoryFull()) {
            return false;
        }

        if (!command.hasSecondWord()) {
            return false;
        }
        for (Item item : getCurrentRoom().getItems()) {
            if (item.getName().equalsIgnoreCase(command.getSecondWord())) {
                if (!item.isMoveable()) {
                    return false;
                }
                getCurrentRoom().getItems().remove(item);
                return items.add(item);

            }
        }
        return false;
    }

    /**
     * This method drops items from the arraylist.
     *
     * @param command
     * @return returns either false or true if the item is dropped.
     */
    public boolean dropItems(Command command) {
        if (!command.hasSecondWord()) {
            return false;
        }
        for (Item item : getItems()) {
            if (item.getName().equalsIgnoreCase(command.getSecondWord())) {
                getCurrentRoom().getItems().add(item);
                return items.remove(item);
            }
        }
        return false;
    }

    /**
     * The method displays the items currently held.
     *
     * @return
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * The method checks if the inventory is full.
     *
     * @return returns a "true" or "false" value depending on how many items
     * held.
     */
    public boolean isInventoryFull() {
        return (this.items.size() >= MAX_ITEMS);
    }

    /**
     * Creates a "use" method that requires a second word, and looks for the
     * item to be used in the inventory.
     *
     * @param command
     */
    public void useItem(Command command) {
        if (!command.hasSecondWord()) {
            return;
        }
        String itemName = command.getSecondWord();

        for (Item item : this.items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                item.use(this);
                break;
            }
        }
        for (Item item : this.getCurrentRoom().getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                item.use(this);
                break;
            }
        }
    }

    /**
     * Returns the state of tweeted.
     *
     * @return true of false.
     */
    public boolean hasTweeted() {
        return this.tweeted;
    }

    /**
     * When used changes the state of tweeted to true.
     */
    public void tweeted() {
        this.tweeted = true;
    }

    /**
     * This method checks if the player has the item.
     *
     * @param name
     * @return
     */
    public boolean hasItem(String name) {
        return getItem(name) != null;
    }

    /**
     * This method is a getter method for our items.
     *
     * @param name
     * @return return item or null
     */
    public Item getItem(String name) {
        for (Item item : getItems()) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * This method adds a single item at a time
     *
     * @param item
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * This method empties the players inventory
     */
    public void removeItems() {
        this.items.clear();
    }

    /**
     * Gets the name of the items in the player's inventory
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
     * Serialize the player object to xml
     *
     * @param Document doc
     * @return xml element
     */
    @Override
    public Element serialize(Document doc) {
        Element player = super.serialize(doc);
        player.setAttribute("tweeted", Boolean.toString(this.hasTweeted()));

        Element items = doc.createElement("items");
        player.appendChild(items);
        for (Item item : this.getItems()) {
            Element i = doc.createElement("item");
            i.setAttribute("name", item.getName());
            items.appendChild(i);
        }
        return player;
    }

    /**
     * This method loads the player
     * @param list
     * @param environment
     */
    @Override
    public void load(NodeList list, Environment environment) {
        super.load(list, environment);
        Element player = (Element) list.item(0);
        if (player.getAttribute("tweeted").equals("true")) {
            this.tweeted();
        }
        NodeList items = player.getElementsByTagName("item");
        for (int i = 0; i < items.getLength(); i++) {
            Element item = (Element) items.item(i);
            this.addItem(GameState.findItem(item.getAttribute("name")));
        }
    }
}
