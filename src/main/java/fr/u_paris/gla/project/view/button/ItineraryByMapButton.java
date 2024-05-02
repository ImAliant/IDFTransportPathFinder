
package fr.u_paris.gla.project.view.button;


import java.awt.Dimension;

public abstract class ItineraryByMapButton extends MiniButton {
    protected static final int WIDTH = 200;
    protected static final int HEIGHT = 10;

    protected boolean isEnabled = false;

    protected ItineraryByMapButton() {
        super("clicker sur le plan pour choisir un point");

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
