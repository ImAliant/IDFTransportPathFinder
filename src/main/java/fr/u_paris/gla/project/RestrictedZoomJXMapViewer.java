package fr.u_paris.gla.project;

import org.jxmapviewer.JXMapViewer;

public class RestrictedZoomJXMapViewer extends JXMapViewer {
    private static final int MAX_ZOOM = 5;

    public RestrictedZoomJXMapViewer() {
        super();
    }

    @Override
    public void setZoom(int zoom) {
        if (zoom > MAX_ZOOM) {
            return;
        }

        super.setZoom(zoom);
    }
}
