/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maga.item;
import maga.character.Player;
/**
 *
 * @author mikkellarsen
 */
public class Steak extends Item {
    
    /**
     * Creating a variable of the type Ketchup
     */
    private boolean ketchup = false;

    /**
     * Creating a constructor with only the name
     */
    public Steak(){
        super("Steak");
    }
    
    /**
     * Creating a method to see if the Steak has ketchup
     * @return null if the Steak does not have ketchup
     */
    public boolean hasKetchup(){
        return this.ketchup;
    }
    
    /**
     * Creates a "use" method for the "Steak" item. The method searches for
     * a "Ketchup" item, and cant be used if there isn't one in the inventory.
     * @param player 
     */
    @Override
    public void use(Player player) {
        for(Item item : player.getItems()) {
            if(item.getName().equals("Ketchup")){
                this.ketchup = true;
                this.setName("Steak-with-ketchup");
                System.out.println("The steak now has ketchup on it.");
                return;
            } 
        }
        System.out.println("You need ketchup to use the steak.");
    }
}