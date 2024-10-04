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

public class RoutePainter implements Painter<JXMapViewer> {
    private List<MapsRoute> routes;
    private boolean antialias = true;

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

    private void drawRoutes(Graphics2D g, JXMapViewer map) {
        for (MapsRoute route : routes) {
            g.setColor(route.getColor());

            Point2D pt1 = map.getTileFactory().geoToPixel(route.getStart(), map.getZoom());
            Point2D pt2 = map.getTileFactory().geoToPixel(route.getEnd(), map.getZoom());

            g.drawLine((int) pt1.getX(), (int) pt1.getY(), (int) pt2.getX(), (int) pt2.getY());
        }
    }
}
