package fr.u_paris.gla.crazytrip.gui.observer;

import fr.u_paris.gla.crazytrip.algorithm.ItineraryResult;

public interface PathResultObserver {
    void showResult(ItineraryResult result);
}
