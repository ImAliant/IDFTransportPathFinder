package fr.u_paris.gla.project.observer;

import java.util.List;

import fr.u_paris.gla.project.idfnetwork.line.Line;

public interface DisplayLineObserver {
    void update(List<Line> lines);
}