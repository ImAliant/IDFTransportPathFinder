package fr.u_paris.gla.project;

import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

public class Maps extends RestrictedZoomJXMapViewer {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Default latitude of the map. Latitude of Paris.
     */
    private static final double IDF_LATITUDE = 48.85656189753147;
    /**
     * Default longitude of the map. Longitude of Paris.
     */
    private static final double IDF_LONGITUDE = 2.4002576758282745;
    /**
     * Default zoom of the map.
     */
    private static final int DEFAULT_ZOOM = 2;

    /**
     * Constructor of the maps.
     * 
     * @param width
     * @param height
     */
    public Maps(int width, int height) {
        super();

        init(width, height);
    }

    /**
     * Initialize the map.
     * 
     * @param width width of the panel
     * @param height height of the panel
     */
    private void init(int width, int height) {
        setSize(width, height);

        // Tile factory to get the map
        createTileFactory();

        // Define the default position and zoom
        setDefaultLocation();
    }

    private void createTileFactory() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        setTileFactory(tileFactory);
    }

    private void setDefaultLocation() {
        GeoPosition idf = new GeoPosition(IDF_LATITUDE, IDF_LONGITUDE);
        setZoom(DEFAULT_ZOOM);
        setCenterPosition(idf);
    }

    public void zoomIn() {
        adjustZoom(-1);
    }

    public void zoomOut() {
        adjustZoom(1);
    }

    private void adjustZoom(int factor) {
        setZoom(getZoom() + factor);
    }
}
