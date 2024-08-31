package fr.u_paris.gla.crazytrip.command;

import java.util.Map;
import java.util.HashMap;

public class CommandProcessor {
    private final Map<String, Command> commands = new HashMap<>();

    public void register(String name, Command command) {
        commands.put(name, command);
    }

    public void execute(String name, String[] args) {
        Command command = commands.get(name);
        if (command != null) {
            command.execute(args);
        }
    }
}
