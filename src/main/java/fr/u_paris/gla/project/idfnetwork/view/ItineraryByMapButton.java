
package fr.u_paris.gla.project.idfnetwork.view;


import java.awt.Dimension;

import fr.u_paris.gla.project.idfnetwork.view.button.MiniButton;

public abstract class ItineraryByMapButton extends MiniButton {
    protected static final int WIDTH = 200;
    protected static final int HEIGHT = 10;

    protected boolean isEnabled = false;

    protected ItineraryByMapButton() {
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
