package maga.item;
import maga.character.Player;

public abstract class Item {
    
    /**
     * String which sets the name
     */
    private String name;
    
    /**
     * Boolean expression that describes if the item is moveable
     */
    private boolean moveable = true;

    /**
     * Constructor for the class Item with 1 parameter
     * @param name
     */
    public Item(String name) {
        this.name = name;
    }
    
    /**
     * Constructor for the class Item with 2 parameteres
     * @param name
     * @param moveable
     */
    public Item(String name, boolean moveable) {
        this.name = name;
        this.moveable = moveable;
    }

    /**
     * Getter for the name of the item
     * @return returns the items name. 
     */
    public String getName() {
        return name;
    }
    
    /**
     * Setter for the name of an item
     * @param name The new name of the steak item
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Creates a "use" method for dummyItems, has no implementation
     * @param player 
     */
    public void use(Player player) {  
    }
    
    /**
     *This method makes it possible to tell if the item is moveable or not
     * @return boolean
     */
    public boolean isMoveable() {
        return moveable;
    }
}