package worldofzuul.command;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord {
    /**
     * Available commands
     */
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?");

    /**
     * The entered command
     */
    private String commandString;

    /**
     * Create a new instance of a CommandWord
     * @param commandString the commandString
     * @return a new instance of the CommandWord
     */
    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    /**
     * If the CommandWord instance is used in a string context
     * @return the commandString
     */
    public String toString() {
        return commandString;
    }
}
