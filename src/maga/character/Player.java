/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maga.character;

import java.util.ArrayList;
import maga.command.Command;
import maga.item.Item;
import maga.item.Steak;
import maga.command.CommandWord;

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
    @Override
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
            if(
                item.getName().equalsIgnoreCase(command.getSecondWord())
            ) { 
                if(!item.isMoveable()) {
                    System.out.println("This item can not be picked up");
                    return false; 
                }
                System.out.println("You picked up: " + item.getName());
                getCurrentRoom().getItems().remove(item); 
                return items.add(item); 
    
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
        } else{
            System.out.println("Your inventory contains: ");
            for(Item item : getItems()){
                System.out.println(item.getName());
            }
            System.out.println("");   
        }
    }
    
    /**
     * Creates a "use" method that requires a second word, and 
     * looks for the item to be used in the inventory. 
     * @param command 
     */
    public void useItem(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("What do you want to use?");
            return;
        }
        String itemName = command.getSecondWord();
        
        for(Item item : this.items) {
            if(item.getName().equalsIgnoreCase(itemName)){
                item.use(this);
                break;
            }
        }
        for(Item item : this.getCurrentRoom().getItems()) {
            if(item.getName().equalsIgnoreCase(itemName)){
                item.use(this);
                break;
            }
        }
    }
    
    /**
     * Interaction between the player and the cook
     * @param character the cook the player interacts with
     */
    @Override
    public void talk(Character character){
        if (this.getCurrentRoom() != character.getCurrentRoom()) {
            System.out.println("There's no one in the room.");
            return;
        }
        character.talk(this);
        
    }

    /**
     * Returns the state of tweeted.
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
     * @param name
     * @return 
     */
    public boolean hasItem(String name) {
        return getItem(name) != null; 
    }
    
    /**
     * This method is a getter method for our items.
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

}
