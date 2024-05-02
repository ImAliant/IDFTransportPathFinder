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

/**
 * ItineraryPainter draws a color-coded route on a map based on a series of geographical positions.
 * Each segment of the route is drawn with a unique color.
 *
 * @author Jean Paul
 * @author Youcef Baleh
 */
public class ItineraryPainter implements Painter<JXMapViewer>
{

    private boolean antiAlias = true;

    private Itinerary path;

    /**
     * Constructs an ItineraryPainter with a specified itinerary.
     *
     * @param track The itinerary to be rendered.
     */
    public ItineraryPainter(Itinerary track)
    {
        this.path = track;
    }

    /**
     * Performs the painting of the itinerary on the map. This method handles graphical settings and translates
     * geo-coordinates to pixel coordinates for drawing.
     *
     * @param g the Graphics2D object to draw on
     * @param map the JXMapViewer on which to paint
     * @param w the width of the area to paint
     * @param h the height of the area to paint
     */
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
     /**
     * Draws the actual route on the map using the itinerary data. Each segment is drawn twice: once with a thick black
     * stroke for contrast, and once with a thinner colored stroke corresponding to the route segment's line color.
     *
     * @param g the Graphics2D object to draw on
     * @param map the JXMapViewer on which to paint
     */
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
            {   // Blach color for contrast
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(4));
                g.drawLine(lastX, lastY, (int) pt.getX(), (int) pt.getY());

                // Colored line
                g.setColor(colors.get(i-1));
                g.setStroke(new BasicStroke(2));
                g.drawLine(lastX, lastY, (int) pt.getX(), (int) pt.getY());
            }

            lastX = (int) pt.getX();
            lastY = (int) pt.getY();
        }

    }
}
