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
public class Steak extends Item {
    /**
     * Creating a variable of the type Ketchup
     */
    private Ketchup ketchup;
    
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
        return this.ketchup != null;
    }
    /**
     * Creating a method to add Ketchup to the steak
     * @param ketchup 
     */
    public void addKetchup(Ketchup ketchup){
        this.ketchup = ketchup;
    }
}
