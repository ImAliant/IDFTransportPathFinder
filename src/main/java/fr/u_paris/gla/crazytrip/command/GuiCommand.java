package fr.u_paris.gla.crazytrip.command;

import java.io.IOException;
import java.util.Properties;

import javax.swing.SwingUtilities;

import fr.u_paris.gla.crazytrip.gui.UserInterface;
import fr.u_paris.gla.crazytrip.gui.loadingscreen.LoadingScreen;
import fr.u_paris.gla.crazytrip.model.Network;
import fr.u_paris.gla.crazytrip.utils.ApplicationUtils;
import fr.u_paris.gla.crazytrip.utils.CommandUtils;
import fr.u_paris.gla.crazytrip.utils.NetworkBackendHandler;

/**
 * This class represents a GUI command.
 * It is used to start the application with a graphical user interface.
 */
public class GuiCommand implements Command {
	/**
	 * Start the application with a graphical user interface.
	 * 
	 * @param args The arguments of the command. 
	 * 			   It can contain the argument "noextract" to avoid the extraction of the network data.
	 * 			   And the argument "--console" to start the application with a console interface. 
	 */
    @Override
    public void execute(String[] args) {
		// Load the application properties
        Properties props = ApplicationUtils.readApplicationProperties();
        String title = props.getProperty("app.name");

		// Start the loading screen
        SwingUtilities.invokeLater(LoadingScreen.getInstance()::start);
		// Check if the extraction should be done
        checkExtraction(args);

        Network.getInstance();

		// Start the user interface
        checkInterface(args, title);
    }

	/**
	 * Check if the extraction should be done. 
	 * 
	 * @param args Can contain the an argument "noextract" to avoid the extraction 
	 * 			   of the network data.
	 */
    private static void checkExtraction(String[] args) {
		boolean extract = true;
		for (String arg: args) {
			if (CommandUtils.NOEXTRACTCMD.equals(arg)) {
				extract = false;
			}
		}
		
		if (extract) {
			try {
				NetworkBackendHandler.extraction();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * <br>Start the user interface.</br>
	 * <br>Two types of interfaces can be used: ONLINE or CONSOLE.</br>
	 * <br>The default interface is ONLINE.</br>
	 * 
	 * @param args The arguments of the command. If the argument "--console" is present, the interface will be CONSOLE.
	 * @param title The title of the application
	 */
	private static void checkInterface(String[] args, String title) {
		String type = "ONLINE";
		for (String arg: args) {
			if (CommandUtils.CONSOLECMD.equals(arg)) {
				type = "CONSOLE";
			}
		}

		UserInterface userInterface = UserInterface.create(type, title);
		userInterface.start();
	}
}
