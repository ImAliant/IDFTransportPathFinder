package fr.u_paris.gla.crazytrip.controller;

import java.util.List;

import fr.u_paris.gla.crazytrip.algorithm.AstarPathFinder;
import fr.u_paris.gla.crazytrip.algorithm.ItineraryResult;
import fr.u_paris.gla.crazytrip.dao.LineDAO;
import fr.u_paris.gla.crazytrip.dao.StationDAO;
import fr.u_paris.gla.crazytrip.gui.view.ConsoleView;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Station;

/**
 * This class represents a console controller.
 * 
 * It is used to start the application with a console interface.
 * It allows the user to search for trips, lines and stations.
 * 
 * @see Controller
 * @see ConsoleView
 */
public class ConsoleController implements Controller {
    /** The view of the application. */
    private final ConsoleView view;
    /** A boolean to know if the application is running. */
    private boolean running = false;

    /**
     * Creates a new console controller.
     * 
     * @param view The view of the application.
     */
    public ConsoleController(ConsoleView view) {
        this.view = view;
    }

    /**
     * Starts the application.
     * 
     * It displays a welcome message and a menu to the user.
     * The user can choose to search for a trip, a line, a station or to quit the application.
     */
    @Override
    public void start() {
        view.start();
        view.displayMessage("Bienvenue !");

        running = true;
        while (running) {
            view.displayMessage("\nMenu:");
            view.displayMessage("1: Search a trip");
            view.displayMessage("2: Search a line");
            view.displayMessage("3: Search a station");
            view.displayMessage("4: Quit");

            String choice = view.getInput("Choose an option: ");

            processChoice(choice);
        }
    }

    /**
     * Processes the choice of the user.
     * 
     * @param choice <br>The choice of the user.</br> 
     *               <br>It can be between 1 and 4. If the choice is invalid, an error message is displayed.</br>
     */
    private void processChoice(String choice) {
        switch(choice) {
            case "1": 
                searchTrip();
                break;
            case "2":
                searchLine();
                break;
            case "3":
                searchStation();
                break;
            case "4":
                running = false;
                view.displayMessage("Exiting the program");
                break;
            default:
                view.displayMessage("Invalid choice. Please try again.");
        }
    }

    /**
     * Searches for a trip.
     * 
     * It asks the user to enter the name of the start station and the end station.
     * It then displays the paths found between the two stations.
     */
    private void searchTrip() {
        Station startStation = selectStation("Enter the name of the start station: ", "Start station: ");
        if (startStation == null) {
            return;
        }
        Station endStation = selectStation("Enter the name of the end station: ", "End station: ");
        if (endStation == null) {
            return;
        }

        AstarPathFinder finder = new AstarPathFinder(startStation, endStation);
        ItineraryResult result = finder.findPath();

        view.displayMessage("Paths found: ");
        view.displayMessage(result.toString());
    }

    /**
     * Selects a station. Ask the user to enter the name of the station.
     * 
     * @param promptMessage The message to display to the user.
     * @param inputMessage The message to display to the user to ask for the name of the station.
     * @return The station selected by the user or null if no station is found.
     */
    private Station selectStation(String promptMessage, String inputMessage) {
        view.displayMessage(promptMessage);
        String name = view.getInput(inputMessage);

        List<Station> stations = StationDAO.findStation(name);
        if (stations.isEmpty()) {
            view.displayMessage("No station found");
            return null;
        }

        view.displayMessage("Stations found: ");
        for (int i = 0; i < stations.size(); i++) {
            view.displayMessage(i + ": " + stations.get(i));
        }

        String choice = view.getInput("Choose a station: ");
        return stations.get(Integer.parseInt(choice));
    }

    /**
     * Searches for a line.
     * 
     * It asks the user to enter the name of the line.
     * It then displays the stations of the line.
     */
    private void searchLine() {
        String name = view.getInput("Enter the name of the line: ");
        List<Line> lines = LineDAO.findLine(name);

        view.displayMessage("Lines found: ");
        for (int i = 0; i < lines.size(); i++) {
            view.displayMessage(i + ": " + lines.get(i));
        }

        String choice = view.getInput("Choose a line: ");
        Line line = lines.get(Integer.parseInt(choice));

        view.displayMessage("Line " + line.getName() + " :");
        for (Station station : line.getStations()) {
            view.displayMessage(station.toString());
        }
    }

    /**
     * Searches for a station.
     * 
     * It asks the user to enter the name of the station.
     * It then displays the stations found.
     */
    private void searchStation() {
        String name = view.getInput("Enter the name of the station: ");
        List<Station> stations = StationDAO.findStation(name);

        view.displayMessage("Stations found: ");
        for (int i = 0; i < stations.size(); i++) {
            view.displayMessage(i + ": " + stations.get(i));
        }
    }
}
