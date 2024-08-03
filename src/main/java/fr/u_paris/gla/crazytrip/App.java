package fr.u_paris.gla.crazytrip;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Network;
import fr.u_paris.gla.crazytrip.model.Node;
import fr.u_paris.gla.crazytrip.model.Segment;
import fr.u_paris.gla.crazytrip.model.SegmentTransport;
import fr.u_paris.gla.crazytrip.model.SegmentWalk;
import fr.u_paris.gla.crazytrip.model.Station;
import fr.u_paris.gla.crazytrip.parser.Parser;
import fr.u_paris.gla.crazytrip.utils.NetworkBackendHandler;

public class App {
	private static final String UNSPECIFIED = "Unspecified"; //$NON-NLS-1$
	private static final String INFOCMD = "--info";
	private static final String GUICMD = "--gui";

	public static void main(String[] args) {
		debug();

		/*
		 * if (args.length == 0) return;
		 * 
		 * processArgs(args);
		 */
	}

	/* DEBUG FUNCTIONS */
	private static void debug() {
		try {
			NetworkBackendHandler.extraction();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Debugging...");

		Network network = Network.getInstance();

		while (true) {
			String line = System.console().readLine();
			switch (line) {
				case "1":
					showALine(network);
					break;
				case "3":
					showAllLines(network);
					break;
				default://case "2": case "3":
					return;
			}
		}
	}

	private static boolean showALine(Network network) {
		String line = System.console().readLine();
		String[] parts = line.split(" ");

		Line transLine = network.getLine(parts[0], parts[1]);
		if (transLine == null) {
			System.out.println("This line doesn't exist");
			return false;
		}

		transLine.printLine();

		return true;
	}

	private static void showAllLines(Network network) {
		Map<String, Line> allLines = network.getAllLines();

		allLines.forEach((key, value) -> System.out.println(value));
	}

	/* END OF DEBUG FUNCTIONS*/

	private static void processArgs(String[] args) {
		for (String string : args) {
			if (INFOCMD.equals(string)) {
				processInfoCmd();
			}
			if (GUICMD.equals(string)) {
				processGuiCmd();
			}
		}
	}

	private static void processInfoCmd() {
		printAppInfos(System.out);
	}

	private static void processGuiCmd() {
		Properties props = readApplicationProperties();

		/*
		 * try {
		 * NetworkBackendHandler.extraction();
		 * } catch (IOException e) {
		 * e.printStackTrace();
		 * }
		 */
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
