package maga;

import maga.environment.Environment;
import maga.character.Cook;
import maga.character.Trump;
import maga.character.Player;
import maga.command.Parser;
import maga.command.Command;
import maga.command.CommandWord;
import maga.util.Console;

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
        Console.print("Thank you for playing. Goodbye.");
    }
    /**
    * This method prints strings when the game is started.
    *
    * The game first prints several strings with a welcoming message. It also
    * gives a command that you can use to get help with the game. In the final
    * line it also prints a description of the room you start in.
    */
    private void printWelcome() {
        Console.print(
            "",
            "Welcome and thank you for playing Make America Great Again. 🇺🇸",
            "The goal is to impeach Donald Trump.",
            "On your mission, you'll be able to carry two items at a time.",
            "If you encounter Trump, the game is over!",
            "Enjoy!",
            "Type '" + CommandWord.HELP + "' if you need help.",
            "",
            trump.whereIsTrump(),
            player.getCurrentRoom().getLongDescription()
        );
    }
    /**
    * This method process the different commands and decide what they do.
    *
    * The method is a switch depending on what command the user
    * has chosen to use. It gives different options on what will happen
    * if the user uses an unknown command or if the user uses a known command.
    * If youLose is true then the game is over.
    *
    * @param command
    * @return boolean (wantToQuit, false or true)
    */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                Console.print("I don't know what you mean...");
                return false;

            case HELP:
                 printHelp();
                 break;

            case GO:
                step();
                player.goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;

            case PICKUP:
                player.pickupItems(command);
                break;

            case DROP:
                player.dropItems(command);
                break;

            case LIST:
                step();
                player.getCurrentRoom().printItems();
                break;

            case INVENTORY:
                player.printInventory();
                break;
            
            case USE:
                player.useItem(command);
                break;
        }
        if (youLose()) {
           return true;
        }

        return wantToQuit;
    }

    /**
     * Every time Player makes a move, the counter "steps" increments, and
     * Trump moves to a new random location.
     * @param command
     */
    private void step() {

         randomizeTrump();

         steps++;
         Console.print(steps + " step(s) taken");
    }
    private void randomizeTrump() {

         String[] possibleDirections = {"east", "west", "north", "south"};

         String direction = possibleDirections[(int) Math.floor(Math.random()  * 3)];

         trump.goRoom(parser.createCommand("go", direction));

    }
    /**
    * This method prints a strings to help the user.
    *
    * The method prints strings with what the game is about and prints
    * what different commands the user has if the user uses the command help.
    */
    private void printHelp() {
        Console.print(
            "You are lost. You are alone. You wander",
            "around at The White House.",
            "",
            "Your command words are:"
        );
        parser.showCommands();
    }

    /**
    * This method quits the game.
    *
    * The method decides what happens when the user uses the quit command.
    * It first checks if the user posted a second word when quitting, returns
    * false if it has. If the user doesnt have a second word after quit, then
    * its true and therefore quits the game.
    *
    * @param command
    * @return boolean (false if second word, true if not)
    */
    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            Console.print("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method checks if the player is in the same room as Trump.
     * If you are then the game is lost.
     * @return returns true or false
     */
    private boolean youLose(){
        if (player.getCurrentRoom() != trump.getCurrentRoom()) {
           return false;
        }
        Console.print("You entered the same room as Trump, game lost.");
        return true;
    }
}
