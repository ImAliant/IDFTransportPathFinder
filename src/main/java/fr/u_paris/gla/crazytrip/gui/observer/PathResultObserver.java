package fr.u_paris.gla.crazytrip.gui.observer;

import fr.u_paris.gla.crazytrip.algorithm.ItineraryResult;

/**
 * This interface is used to show the result of a path search.
 */
public interface PathResultObserver {
    /**
     * Show the result of a path search.
     * @param result the result of the path search.
     */
    void showResult(ItineraryResult result);
}
