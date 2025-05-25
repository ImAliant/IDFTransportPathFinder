package fr.u_paris.gla.crazytrip.gui.scrollpane;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import fr.u_paris.gla.crazytrip.algorithm.ItineraryResult;
import fr.u_paris.gla.crazytrip.algorithm.Path;
import fr.u_paris.gla.crazytrip.gui.label.StyleLabel;
import fr.u_paris.gla.crazytrip.model.ResultRoute;
import fr.u_paris.gla.crazytrip.utils.ColorUtils;

/**
 * This class is a JScrollPane that displays the result of an itinerary search.
 * 
 * <p>It displays the different routes to take to go from a point A to a point B.
 * If no itinerary is found, it displays a message saying so.</p>
 * 
 * @see JScrollPane
 * @see StyleLabel
 */
public class ResultItinerary extends JScrollPane {
    /** The label that will display the result */
    private StyleLabel result;

    /**
     * Constructor.
     */
    public ResultItinerary() {
        super();

        initComponents();
        addComponents();
    }

    /**
     * Initializes the components.
     */
    private void initComponents() {
        result = new StyleLabel("");

        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        setVisible(false);

        result.setHorizontalAlignment(SwingConstants.CENTER);
        result.setVerticalAlignment(SwingConstants.CENTER);
    }

    /**
     * Adds the components to the JScrollPane.
     */
    private void addComponents() {
        setViewportView(result);
    }

    /**
     * Sets the result to display.
     * 
     * @param result The result to display.
     */
    public void setResult(ItineraryResult result) {
        if (result == null || result.getPaths().isEmpty()) {
            noItinerary();
        } else {
            buildString(result);
        }
    }

    /**
     * Displays a message saying that no itinerary was found.
     */
    private void noItinerary() {
        result.setText("Aucun itinéraire trouvé");
    }

    /**
     * Builds the string to display.
     * 
     * @param result The result to display.
     */
    private void buildString(ItineraryResult result) {
        List<ResultRoute> routes = convertItineraryToRoute(result);
        StringBuilder sb = new StringBuilder("<html>");
        processRoutes(routes, sb);
        sb.append("</html>");

        this.result.setText(sb.toString());
        revalidate();
        setVisible(true);
    }  

    /**
     * Processes the routes to display.
     * 
     * @param routes The routes to display.
     * @param builder The StringBuilder to build the string.
     */
    private void processRoutes(List<ResultRoute> routes, StringBuilder builder) {
        for (ResultRoute route: routes) {
            builder.append(String.format("<span style='color: %s;'>%s</span> : %s -> %s<br>", 
                ColorUtils.toHex(route.getColor()), route.getLineName(), route.getStart(), route.getEnd()));
        }
    }

    /**
     * Converts an itinerary to a list of ResultRoute.
     * 
     * @param result The itinerary to convert.
     * @return The list of ResultRoute.
     */
    private List<ResultRoute> convertItineraryToRoute(ItineraryResult result) {
        List<ResultRoute> routes = new ArrayList<>();
        List<Path> paths = result.getPaths();

        for (Path path : paths) {
            String start = path.getStart().getName();
            String end = path.getEnd().getName();
            Color color = path.getColorFromPath();
            String name;
            
            if (path.isWalk()) name = "Marche";
            else name = path.getLineKey().getName();

            routes.add(new ResultRoute(start, end, color, name));
        }

        return routes;
    }
}
