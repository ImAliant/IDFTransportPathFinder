package fr.u_paris.gla.crazytrip.gui.maps.waypoint;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import javax.swing.JLabel;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

public class StationRender extends WaypointPainter<StationWaypoint> {
    public StationRender() {
        super();
    }
    
    @Override
    protected void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {
        for (StationWaypoint wp: getWaypoints()) {
            Point2D p = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());
            Rectangle rec = map.getViewportBounds();
            int x = (int) (p.getX() - rec.getX());
            int y = (int) (p.getY() - rec.getY());
            JLabel cmd = wp.getButton();
            cmd.setLocation(x - cmd.getWidth() / 2, y - cmd.getHeight());
        }
    }
}
