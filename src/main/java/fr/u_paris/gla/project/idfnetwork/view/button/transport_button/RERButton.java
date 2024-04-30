package fr.u_paris.gla.project.idfnetwork.view.button.transport_button;

import java.util.List;

import fr.u_paris.gla.project.idfnetwork.Line;
import fr.u_paris.gla.project.idfnetwork.LineType;
import fr.u_paris.gla.project.idfnetwork.Network;

public class RERButton extends TransportButton {
    private static final String ICON_PATH = "src/main/resources/fr/u_paris/gla/project/button_icon/paris_transit_icons/20221022173330!Paris_transit_icons_-_RER.svg.png";
    private static final String TOOLTIP_TEXT = "RER";

    public RERButton() {
        super(ICON_PATH, TOOLTIP_TEXT);
    }

    @Override
    protected void onClick() {
        List<Line> rerLines = Network.getLinesByType(LineType.RER);
        notifyObservers(rerLines);
    }
}
