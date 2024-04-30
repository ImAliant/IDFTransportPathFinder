package fr.u_paris.gla.project.idfnetwork.view.button.transport_button;

import java.util.List;

import fr.u_paris.gla.project.idfnetwork.Line;
import fr.u_paris.gla.project.idfnetwork.LineType;
import fr.u_paris.gla.project.idfnetwork.Network;

public class MetroButton extends TransportButton {
    private static final String ICON_PATH = "src/main/resources/fr/u_paris/gla/project/button_icon/paris_transit_icons/Paris_transit_icons_-_M%C3%A9tro.svg_002.png";
    private static final String TOOLTIP_TEXT = "Métro";

    public MetroButton() {
        super(ICON_PATH, TOOLTIP_TEXT);
    }

    @Override
    public void onClick() {
        List<Line> metroLines = Network.getLinesByType(LineType.METRO);
        notifyObservers(metroLines);
    }
}
