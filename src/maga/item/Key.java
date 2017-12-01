/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maga.item;

import maga.character.Player;
import maga.environment.Room;

/**
 *
 * @author mikkellarsen
 */
public class Key extends Item {

    /**
     * Creating a variable room of the type Room
     */
    private Room room;

    /**
     * Creating a constructor of the class Key
     * @param room
     */
    public Key(Room room){
        super("Key");
        this.room = room;
    }

    /**
     * Creating a method to get room
     * @return room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Creating a method to set the room
     * @param room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Creates a "use" method for the "Key" item. The method check for a locked
     * door in the current room, and unlocks the door if there is one.
     * @param player
     */
    @Override
    public void use(Player player) {
        Room currentRoom = player.getCurrentRoom();

        if(currentRoom.getName().equals("Lobby1")) {
            currentRoom.getExit("south").unlock(this);
        }
    }
}
