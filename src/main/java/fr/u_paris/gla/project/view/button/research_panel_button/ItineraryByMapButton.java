
package fr.u_paris.gla.project.view.button.research_panel_button;


import java.awt.Dimension;

import fr.u_paris.gla.project.view.button.MiniButton;

public abstract class ItineraryByMapButton extends MiniButton {
    protected static final int WIDTH = 200;
    protected static final int HEIGHT = 10;

    protected ItineraryByMapButton() {
        super("clicker sur le plan pour choisir un point");

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}
