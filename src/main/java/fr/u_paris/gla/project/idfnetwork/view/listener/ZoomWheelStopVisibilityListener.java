package fr.u_paris.gla.project.idfnetwork.view.listener;

import java.awt.event.MouseWheelEvent;

import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;

import fr.u_paris.gla.project.Maps;

public class ZoomWheelStopVisibilityListener extends ZoomMouseWheelListenerCursor {
    private Maps map;

    public ZoomWheelStopVisibilityListener(Maps map) {
        super(map);

        this.map = map;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent evt) {
        super.mouseWheelMoved(evt);
         
        int zoom = map.getZoom();
        map.getWaypoints().forEach(waypoint -> waypoint.getButton().updateVisibility(zoom));
    }
}
