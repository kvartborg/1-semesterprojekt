package worldofzuul;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class Game {
    /** 
     * parser attribute, an instance from the Parser class. 
     */
    private Parser parser;
   
    
    public Game() {
        /**
         * This constructor method creates rooms that can be accessed in game.
         */
        createRooms();
        parser = new Parser();
    }
   /** 
    * This method creates rooms, sets their exits and what room you start in. 
    * 
    * The method is called createRooms, and has no parameters. The first thing 
    * it does is define what rooms you want in the game. Thereafter the code
    * equals the room name and prints a string that comes up when you enter
    * a new room. After this you set exits for each room and give what
    * room you can go to from there. The last line sets the room you
    * start in. 
    * 
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
        
        kitchen.setExit("west", lobby2);
        kitchen.setExit("south", diningRoom);
        
        pressBriefingRoom.setExit("west", lobby2); 
        
        cleaningRoom.setExit("south", lobby2); 
        

        currentRoom = pressBriefingRoom;
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
        System.out.println(currentRoom.getLongDescription());
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
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
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
}
