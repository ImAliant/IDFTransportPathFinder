package fr.u_paris.gla.crazytrip.gui.maps.painter;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.List;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;

import fr.u_paris.gla.crazytrip.model.MapsRoute;

/**
 * Painter for drawing routes on the map.
 * 
 * @see MapsRoute
 * @see Painter
 * @see JXMapViewer
 */
public class RoutePainter implements Painter<JXMapViewer> {
    /** List of routes to draw */
    private List<MapsRoute> routes;
    /** Whether to antialias the drawing */
    private boolean antialias = true;

    /**
     * Constructor.
     * 
     * @param routes the list of routes to draw
     * 
     * @see MapsRoute
     */
    public RoutePainter(List<MapsRoute> routes) {
        this.routes = routes;
    }

    @Override
    public void paint(Graphics2D g, JXMapViewer map, int w, int h) {
        g = (Graphics2D) g.create();

        Rectangle rect = map.getViewportBounds();
        g.translate(-rect.x, -rect.y);

        if (antialias) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        g.setStroke(new BasicStroke(4));

        drawRoutes(g, map);
    }

    /**
     * Draw the routes on the map.
     * 
     * @param g the graphics
     * @param map the map
     */
    private void drawRoutes(Graphics2D g, JXMapViewer map) {
        for (MapsRoute route : routes) {
            g.setColor(route.getColor());

            Point2D pt1 = map.getTileFactory().geoToPixel(route.getStart(), map.getZoom());
            Point2D pt2 = map.getTileFactory().geoToPixel(route.getEnd(), map.getZoom());

            g.drawLine((int) pt1.getX(), (int) pt1.getY(), (int) pt2.getX(), (int) pt2.getY());
        }
    }
}
