package fr.u_paris.gla.crazytrip;

import fr.u_paris.gla.crazytrip.command.CommandProcessor;
import fr.u_paris.gla.crazytrip.command.GuiCommand;
import fr.u_paris.gla.crazytrip.command.InfoCommand;
import fr.u_paris.gla.crazytrip.utils.CommandUtils;

public class App {
	public static void main(String[] args) {
		if (args.length == 0) return;
		
		CommandProcessor processor = new CommandProcessor();
		processor.register(CommandUtils.INFOCMD, new InfoCommand());
		processor.register(CommandUtils.GUICMD, new GuiCommand());

		for (String arg: args) {
			processor.execute(arg, args);
		}
	}
}
