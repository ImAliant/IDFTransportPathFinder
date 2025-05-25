package fr.u_paris.gla.crazytrip.command;

import java.util.Map;
import java.util.HashMap;

/**
 * This class represents a command processor.
 * It is used to store and execute commands if they exist.
 */
public class CommandProcessor {
    /** The commands */
    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Register a command.
     * 
     * @param name The name of the command
     * @param command The command
     * 
     * @see Command
     */
    public void register(String name, Command command) {
        commands.put(name, command);
    }

    /**
     * Execute a command if it exists.
     * 
     * @param name The name of the command
     * @param args The arguments of the command
     */
    public void execute(String name, String[] args) {
        Command command = commands.get(name);
        if (command != null) {
            command.execute(args);
        }
    }
}
