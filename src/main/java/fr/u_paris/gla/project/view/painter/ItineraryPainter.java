package fr.u_paris.gla.project.view.painter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.List;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.project.algo.Itinerary;

import org.jxmapviewer.painter.Painter;


public class ItineraryPainter implements Painter<JXMapViewer>
{

    private boolean antiAlias = true;

    private Itinerary path;


    public ItineraryPainter(Itinerary track)
    {
        this.path = track;
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

        drawRoute(g, map);
    }

    private void drawRoute(Graphics2D g, JXMapViewer map)
    {
        int lastX = 0;
        int lastY = 0;

        boolean first = true;
        List<GeoPosition> track = path.getStops().stream().map(
                stop -> new GeoPosition(stop.getLatitude(), stop.getLongitude())
        ).toList();
        List<Color> colors = path.getLines().stream().map(
                line ->  Color.decode("#" + line.getColor())
        ).toList();
        GeoPosition gp;
        for (int i=0; i < track.size(); i++)

        {
            gp = track.get(i);
            // convert geo-coordinate to world bitmap pixel
            Point2D pt = map.getTileFactory().geoToPixel(gp, map.getZoom());

            if (first)
            {
                first = false;
            }
            else
            {   // ligne noir pour le contraste
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(4));
                g.drawLine(lastX, lastY, (int) pt.getX(), (int) pt.getY());

                //ligne colorée
                g.setColor(colors.get(i-1));
                g.setStroke(new BasicStroke(2));
                g.drawLine(lastX, lastY, (int) pt.getX(), (int) pt.getY());
            }

            lastX = (int) pt.getX();
            lastY = (int) pt.getY();
        }

    }
}
