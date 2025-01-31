package fr.u_paris.gla.crazytrip.gui.maps;

import fr.u_paris.gla.crazytrip.gui.observer.ZoomInObserver;
import fr.u_paris.gla.crazytrip.gui.observer.ZoomOutObserver;

/**
 * <p>Class representing the zoom handler for the map.</p>
 * 
 * <p>It is used to handle the zoom in and zoom out actions on the map.</p>
 * 
 * @see ZoomInObserver
 * @see ZoomOutObserver
 */
public class ZoomHandler implements ZoomInObserver, ZoomOutObserver {
    /**  Default zoom. */
    private static final int DEFAULT_ZOOM = 2;
    /** Maximum zoom. */
    public static final int MAX_ZOOM = 7;
    /** The map */
    private final Maps map;

    /**
     * Constructor for the zoom handler.
     * 
     * @param map The map to handle the zoom on.
     * 
     * @see Maps
     */
    public ZoomHandler(Maps map) {
        this.map = map;

        init();
    }

    /**
     * Initialize the zoom handler.
     */
    private void init() {
        map.setZoom(DEFAULT_ZOOM);
    }
    /**
     * Adjust the zoom of the map.
     * 
     * @param factor The factor to adjust the zoom by.
     */
    private void adjustZoom(int factor) {
        map.setZoom(map.getZoom() + factor);

        map.updateStationsVisibility();
        map.repaint();
    }
    /**
     * Zoom in the map.
     * 
     * <p>It decreases the zoom of the map by 1.</p>
     * <p>If the zoom is already at the maximum zoom, it does nothing.</p>
     */
    @Override
    public void zoomIn() {
        if (map.getZoom() < 0) return;

        adjustZoom(-1);
    }
    /**
     * Zoom out the map.
     * 
     * <p>It increases the zoom of the map by 1.</p>
     * <p>If the zoom is already at the minimum zoom, it does nothing.</p>
     */
    @Override
    public void zoomOut() {
        if (map.getZoom() > MAX_ZOOM) return;

        adjustZoom(1);
    }
}
