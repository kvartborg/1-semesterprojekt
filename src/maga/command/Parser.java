package maga.command;

import java.util.Scanner;


/**
 *
 * @author ViktoriaNadarajah
 */
public class Parser {

    /**
     * It is a list of CommandWords
     */
    private final CommandWords commands;

    /**
     * The Scanner reads the commands
     */
    private final Scanner reader;

    /**
     * The reader reads new commands if any
     */
    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
    * It reads word1 and makes a new Scanner to make a inputLine.
    * Tokenizer is a instance variable of the type Scanner. It returns new commands
    * @return command object
    */
    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        inputLine = reader.nextLine();

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
     * Creates a command.
     * @param word1 is the command.
     * @param word2 is the argument.
     * @return instance of command.
     */
    public Command createCommand(String word1, String word2) {
        return new Command(commands.getCommandWord(word1), word2);
    }

    /**
    * Shows all the commands
    */
    public void showCommands() {
        commands.showAll();
    }
}
