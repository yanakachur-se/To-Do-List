package edu.sda.parser;
/**
 * Representations for all the valid command words for the program
 * along with a string.
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    CREATE("create"), UPDATE("update"), DONE ("done"), REMOVE ("remove"), LIST ("list"),
    BY_DATE ("by_date"), BY_PROJECT ("by_project"), QUIT ("quit"), MENU ("menu"), UNKNOWN("?");

    // The command string.
    private String commandString;

    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}

