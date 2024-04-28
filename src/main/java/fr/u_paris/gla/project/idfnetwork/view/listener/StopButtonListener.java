package fr.u_paris.gla.project.idfnetwork.view.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;

/**
 * Listener for the stop button.
 */
public class StopButtonListener extends MouseAdapter {
    /** The stop associated with the button. */
    private Stop stop;

    /**
     * Create a new listener for the stop button.
     *
     * @param stop The stop associated with the button.
     */
    public StopButtonListener(Stop stop) {
        this.stop = stop;
    }

    /**
     * Show the caracteristics of the stop when clicked.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Click on " + stop);
    }
}
