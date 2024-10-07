package fr.u_paris.gla.crazytrip.gui.observer;

import java.util.List;

import fr.u_paris.gla.crazytrip.model.Line;

/**
 * Observer for the display of lines
 */
public interface DisplayLineObserver {
    /**
     * Update the lines to display
     * @param lines the lines to display
     */
    void update(List<Line> lines);
}
