/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.character;

import worldofzuul.command.Command;
import worldofzuul.environment.Room;

/**
 *
 * @author Kasper
 */
public class Trump extends Character {
    /**
     * The name of the character
     */
    public Trump() {
        super("Donald Trump");
    }
    /**
     * The method sets Trumps route to the steak item.
     * @param cleaningRoom is the room with the steak.
     */
    public void findSteak(Room cleaningRoom) {


    }
    /**
     * Simplified method for the character Trumps movement.
     * @param command 
     */
    @Override
    public void goRoom(Command command) {
        if(!command.hasSecondWord()) {
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = getCurrentRoom().getExit(direction);

        if (nextRoom != null) {
           setCurrentRoom(nextRoom);
        }
        System.out.println("Trump is currently in: " + getCurrentRoom().getShortDescription()); 
    }
}
