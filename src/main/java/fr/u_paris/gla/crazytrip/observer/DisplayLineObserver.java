package fr.u_paris.gla.crazytrip.observer;

import java.util.List;

import fr.u_paris.gla.crazytrip.idfnetwork.line.Line;

public interface DisplayLineObserver {
    void update(List<Line> lines);
}
