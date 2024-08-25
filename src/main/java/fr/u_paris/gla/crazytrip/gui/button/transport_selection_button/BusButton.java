package fr.u_paris.gla.crazytrip.gui.button.transport_selection_button;

import java.util.List;

import fr.u_paris.gla.crazytrip.dao.LineDAO;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.line.RouteType;

public class BusButton extends TransportButton {
    private static final String ICON_PATH = "src/main/resources/stop_logo/bus_logo.png";
    private static final String TOOLTIP = "Bus";

    public BusButton() {
        super(ICON_PATH, TOOLTIP);
    }

    @Override
    public void action() {
        List<Line> lines = LineDAO.findLineByType(RouteType.BUS);
        notifyObservers(lines);
    }
}
