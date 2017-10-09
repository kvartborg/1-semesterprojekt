/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.item;

import worldofzuul.Room;
import worldofzuul.item.Key;

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



}
