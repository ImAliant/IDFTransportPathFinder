package fr.u_paris.gla.project;

import org.jxmapviewer.JXMapViewer;

public class MapViewer extends JXMapViewer{
    private int maxzoom;

    public MapViewer(int maxzoom) {
        super();

        this.maxzoom = maxzoom;
    }

    @Override
    public void setZoom(int zoom) {
        if (zoom > maxzoom) {
            return;
        }

        super.setZoom(zoom);
    }
}
