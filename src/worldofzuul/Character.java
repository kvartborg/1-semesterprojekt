/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author Kasper
 */
public class Character {
    
    /**
     * currentRoom attribute, an instance from the Room class. 
     * 
     * The currentRoom variable is what room the player is in at the moment
     * in game. 
     */
    private Room currentRoom;
    
    
    
    /**
     * The name of the character
     */
    private String name;
    
    /**
     * A constructor for a character
     * @param name The name of the character
     */
    public Character(String name){
        this.name = name;
    }
    /**
     * A no arguments constructor
     */
    Character(){
        
    }
    
     /**
     * This method checks if the command has a second word. 
     * 
     * The method firstly checks if the "go" command has a second word or not.
     * if it doesnt then it tells the user to use a second word. 
     * It also changes the currentroom to the the next room the user wants
     * to access. 
     * @param command 
     */
      private void goRoom(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
}
