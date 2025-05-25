package fr.u_paris.gla.crazytrip.gui.observer;

/**
 * Observer for error on a path research
 */
public interface ErrorOnResearchObserver {
    /**
     * Method called when an error occurs during a path research
     * @param message the error message
     */
    void errorOnResearch(String message);   
}
