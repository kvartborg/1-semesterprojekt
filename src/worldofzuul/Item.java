/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author mikkellarsen
 */
public class Item {
    /**
     * Creating private variables 
     * String which sets the name
     * Boolean expression which is true by default
     */
    private String name;
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
}



