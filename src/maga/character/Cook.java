/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maga.character;

import maga.command.Command;
import maga.command.CommandWord;
import maga.item.Steak;

/**
 *
 * @author Rasmus
 */
public class Cook extends Character {

    /**
     * Names the character "Cook".
     */
   public Cook() {
       super("Cook");
   }
   
   /**
    * Override method for the talk method
    * What's printed when the player uses the talk command
    */
   @Override
   public void talk(Character character){
        System.out.println("Hello, here's a steak for you!");
        character.getCurrentRoom().addItem(new Steak());
        character.pickupItems(new Command(CommandWord.PICKUP, "Steak"));
   }

}
