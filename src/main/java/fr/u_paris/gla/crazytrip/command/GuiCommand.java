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

public class GuiCommand implements Command {
    @Override
    public void execute(String[] args) {
        Properties props = ApplicationUtils.readApplicationProperties();
        String title = props.getProperty("app.name");

        SwingUtilities.invokeLater(LoadingScreen.getInstance()::start);

        checkExtraction(args);

        Network.getInstance();

        checkInterface(args, title);
    }

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
