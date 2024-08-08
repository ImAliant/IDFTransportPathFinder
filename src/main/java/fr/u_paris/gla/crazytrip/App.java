package fr.u_paris.gla.crazytrip;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import fr.u_paris.gla.crazytrip.algorithm.DijkstraPathFinder;
import fr.u_paris.gla.crazytrip.model.Coordinates;
import fr.u_paris.gla.crazytrip.model.Network;
import fr.u_paris.gla.crazytrip.model.Segment;
import fr.u_paris.gla.crazytrip.model.Station;
import fr.u_paris.gla.crazytrip.utils.ItineraryPrinter;
import fr.u_paris.gla.crazytrip.utils.NetworkBackendHandler;

public class App {
	private static final String UNSPECIFIED = "Unspecified"; //$NON-NLS-1$
	private static final String INFOCMD = "--info";
	private static final String GUICMD = "--gui";

	public static void main(String[] args) throws IOException {
		//NetworkBackendHandler.extraction();
		Network network = Network.getInstance();

		Station start = network.getNearestStation(new Coordinates(48.857874, 2.399755));
		Station end = network.getNearestStation(new Coordinates(48.853081, 2.370830));

		System.out.println("Start: " + start);
		System.out.println("End: " + end);

		List<Segment> path = DijkstraPathFinder.getSegmentsFromItinerary(start, end);

		ItineraryPrinter printer = new ItineraryPrinter(path);
		printer.print();

		/* if (args.length == 0) return;
		
		processArgs(args); */
	}

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
