package maga.command;


public enum CommandWord {
    
    /**
     * Available commands
     */
    GO("go"),
    QUIT("quit"),
    SEARCH("search"),
    PICKUP("pickup"),
    DROP("drop"),
    INVENTORY("inventory"),
    UNKNOWN("?"),
    USE("use"),
    CALLTRUMP("call"),
    TALK("talk"),
    WAIT("wait"),
    SAVE("save"),
    LOAD("load");

    /**
     * The entered command
     */
    private final String commandString;

    /**
     * Create a new instance of a CommandWord
     *
     * @param commandString the commandString
     * @return a new instance of the CommandWord
     */
    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    /**
     * If the CommandWord instance is used in a string context
     *
     * @return the commandString
     */
    @Override
    public String toString() {
        return commandString;
    }
}