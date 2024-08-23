package fr.u_paris.gla.crazytrip.controller;

import java.util.List;

import fr.u_paris.gla.crazytrip.algorithm.AstarPathFinder;
import fr.u_paris.gla.crazytrip.algorithm.ItineraryResult;
import fr.u_paris.gla.crazytrip.dao.LineDAO;
import fr.u_paris.gla.crazytrip.dao.StationDAO;
import fr.u_paris.gla.crazytrip.gui.view.ConsoleView;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Station;

public class ConsoleController implements Controller {
    private final ConsoleView view;

    private boolean running = false;

    public ConsoleController(ConsoleView view) {
        this.view = view;
    }

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

    private void searchStation() {
        String name = view.getInput("Enter the name of the station: ");
        List<Station> stations = StationDAO.findStation(name);

        view.displayMessage("Stations found: ");
        for (int i = 0; i < stations.size(); i++) {
            view.displayMessage(i + ": " + stations.get(i));
        }
    }
}
