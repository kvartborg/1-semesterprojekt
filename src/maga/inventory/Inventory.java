package maga.inventory;

import java.util.ArrayList;
import java.util.Iterator;
import maga.item.Item;

public class Inventory implements Iterable<Item> {
    /**
     * Capacity of the inventory, if 0 then the capacity should be considered infinite
     */
    private final int capacity;

    /**
     * List of items
     */
    private ArrayList<Item> items = new ArrayList<Item>();

    /**
     * Create a new instance of inventory with no capacity limit
     * @return [description]
     */
    public Inventory () {
        this(0);
    }

    /**
     * Create a new instance of inventory with a max capacity
     * @param capacity is the maximum number of items the inventory can hold
     */
    public Inventory (int capacity) {
        this.capacity = capacity;
    }

    /**
     * Get item from a name
     * @param name is the name of the item to look for
     * @return the found item or null if nothing matches
     */
    public Item get (String name) {
        for (Item item : this.items) {
            if (item.getName().equalsIgnoreCase(name)) return item;
        }

        return null;
    }

    /**
     * Add item to the inventory
     * @param  item to add to the inventory
     * @return true if the inventory has capacity for the new item
     */
    public boolean add (Item item) {
        if (this.isFull()) {
            return false;
        }

        return this.items.add(item);
    }

    /**
     * Remove an item from the inventory
     * @param  item to remove
     * @return true if the item was removed
     */
    public boolean remove (Item item) {
        return this.items.remove(item);
    }

    /**
     * Check if inventory contains an item
     * @param  item to look for
     * @return if inventory contains item then return true
     */
    public boolean contains (Item item) {
        return this.items.contains(item);
    }

    /**
     * Check if inventory contains an item
     * @param  item name to look for
     * @return if inventory contains item then return true
     */
    public boolean contains (String name) {
        for (Item item : this.items) {
            if (item.getName().equalsIgnoreCase(name)) return true;
        }

        return false;
    }

    /**
     * Check if the Inventory is full
     * @return true if the array length is greater than the capacity
     */
    public boolean isFull () {
        if (capacity == 0) {
            return false;
        }

        return this.items.size() >= capacity;
    }

    /**
     * Check if inventory is empty
     * @return true if the items array is empty
     */
    public boolean isEmpty () {
        return this.items.size() == 0;
    }

    /**
     * Iterate over items within the inventory
     * @return a list of items to iterate over
     */
    public Iterator<Item> iterator () {
      return this.items.iterator();
    }
}
