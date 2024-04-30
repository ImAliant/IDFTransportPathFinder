package fr.u_paris.gla.project.idfnetwork.view.button.transport_button;

import java.util.List;

import fr.u_paris.gla.project.idfnetwork.Line;
import fr.u_paris.gla.project.idfnetwork.LineType;
import fr.u_paris.gla.project.idfnetwork.Network;

public class TramButton extends TransportButton {
    private static final String ICON_PATH = "src/main/resources/fr/u_paris/gla/project/button_icon/paris_transit_icons/Paris_transit_icons_-_Tram.svg.png";
    private static final String TOOLTIP_TEXT = "Tram";

    public TramButton() {
        super(ICON_PATH, TOOLTIP_TEXT);
    }

    @Override
    public void onClick() {
        List<Line> tramLines = Network.getLinesByType(LineType.TRAMWAY);
        notifyObservers(tramLines);
    }
}
