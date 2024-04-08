package fr.u_paris.gla.project.idfnetwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import fr.u_paris.gla.project.idfnetwork.factory.LineFactory;
import fr.u_paris.gla.project.io.TimeFormat;

/**
 * Load the network from the csv file created by IDFMNetworkExtractor
 */
public class NetworkLoader {
    // CSV file format
    private static final int LNAME_INDEX = 0;
    private static final int ROUTETYPE_INDEX = 2;
    private static final int COLOR_INDEX = 3;
    private static final int STOP_NAME_INDEX = 4;
    private static final int LONGLAT_INDEX = 5;
    private static final int NEXT_STOP_NAME_INDEX = 6;
    private static final int NEXT_LONGLAT_INDEX = 7;
    private static final int DURATION_INDEX = 8;
    private static final int DISTANCE_INDEX = 9;

    /**
     * Instance of the idf network
     */
    private static Network network = Network.getInstance();

    /**
     * Fields of the current line
     */
    private static String[] fields;
    
    private NetworkLoader() {
        // utility class
    }

    /**
     * Load the network from the given file
     * @param file csv file where all the data is stored
     */
    public static void load(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String csvDelimiter = ";";
            while ((line = reader.readLine()) != null) {
                fields = parseCSVLine(line, csvDelimiter);

                processLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Process the current line of the csv file.
     * Add the line, stops and paths to the network
     */
    private static void processLine() {
        String lname = fields[LNAME_INDEX].trim();
        String routetype = fields[ROUTETYPE_INDEX].trim();
        String color = fields[COLOR_INDEX].trim();
        
        Line currentLine = findOrCreateLine(lname, LineType.fromString(routetype), color);

        Stop startStop = processStop(LONGLAT_INDEX, STOP_NAME_INDEX);
        Stop endStop = processStop(NEXT_LONGLAT_INDEX, NEXT_STOP_NAME_INDEX);

        double distance = Double.parseDouble(fields[DISTANCE_INDEX].trim());
        int duration = TimeFormat.convertToSeconds(fields[DURATION_INDEX].trim());

        currentLine.addPath(startStop, endStop, distance, duration,currentLine);
        currentLine.addStop(startStop);
        startStop.addLine(currentLine);
    }

    /**
     * Find the line with the given name and route type in the network data or create it if it does not exist
     * @param lname
     * @param routetype
     * @param color
     * @return the line found or created
     */
    private static Line findOrCreateLine(String lname, LineType routetype, String color) {
        Line temp = network.findLine(lname, routetype);
        if (temp == null) {
            temp = LineFactory.createLine(routetype, lname, color);
            network.addLine(temp);
        }
        return temp;
    }

    /**
     * Add the stop to the network if it does not exist
     * @param longlatIndex
     * @param stopNameIndex
     * @return
     */
    private static Stop processStop(int longlatIndex, int stopNameIndex) {
        String stopname = fields[stopNameIndex].trim();
        String longlat = fields[longlatIndex].trim();
        double latitude = Double.parseDouble(longlat.split(",")[0]);
        double longitude = Double.parseDouble(longlat.split(",")[1]);
        Stop temp = network.findStop(stopname, longitude, latitude);
        if (temp == null) {
            temp = new Stop(stopname, longitude, latitude);
            network.addStop(temp);
        }
        return temp;
    }

    /**
     * Parse the csv line and remove quotes
     * @param line
     * @param delimiter
     * @return
     */
    private static String[] parseCSVLine(String line, String delimiter) {
        // set the delimiter and the regex to remove quotes
        String regex = "\"([^\"]*)\"";
        // split the line and remove quotes
        String[] fields = line.split(delimiter);
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].replaceAll(regex, "$1");
        }
        return fields;
    }
}
