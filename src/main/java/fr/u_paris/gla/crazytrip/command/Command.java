package fr.u_paris.gla.crazytrip.command;

/**
 * This interface represents a command.
 * 
 * A command is an action that can be executed by the user.
 */
public interface Command {
    /**
     * Execute the command.
     * 
     * @param args The arguments of the command
     */
    void execute(String[] args);
}
