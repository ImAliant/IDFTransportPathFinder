package fr.u_paris.gla.crazytrip.gui.observer;

import java.util.List;

import fr.u_paris.gla.crazytrip.model.Line;

public interface DisplayLineObserver {
    void update(List<Line> lines);
}
