package fr.u_paris.gla.crazytrip.gui.observer;

import fr.u_paris.gla.crazytrip.model.Line;

/**
 * Observer used to paint a line
 */
public interface LinePainterObserver {
    /**
     * Paint a line
     * @param line the line to paint
     */
    void paintLine(Line line);
}
