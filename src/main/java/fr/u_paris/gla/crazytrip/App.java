package fr.u_paris.gla.crazytrip;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Properties;

import fr.u_paris.gla.crazytrip.gui.UserInterface;
import fr.u_paris.gla.crazytrip.model.Network;
import fr.u_paris.gla.crazytrip.utils.NetworkBackendHandler;

public class App {
	private static final String UNSPECIFIED = "Unspecified"; //$NON-NLS-1$
	private static final String INFOCMD = "--info";
	private static final String GUICMD = "--gui";
	private static final String CONSOLECMD = "--console";
	private static final String NOEXTRACTCMD = "--noextract";

	public static void main(String[] args) {
		if (args.length == 0) return;
		
		processArgs(args);
	}

	private static void processArgs(String[] args) {
		for (String string : args) {
			if (INFOCMD.equals(string)) {
				processInfoCmd();
			}
			if (GUICMD.equals(string)) {
				processGuiCmd(args);
			}
		}
	}

	private static void processInfoCmd() {
		printAppInfos(System.out);
	}

	private static void processGuiCmd(String[] args) {
		Properties props = readApplicationProperties();
		String title = props.getProperty("app.name");

		checkExtraction(args);
		
		Network.getInstance();

		checkInterface(args, title);
	}

	private static void checkExtraction(String[] args) {
		boolean extract = true;
		for (String arg: args) {
			if (NOEXTRACTCMD.equals(arg)) {
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
			if (CONSOLECMD.equals(arg)) {
				type = "CONSOLE";
			}
		}

		UserInterface userInterface = UserInterface.create(type, title);
		userInterface.start();
	}

	private static void printAppInfos(PrintStream out) {
		Properties props = readApplicationProperties();

		out.println("Application: " + props.getProperty("app.name", UNSPECIFIED)); //$NON-NLS-1$ //$NON-NLS-2$
		out.println("Version: " + props.getProperty("app.version", UNSPECIFIED)); //$NON-NLS-1$ //$NON-NLS-2$
		out.println("By: " + props.getProperty("app.team", UNSPECIFIED)); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private static Properties readApplicationProperties() {
		Properties props = new Properties();

		try (InputStream is = App.class.getResourceAsStream("application.properties")) {
			props.load(is);
		} catch (IOException e) {
			throw new RuntimeException("Unable to read application informations", e);
		}
		return props;
	}
}
