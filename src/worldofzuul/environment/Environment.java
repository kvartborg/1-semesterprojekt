/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.environment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import worldofzuul.item.*;

/**
 *
 * @author ViktoriaNadarajah
 */
public class Environment {
    /**
     * HashMap attribute for rooms and their names
     */
    private HashMap<String, Room> rooms = new HashMap<>();

    /**
     * This Set keeps all dummy items.
     */
    private Set<DummyItem> dummyItems = new HashSet<>();
    
    /**
     * This method is a getter method
     * @param name
     * @return returns name of room
     */
    public Room getRoom(String name) {
        return this.rooms.get(name);
    }

    /**
     * Create a new instance of Environment
     */
    public Environment() {
        this.createRooms();
        this.placeItemsInOvalOffice();
        this.placeItemsInDiningRoom();
        this.placeItemsInSecretServiceRoom();
        this.createDummyItems();
    }

    /**
     * This method create the different rooms in our game.
     *
     * We create the rooms in our game and sets an exit for each room.
     * We put our room into a HashMap. The method returns the rooms
     * into our HashMap.
     */
    private void createRooms() {
        Room ovalOffice, lobby1, lobby2, kitchen, diningRoom, cleaningRoom, pressBriefingRoom, secretServiceRoom;

        ovalOffice = new Room("in the oval office");
        lobby1 = new Room("in the first lobby");
        lobby2 = new Room("in the second lobby");
        kitchen = new Room("in the kitchen");
        diningRoom = new Room("in the dining room");
        cleaningRoom = new Room("in the cleaning room");
        pressBriefingRoom = new Room("in the press briefing room");
        secretServiceRoom = new Room("in the secret service room");

        ovalOffice.setExit("north", lobby1);
        ovalOffice.lock();

        lobby1.setExit("north", lobby2);
        lobby1.setExit("east", secretServiceRoom);
        lobby1.setExit("south", ovalOffice);
        lobby1.setExit("west", diningRoom);

        diningRoom.setExit("north", kitchen);
        diningRoom.setExit("east", lobby1);

        secretServiceRoom.setExit("west", lobby1);

        lobby2.setExit("north", cleaningRoom);
        lobby2.setExit("east", pressBriefingRoom);
        lobby2.setExit("south", lobby1);
        lobby2.setExit("west", kitchen);

        kitchen.setExit("east", lobby2);
        kitchen.setExit("south", diningRoom);

        pressBriefingRoom.setExit("west", lobby2);

        cleaningRoom.setExit("south", lobby2);

        rooms.put("Oval office", ovalOffice);
        rooms.put("Lobby1", lobby1);
        rooms.put("Lobby2", lobby2);
        rooms.put("Dining room", diningRoom);
        rooms.put("Secret service room", secretServiceRoom);
        rooms.put("Kitchen", kitchen);
        rooms.put("Press briefing room", pressBriefingRoom);
        rooms.put("Cleaning room", cleaningRoom);
    }
    
    /**
     * Places the computer in the oval office
     */
    public void placeItemsInOvalOffice(){
        Item[] items = {
            new Computer()
        };
        rooms.get("Oval office").addItems(items);
    }
    
    /**
     * Places the ketchup in the dining room
     */
    public void placeItemsInDiningRoom(){
        Item[] items = {
            new Ketchup()
        };
        rooms.get("Dining room").addItems(items);
    }
    
    /**
     * Places the key in the Secret service room
     */
    public void placeItemsInSecretServiceRoom(){
        Item[] items = {
            new Key(rooms.get("Oval office"))
        };
        rooms.get("Secret service room").addItems(items);
    }
    
    /**
     * This method creates dummy items and places them into random rooms.
     */
    private void createDummyItems() {
       dummyItems.add(new DummyItem("Egg"));
       dummyItems.add(new DummyItem("Nuclear-football"));
       dummyItems.add(new DummyItem("Putin-picture-book"));
       dummyItems.add(new DummyItem("Hillary-Clintons-phone"));
       dummyItems.add(new DummyItem("Wig"));
       dummyItems.add(new DummyItem("Mayo"));
       dummyItems.add(new DummyItem("Ivanka-Trumps-sunglasses"));
       for (DummyItem dummyItem : dummyItems ) {
           String[] availableRooms = this.rooms.keySet().toArray(new String[this.rooms.size()]);
           
           String randomRoom = availableRooms[(int) Math.floor(Math.random() * 7)];
           rooms.get(randomRoom).addItem(dummyItem);
       }
    }
}
