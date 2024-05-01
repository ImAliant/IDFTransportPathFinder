package fr.u_paris.gla.project.view.painter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.project.idfnetwork.TravelPath;

import org.jxmapviewer.painter.Painter;


public class RoutePainter implements Painter<JXMapViewer>
{
    private Color color = Color.RED;
    private boolean antiAlias = true;

    private List<TravelPath> track;

    public RoutePainter(List<TravelPath> track,Color color)
    {
        this.track = new ArrayList<>(track);
        this.color = color;
    }
    public RoutePainter(List<TravelPath> track)
    {
        this.track = new ArrayList<>(track);
    }


    @Override
    public void paint(Graphics2D g, JXMapViewer map, int w, int h)
    {
        g = (Graphics2D) g.create();

        // convert from viewport to world bitmap
        Rectangle rect = map.getViewportBounds();
        g.translate(-rect.x, -rect.y);

        if (antiAlias)
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // do the drawing
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(4));

        drawRoute(g, map);

        // do the drawing again
        g.setColor(color);
        g.setStroke(new BasicStroke(2));

        drawRoute(g, map);
    }

    private void drawRoute(Graphics2D g, JXMapViewer map)
    {
        boolean first = true;

        for (TravelPath path : track)
        {
            GeoPosition gp = new  GeoPosition(path.getStart().getLatitude(), path.getStart().getLongitude());
            Point2D pt = map.getTileFactory().geoToPixel(gp, map.getZoom());
            GeoPosition gp2 = new  GeoPosition(path.getEnd().getLatitude(), path.getEnd().getLongitude());
            Point2D pt2 = map.getTileFactory().geoToPixel(gp2, map.getZoom());

            if (first)
            {
                first = false;
            }
            else
            {
                g.drawLine((int) pt.getX(), (int) pt.getY(), (int )pt2.getX(),(int) pt2.getY());
            }
        }
    }
}