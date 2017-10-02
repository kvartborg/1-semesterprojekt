package worldofzuul;

import java.util.Scanner;


/**
 * 
 * @author ViktoriaNadarajah
 * It is a list of CommandWords
 */
public class Parser {
    private CommandWords commands;
    private Scanner reader;

    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }
    /**
    * @return
    * It reads word1 and makes a new Scanner to make a inputLine
    */
    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();
        
        /**
        * Tokenizer is a instance variable of the type Scanner. It returns new commands.
        */
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        return new Command(commands.getCommandWord(word1), word2);
    }
    /**
    * Shows all the commands
    */
    public void showCommands() {
        commands.showAll();
    }
}
