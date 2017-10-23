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
    
    public void addKetchup(){
        this.ketchup = true;
    }
    
    @Override
    public void use(Player player) {
        for(Item item : player.getItems()) {
            if(item.getName().equals("Ketchup")){
                this.ketchup = true;
                System.out.println("The steak now has ketchup on it.");
                return;
            } 
        }
        System.out.println("You need ketchup to use the steak.");
    }
}
