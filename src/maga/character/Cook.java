
package maga.character;

import maga.environment.Room;
import maga.item.Steak;


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