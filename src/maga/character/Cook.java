/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maga.character;

import maga.environment.Room;
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
    * Method for creating a steak and adding it to a room.
    * @param room 
    */
   public void createSteak(Room room) {
       room.addItem(new Steak());
   }
}