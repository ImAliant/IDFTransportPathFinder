
package fr.u_paris.gla.project.idfnetwork.view;


import fr.u_paris.gla.project.idfnetwork.view.button.MiniButton;

public abstract class ItineraryByMapButton extends MiniButton {
    

    public ItineraryByMapButton(int x, int y) {
        super(x, y);
    }

    protected boolean isEnabled = false;


    public boolean isEnabled() {
        return isEnabled;
    }

}
