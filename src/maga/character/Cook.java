/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maga.character;

import java.util.Scanner;
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
    * This method interacts with the player.
    */
   @Override
   public void talk(Character character){
       Player player = (Player)character;
       if(player.isInventoryFull()) {
           System.out.println("Sorry mate, your inventory is full");
           return;
       } 
       Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, do you want a steak?");
        String answer = scanner.nextLine();
        if("Yes".equalsIgnoreCase(answer)) {
            System.out.println("Here you go");
            character.getCurrentRoom().addItem(new Steak());
            character.pickupItems(new Command(CommandWord.PICKUP, "Steak"));
        }
        else{
            System.out.println("Well okay. Bye");
        }
   }

}
