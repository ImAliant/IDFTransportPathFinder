package fr.u_paris.gla.project.idfnetwork.view.button.transportButton;

import java.util.List;

import fr.u_paris.gla.project.idfnetwork.Line;
import fr.u_paris.gla.project.idfnetwork.LineType;
import fr.u_paris.gla.project.idfnetwork.view.button.TransportButton;

public class TramButton extends TransportButton {
    public TramButton() {
        super("src/main/resources/fr/u_paris/gla/project/button_icon/paris_transit_icons/Paris_transit_icons_-_Tram.svg.png",
                50, 50, "Tram");
    }

    protected void onClick() {
        showLines(LineType.TRAMWAY);
    }

    @Override
    protected void showClickedLineType(List<Line> lineTypeLines) {
        // TODO
    }
}