/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maga.character;

import maga.command.Command;
import maga.command.CommandWord;
import maga.environment.Room;

/**
 *
 * @author Kasper
 */
public class Trump extends Character {
    
    /**
     * Integer that sets how many rounds Trump should wait.
     */
    private int roundsToWait = 0;
    
    /**
     * The name of the character
     */
    public Trump() {
        super("Donald Trump");
    }
    
    /**
     * This method is a get method for the rounds to wait.
     * @return 
     */
    public int getRoundsToWait() {
        return roundsToWait;
    }
    
    /**
     * This method sets the rounds to wait.
     * @param roundsToWait
     */
    public void setRoundsToWait(int roundsToWait) {
        this.roundsToWait = roundsToWait;
    }
    
    /**
     * The method sets Trumps route to the steak item.
     * @param cleaningRoom is the room with the steak.
     * @param player
     * @return returns an integer with points
     */
    public int findSteak(Room cleaningRoom, Player player) { 
        if (player.getCurrentRoom() != cleaningRoom) {
            return 0; 
        }
        if (!player.hasItem("steak-with-ketchup")) {
            return 0;
        }
        this.setCurrentRoom(cleaningRoom);
        roundsToWait = 3; 
        player.dropItems(new Command(CommandWord.DROP, "steak-with-ketchup"));
        player.goRoom(new Command(CommandWord.GO, "south"));
        return 5000;
    }
    
    /**
     * Simplified method for the character Trumps movement.
     * @param command 
     */
    @Override
    public void goRoom(Command command) {
        if (roundsToWait != 0) {
            roundsToWait--;
            return;
        }
        if(!command.hasSecondWord()) {
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = getCurrentRoom().getExit(direction);

        if (nextRoom != null) {
           setCurrentRoom(nextRoom);
        }
    }
}
