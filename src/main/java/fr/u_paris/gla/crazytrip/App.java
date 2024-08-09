package fr.u_paris.gla.crazytrip;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import fr.u_paris.gla.crazytrip.algorithm.DijkstraPathFinder;
import fr.u_paris.gla.crazytrip.dtos.NodeDTO;
import fr.u_paris.gla.crazytrip.dtos.SegmentTransportDTO;
import fr.u_paris.gla.crazytrip.model.Coordinates;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Network;
import fr.u_paris.gla.crazytrip.model.Node;
import fr.u_paris.gla.crazytrip.model.Segment;
import fr.u_paris.gla.crazytrip.model.Station;
import fr.u_paris.gla.crazytrip.model.key.LineKey;
import fr.u_paris.gla.crazytrip.model.key.NodeKey;
import fr.u_paris.gla.crazytrip.parser.Parser;
import fr.u_paris.gla.crazytrip.utils.ItineraryPrinter;
import fr.u_paris.gla.crazytrip.utils.NetworkBackendHandler;

public class App {
	private static final String UNSPECIFIED = "Unspecified"; //$NON-NLS-1$
	private static final String INFOCMD = "--info";
	private static final String GUICMD = "--gui";

	public static void main(String[] args) throws IOException {
		/* if (args.length == 0) return;
		
		processArgs(args); */
	}

	// TODO: Need to be moved to a dedicated class
	public static List<Station> stationFinder(Network network, String name) {
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
		Properties props = readApplicationProperties();
		System.out.println("Launching GUI for " + props.getProperty("app.name", UNSPECIFIED)); //$NON-NLS-1$ //$NON-NLS-2$
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
