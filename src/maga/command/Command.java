package maga.command;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Command {
    
    /**
     * The command to execute
     */
    private final CommandWord commandWord;

    /**
     * The argument for the command
     */
    private final String secondWord;

    /**
     * Create a new instance of a command
     * @param  commandWord the command
     * @param  secondWord  the argument for the command
     */
    public Command(CommandWord commandWord, String secondWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    /**
     * Getter for the commandWord
     * @return value of the commandWord attribute
     */
    public CommandWord getCommandWord() {
        return commandWord;
    }

    /**
     * Getter for the secondWord
     * @return value of the secondWord attribute
     */
    public String getSecondWord() {
        return secondWord;
    }

    /**
     * Check if the commandWord is unknown
     * @return true if commandWord is unknown
     */
    public boolean isUnknown() {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     * Check if the command has a secondWord
     * @return true if secondWord is not equal to null
     */
    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}