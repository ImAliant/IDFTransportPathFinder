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

public class ResultItinerary extends JScrollPane {
    private StyleLabel result;

    public ResultItinerary() {
        super();

        initComponents();
        addComponents();
    }

    private void initComponents() {
        result = new StyleLabel("");

        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        setVisible(false);

        result.setHorizontalAlignment(SwingConstants.CENTER);
        result.setVerticalAlignment(SwingConstants.CENTER);
    }

    private void addComponents() {
        setViewportView(result);
    }

    public void setResult(ItineraryResult result) {
        if (result == null || result.getPaths().isEmpty()) {
            noItinerary();
        } else {
            buildString(result);
        }
    }

    private void noItinerary() {
        result.setText("Aucun itinéraire trouvé");
    }

    private void buildString(ItineraryResult result) {
        List<ResultRoute> routes = convertItineraryToRoute(result);
        StringBuilder sb = new StringBuilder("<html>");
        processRoutes(routes, sb);
        sb.append("</html>");

        this.result.setText(sb.toString());
        revalidate();
        setVisible(true);
    }  

    private void processRoutes(List<ResultRoute> routes, StringBuilder builder) {
        for (ResultRoute route: routes) {
            builder.append(String.format("<span style='color: %s;'>%s</span> : %s -> %s<br>", 
                ColorUtils.toHex(route.getColor()), route.getLineName(), route.getStart(), route.getEnd()));
        }
    }

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
