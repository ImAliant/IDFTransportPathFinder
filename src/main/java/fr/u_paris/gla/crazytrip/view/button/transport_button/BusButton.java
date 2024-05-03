package fr.u_paris.gla.crazytrip.view.button.transport_button;

import java.util.List;

import fr.u_paris.gla.crazytrip.idfnetwork.line.Line;
import fr.u_paris.gla.crazytrip.idfnetwork.line.LineType;

public class BusButton extends TransportButton {
    private static final String ICON_PATH = "src/main/resources/fr/u_paris/gla/project/button_icon/paris_transit_icons/Paris_transit_icons_-_Bus.svg.png";
    private static final String TOOLTIP_TEXT = "Bus";

    public BusButton() {
        super(ICON_PATH, TOOLTIP_TEXT);
    }

    @Override
    public void onClick() {
        List<Line> busLines = network.getLinesByType(LineType.BUS);
        notifyObservers(busLines);
    }
}
