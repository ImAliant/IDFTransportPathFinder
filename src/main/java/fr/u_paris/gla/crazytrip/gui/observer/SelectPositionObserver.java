package fr.u_paris.gla.crazytrip.gui.observer;

import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.crazytrip.model.Node;

/**
 * Observer for the selection of a position
 */
public interface SelectPositionObserver {
    /**
     * Update the selected station
     * @param station the selected station
     */
    void update(Node station);
    /**
     * Update the selected position
     * @param position the selected position
     */
    void update(GeoPosition position);
}
