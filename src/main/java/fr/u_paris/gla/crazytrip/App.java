package fr.u_paris.gla.crazytrip;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.SwingUtilities;

import fr.u_paris.gla.crazytrip.controller.ConsoleController;
import fr.u_paris.gla.crazytrip.gui.UserInterface;
import fr.u_paris.gla.crazytrip.gui.view.ConsoleView;
import fr.u_paris.gla.crazytrip.model.Network;
import fr.u_paris.gla.crazytrip.utils.NetworkBackendHandler;

public class App {
	private static final String UNSPECIFIED = "Unspecified"; //$NON-NLS-1$
	private static final String INFOCMD = "--info";
	private static final String GUICMD = "--gui";

	public static void main(String[] args) {
		if (args.length == 0) return;
		
		processArgs(args);
	}

	// TODO: Need to be moved to a dedicated class
	/* public static List<Station> stationFinder(Network network, String name) {
		return network.getStationsByName(name);
	}

	public static Station stationSelection(Network network, List<Station> stations) {
		for (int i = 0; i < stations.size(); i++) {
			Station station = stations.get(i);
			List<Line> line = network.getLinesFromStation(station);

			String lineFormat;
			if (line.size() == 1) {
				lineFormat = line.get(0).getName();
			} else {
				lineFormat = line.stream().map(Line::getName).reduce((a, b) -> a + ", " + b).get();
			}

			String format = String.format("%d: %s %s", i, station, lineFormat);
			System.out.println(format);
		}

		System.out.println("Choose a station: ");
		String choice = System.console().readLine();

		return stations.get(Integer.parseInt(choice));
	}

	public static Coordinates coordinatesChooser() {
		double latitude;
		double longitude;

		latitude = positionChooser("Enter the latitude: ");
		if (latitude == -1) {
			return null;
		}
		longitude = positionChooser("Enter the longitude: ");
		if (longitude == -1) {
			return null;
		}

		return new Coordinates(latitude, longitude);
	}

	public static double positionChooser(String message) {
		String line;
		double position;

		System.out.println(message);
		line = System.console().readLine();
		if (!validPositionChoice(line)) {
			System.out.println("Invalid choice");
			return -1;
		}
		position = Double.parseDouble(line);

		return position;
	}

	public static boolean validPositionChoice(String choice) {
		try {
			Double.parseDouble(choice);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	} */
	//

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
		/* Properties props = readApplicationProperties();
		String title = props.getProperty("app.name");

		try {
			NetworkBackendHandler.extraction();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Network.getInstance();

		SwingUtilities.invokeLater(() -> {
			// TODO start interface
		}); */

		UserInterface userInterface = UserInterface.create("CONSOLE", "");
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
