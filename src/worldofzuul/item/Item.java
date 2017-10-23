/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.item;
import worldofzuul.character.Player;


/**
 *
 * @author mikkellarsen
 */
public abstract class Item {
    /**
     * String which sets the name
     */
    private String name;
    /**
     * Creating a private boolean expression which by default is true
     */
    private boolean moveable = true;

    /**
     * Creating a constructor for the class Item with 1 parameter
     * @param name
     */
    public Item(String name) {
        this.name = name;
    }
    /**
     * Creating a constructor for the class Item with 2 parameteres
     * @param name
     * @param moveable
     */
    public Item(String name, boolean moveable) {
        this.name = name;
        this.moveable = moveable;
    }

    /**
     * This method returns the Items name.
     * @return returns the items name. 
     */
    public String getName() {
        return name;
    }
    

    public void use(Player player) {  
        System.out.println("There is no way to use this item.");
    }
    /**
     *This method makes it possible to tell if the item is moveable or not
     * @return returns a boolean
     */
    public boolean isMoveable() {
        return moveable;
    
    }
}
