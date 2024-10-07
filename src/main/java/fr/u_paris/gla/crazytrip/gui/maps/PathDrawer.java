package fr.u_paris.gla.crazytrip.gui.maps;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.crazytrip.algorithm.ItineraryResult;
import fr.u_paris.gla.crazytrip.algorithm.Path;
import fr.u_paris.gla.crazytrip.dao.LineDAO;
import fr.u_paris.gla.crazytrip.gui.maps.painter.RoutePainter;
import fr.u_paris.gla.crazytrip.gui.observer.ClearLineObserver;
import fr.u_paris.gla.crazytrip.gui.observer.LinePainterObserver;
import fr.u_paris.gla.crazytrip.gui.observer.PathResultObserver;
import fr.u_paris.gla.crazytrip.model.Coordinates;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.MapsRoute;
import fr.u_paris.gla.crazytrip.model.Node;
import fr.u_paris.gla.crazytrip.model.SegmentTransport;
import fr.u_paris.gla.crazytrip.utils.ColorUtils;

/**
 * <p>Class representing a drawer to paint paths on the map.</p>
 * 
 * @see LinePainterObserver
 * @see PathResultObserver
 * @see ClearLineObserver
 */
public class PathDrawer implements LinePainterObserver, PathResultObserver, ClearLineObserver {
    /** The map on which to draw the paths. */
    private Maps map;

    /**
     * Constructor for the path drawer.
     * 
     * @param map The map on which to draw the paths.
     */
    public PathDrawer(Maps map) {
        this.map = map;
    }

    /**
     * Paints a line on the map.
     * 
     * @param line The line to paint.
     */
    @Override
    public void paintLine(Line line) {
        List<MapsRoute> routes = convertLineToRoute(line);
        
        setPainter(routes);
    }

    /**
     * Paints an itinerary on the map.
     * 
     * @param result The result of the itinerary to paint.
     */
    @Override
    public void showResult(ItineraryResult result) {
        List<MapsRoute> routes = convertItineraryToRoute(result);

        setPainter(routes);
    }

    /**
     * Sets the painter of the map.
     * 
     * @param routes The routes to paint.
     */
    private void setPainter(final List<MapsRoute> routes) {
        RoutePainter painter = new RoutePainter(routes);

        List<Painter<JXMapViewer>> painters = new ArrayList<>();
        painters.add(map.getWayPointPainter());
        painters.add(painter);
        map.setOverlayPainter(new CompoundPainter<>(painters));
        map.repaint();
    }

    /**
     * Converts a line to a list of routes.
     * 
     * @param line The line to convert.
     * 
     * @return The list of routes.
     */
    private List<MapsRoute> convertLineToRoute(Line line) {
        List<MapsRoute> routes = new ArrayList<>();
        Set<SegmentTransport> segments = LineDAO.findAllSegmentsOfLine(line);

        segments.forEach(segment -> {
            Node start = segment.getStartPoint();
            Node end = segment.getEndPoint();
            Coordinates startCoords = start.getCoordinates();
            Coordinates endCoords = end.getCoordinates();
            Color color = ColorUtils.decodeColor(line.getColor());

            routes.add(new MapsRoute(new GeoPosition(startCoords.getLatitude(), startCoords.getLongitude()),
                    new GeoPosition(endCoords.getLatitude(), endCoords.getLongitude()), color));
        });

        return routes;
    }

    /**
     * Converts an itinerary to a list of routes.
     * 
     * @param result The result of the itinerary to convert.
     * 
     * @return The list of routes.
     */
    private List<MapsRoute> convertItineraryToRoute(ItineraryResult result) {
        if (result == null) return new ArrayList<>();

        List<MapsRoute> routes = new ArrayList<>();
        List<Path> paths = result.getPaths();

        for (Path path: paths) {
            Node start = path.getStart();
            Node end = path.getEnd();
            Coordinates startCoords = start.getCoordinates();
            Coordinates endCoords = end.getCoordinates();
            Color color = path.getColorFromPath();

            routes.add(new MapsRoute(new GeoPosition(startCoords.getLatitude(), startCoords.getLongitude()),
                    new GeoPosition(endCoords.getLatitude(), endCoords.getLongitude()), color));
        }

        return routes;
    }

    /**
     * Clears the line on the map.
     */
    @Override
    public void clearLine() {
        map.setOverlayPainter(map.getWayPointPainter());
        map.repaint();
    }
}
