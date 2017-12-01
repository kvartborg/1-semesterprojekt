package maga.item;

import maga.character.Player;
import maga.environment.Room;

public class Key extends Item {
    
    /**
     * Creating a variable room of the type Room
     */
    private Room room;

    /**
     * Constructor of the class Key
     * @param room
     */
    public Key(Room room){
        super("Key");
        this.room = room;
    }
    
    /**
     * Getter method for Room
     * @return Room
     */
    public Room getRoom() {
        return room;
    }
    
    /**
     * Setter method for room
     * @param room
     */
    public void setRoom(Room room) {
        this.room = room;
    }
    
    /**
     * Creates a "use" method for the "Key" item. The method checks for a locked
     * door in the current room, and unlocks the door if there is one.
     * @param player 
     */
    @Override
    public void use(Player player) {
        Room currentRoom = player.getCurrentRoom();
        System.out.println("You used the key on the door " + currentRoom.getShortDescription());
        if(currentRoom.getName().equals("Lobby1")) {
            currentRoom.getExit("south").unlock(this);
            System.out.println("The Oval office has been unlocked");
        } else {
            System.out.println("There is no locked door here.");
        }
    }
}