package fr.u_paris.gla.crazytrip.gui.observer;

import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.crazytrip.model.Node;

public interface SelectPositionObserver {
    void update(Node station);
    void update(GeoPosition position);
}
