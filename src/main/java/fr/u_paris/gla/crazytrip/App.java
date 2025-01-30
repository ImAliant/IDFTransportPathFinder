package fr.u_paris.gla.crazytrip;

import fr.u_paris.gla.crazytrip.command.CommandProcessor;
import fr.u_paris.gla.crazytrip.command.GuiCommand;
import fr.u_paris.gla.crazytrip.command.InfoCommand;
import fr.u_paris.gla.crazytrip.utils.CommandUtils;
import fr.u_paris.gla.crazytrip.utils.InternetChecker;

/**
 * The main class of the application.
 * 
 * This class is the entry point of the application. It reads the command line
 * arguments and processes them.
 * 
 * The application can be run in two modes: info mode and GUI mode.
 * 
 * In info mode, the application displays information about the software.
 * In GUI mode, the application displays a graphical user interface.
 */
public class App {
	public static void main(String[] args) {
		if (args.length == 0) return;
		if (!InternetChecker.isInternetReachable()) {
			System.out.println("No internet connection available.");
			return;
		}
		
		CommandProcessor processor = new CommandProcessor();
		processor.register(CommandUtils.INFOCMD, new InfoCommand());
		processor.register(CommandUtils.GUICMD, new GuiCommand());

		for (String arg: args) {
			processor.execute(arg, args);
		}
	}
}
