package fr.u_paris.gla.project.idfnetwork.view.button.transportButton;

import java.util.List;

import fr.u_paris.gla.project.idfnetwork.Line;
import fr.u_paris.gla.project.idfnetwork.view.button.TransportButton;

public class BusButton extends TransportButton {
    public BusButton() {
        super("src/main/resources/fr/u_paris/gla/project/button_icon/paris_transit_icons/Paris_transit_icons_-_Bus.svg.png",
                50, 50, "Bus");
    }

    protected void onClick() {
        // TODO
    }

    @Override
    protected void showClickedLineType(List<Line> lineTypeLines) {
        // TODO
    }
}