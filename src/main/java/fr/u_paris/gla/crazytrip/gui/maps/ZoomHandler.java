package fr.u_paris.gla.crazytrip.gui.maps;

import fr.u_paris.gla.crazytrip.gui.observer.ZoomInObserver;
import fr.u_paris.gla.crazytrip.gui.observer.ZoomOutObserver;

public class ZoomHandler implements ZoomInObserver, ZoomOutObserver {
    /**  Default zoom. */
    private static final int DEFAULT_ZOOM = 2;
    /** Maximum zoom. */
    public static final int MAX_ZOOM = 7;

    private final Maps map;

    public ZoomHandler(Maps map) {
        this.map = map;

        init();
    }

    private void init() {
        map.setZoom(DEFAULT_ZOOM);
    }

    private void adjustZoom(int factor) {
        map.setZoom(map.getZoom() + factor);

        map.updateStationsVisibility();
        map.repaint();
    }

    @Override
    public void zoomIn() {
        if (map.getZoom() < 0) return;

        adjustZoom(-1);
    }

    @Override
    public void zoomOut() {
        if (map.getZoom() > MAX_ZOOM) return;

        adjustZoom(1);
    }
}
