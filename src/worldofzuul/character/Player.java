/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.character;

import java.util.ArrayList;
import worldofzuul.command.Command;
import worldofzuul.item.Item;

/**
 *
 * @author Kasper
 */
public class Player extends Character{
    /**
     * The arraylist stores the items, that the player picks up.
     */
    private ArrayList<Item> items = new ArrayList<Item>();
    private final int MAX_ITEMS = 2; 

    /**
     * A constructor for the player class
     */
    public Player(){
        super("Fake news Reporter");
    }

    /**
     * The method adds items to the arraylist.
     * 
     * The method checks if the item that you want to add is in the 
     * room that the player is in. If it is and you write it correct
     * then it adds an item to the ArrayList.
     * 
     * @param command 
     * @return returns either false or true if the item is added.
     */
    public boolean pickupItems(Command command){
        if (isInventoryFull()) {
            System.out.println("Your inventory is full. ");
            return false;
        }
        
        if(!command.hasSecondWord()) {
            System.out.println("Pickup what?");
            return false;
        }
        for (Item item : getCurrentRoom().getItems()) {
            if(item.getName().equalsIgnoreCase(command.getSecondWord())) {
                if(item.isMoveable() == true) {
                System.out.println("You picked up: " + item.getName());
                getCurrentRoom().getItems().remove(item); 
                return items.add(item); 
            }
                else {
                    System.out.println("This item can not be picked up");
                    return false;
                }
            }
        } 
        System.out.println("There is no item in this room with that name.");
        return false; 
            
    }
    /**
     * This method drops items from the arraylist. 
     * 
     * The method checks if the item you want to drop is in the ArrayList
     * if it is then it addxs the item into the rooms ArrayList and removes
     * it from the players ArrayList. 
     * 
     * @param command
     * @return returns either false or true if the item is dropped. 
     */
    public boolean dropItems(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return false;
        }   
        for (Item item : getItems()) {
            if (item.getName().equalsIgnoreCase(command.getSecondWord())) {
                System.out.println("You dropped: " + item.getName());
                getCurrentRoom().getItems().add(item); 
                return items.remove(item);
            } 
            
        }
        System.out.println("You have no item with that name.");
        return false; 
      
    }
    /**
     * The method displays the items currently held.
     * @return
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * The method checks if the inventory is full.
     * @return returns a "true" or "false" value depending on how many items held.
     */
    private boolean isInventoryFull() {
        return (this.items.size() >= MAX_ITEMS);
    }
    
        /**
     * Prints the items in the players inventory
     */
    public void printInventory(){
        if (getItems().isEmpty()) {
            System.out.println("Your inventory is empty.");
        }
        else{
            System.out.println("Your inventory contains: ");
            for(Item item : getItems()){
                System.out.println(item.getName());
            }
            System.out.println("");   
        }
    }

}
