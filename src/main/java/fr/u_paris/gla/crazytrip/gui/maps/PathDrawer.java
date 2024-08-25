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
import fr.u_paris.gla.crazytrip.model.Node;
import fr.u_paris.gla.crazytrip.model.Route;
import fr.u_paris.gla.crazytrip.model.SegmentTransport;
import fr.u_paris.gla.crazytrip.model.key.LineKey;

public class PathDrawer implements LinePainterObserver, PathResultObserver, ClearLineObserver {
    private Maps map;

    public PathDrawer(Maps map) {
        this.map = map;
    }

    @Override
    public void paintLine(Line line) {
        List<Route> routes = convertLineToRoute(line);
        
        setPainter(routes);
    }

    @Override
    public void showResult(ItineraryResult result) {
        List<Route> routes = convertItineraryToRoute(result);

        setPainter(routes);
    }

    private void setPainter(final List<Route> routes) {
        RoutePainter painter = new RoutePainter(routes);

        List<Painter<JXMapViewer>> painters = new ArrayList<>();
        painters.add(map.getWayPointPainter());
        painters.add(painter);
        map.setOverlayPainter(new CompoundPainter<>(painters));
        map.repaint();
    }

    private List<Route> convertLineToRoute(Line line) {
        List<Route> routes = new ArrayList<>();
        Set<SegmentTransport> segments = LineDAO.findAllSegmentsOfLine(line);

        segments.forEach(segment -> {
            Node start = segment.getStartPoint();
            Node end = segment.getEndPoint();
            Coordinates startCoords = start.getCoordinates();
            Coordinates endCoords = end.getCoordinates();
            Color color = decodeColor(line.getColor());

            routes.add(new Route(new GeoPosition(startCoords.getLatitude(), startCoords.getLongitude()),
                    new GeoPosition(endCoords.getLatitude(), endCoords.getLongitude()), color));
        });

        return routes;
    }

    private List<Route> convertItineraryToRoute(ItineraryResult result) {
        List<Route> routes = new ArrayList<>();
        List<Path> paths = result.getPaths();

        for (Path path: paths) {
            Node start = path.getStart();
            Node end = path.getEnd();
            Coordinates startCoords = start.getCoordinates();
            Coordinates endCoords = end.getCoordinates();
            Color color = getColorFromPath(path);

            routes.add(new Route(new GeoPosition(startCoords.getLatitude(), startCoords.getLongitude()),
                    new GeoPosition(endCoords.getLatitude(), endCoords.getLongitude()), color));
        }

        return routes;
    }

    private Color getColorFromPath(Path path) {
        if (path.isWalk()) {
            return Color.BLACK;
        }

        LineKey lineKey = path.getLineKey();
        Line line = LineDAO.findLineByKey(lineKey);
        return decodeColor(line.getColor());
    }

    private Color decodeColor(String color) {
        return Color.decode("#" + color);
    }

    @Override
    public void clearLine() {
        map.setOverlayPainter(map.getWayPointPainter());
        map.repaint();
    }
}
