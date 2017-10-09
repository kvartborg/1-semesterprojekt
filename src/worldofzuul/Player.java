/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.ArrayList;

/**
 *
 * @author Kasper
 */
public class Player extends Character{
    /**
     * The arraylist stores the items, that the player picks up. 
     */
    private ArrayList<Item> items = new ArrayList<Item>();
    
    /**
     * The method adds items to the arraylist.
     * @param items the item which is added to the inventory.
     */
    public void addItems(ArrayList items){
        
    }
    /**
     * The method displays the items currently held.
     */
    public void getItems() {
        for (String item : items) {
            System.out.println(item);
        }    
    }
    
    /**
     * The method checks if the inventory is full.
     * @return returns a "true" or "false" value depending on how many items held.
     */
    private boolean isInventoryFull() {
        
        if (items.length() > 1) {
            
            return true;
        }
        else {
            return false;
        }
    }
    
}
