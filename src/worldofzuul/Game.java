package worldofzuul;

import worldofzuul.environment.Environment;
import worldofzuul.character.Cook;
import worldofzuul.character.Trump;
import worldofzuul.character.Player;
import worldofzuul.command.Parser;
import worldofzuul.command.Command;
import worldofzuul.command.CommandWord;
import worldofzuul.environment.Room;
import worldofzuul.item.Item;

//TODO
//Add steak to cook??

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game {
    /**
     * parser attribute, an instance from the Parser class.
     */
    private Parser parser;

    /**
     * The environment which contains all rooms
     */
    private Environment environment;

    /**
     * Player
     */
    private Player player;

    /**
     * Trump
     */
    private Trump trump;

    /**
     * Cook
     */
    private Cook cook;

    /**
     * Steps
     */
    private int steps = 0;

    /**
     * Start Time
     */
    private Long startTime = System.currentTimeMillis() / 1000L;

    /**
     * End time
     */
    private Long endTime;


    /**
     * Create new instance of game
     */
    public Game() {
        environment = new Environment();
        player = new Player();
        trump = new Trump();
        cook = new Cook();
        parser = new Parser();

        player.setCurrentRoom(environment.getRoom("Press briefing room"));
        trump.setCurrentRoom(environment.getRoom("Oval office"));
        cook.setCurrentRoom(environment.getRoom("Kitchen"));
    }

    /**
    * This method called play is what decides when the game is done.
    *
    * The method starts with the printWelcome when the game is started, which
    * is printed in the method after. The code then sets a boolean that sets
    * the finished to false. After a while loop with the condition not finished
    * is made that checks what command the user has used. When the game is finished
    * it jumps out the while loop and prints a string.
    */
    public void play() {
        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    /**
    * This method prints strings when the game is started.
    *
    * The game first prints several strings with a welcoming message. It also
    * gives a command that you can use to get help with the game. In the final
    * line it also prints a description of the room you start in.
    */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the game: Unseat President Trump");
        System.out.println("Unseat President Trump is an amazing new game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    /**
    * This method process the different comamnds and decide what they do.
    *
    * The method is a multi if /else depending on what command the user
    * has chosen to use. It gives different options on what will happen
    * if the user uses an unknown command or if the user uses a known command.
    *
    *
    * @param command
    * @return boolean (wantToQuit, false or true)
    */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            player.goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.PICKUP) {
            player.pickupItems(command); 
        } else if (commandWord == CommandWord.DROP) {
            player.dropItems(command);
        } else if (commandWord == CommandWord.LIST) {
            printItems(player.getCurrentRoom());
        }

        return wantToQuit;
    }
    /**
    * This method prints a strings to help the user.
    *
    * The method prints strings with what the game is about and prints
    * what different commands the user has if the user uses the command help.
    */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
    * This method quits the game.
    *
    * The method decides what happens when the user uses the quit command.
    * It first checks if the uses posted a second word when quitting, returns
    * false if it has. If the user doesnt have a second word after quit, then
    * its true and therefore quits the game.
    *
    * @param command
    * @return boolean (false if second word, true if not)
    */
    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Prints the items in the players currentroom
     * @param room the room the player is in
     */
    public void printItems(Room room){
            if (room.getItems()==null) {
                System.out.println("The room is empty");
            }
            else{
                for(Item item : room.getItems()){
                    System.out.print(item.getName()+" ");
                }
                System.out.println("");   
            }
    }
}
