package fr.u_paris.gla.crazytrip.gui.button.transport_selection_button;

import java.util.List;

import fr.u_paris.gla.crazytrip.dao.LineDAO;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.line.RouteType;

public class RailButton extends TransportButton{
    private static final String ICON_PATH = "src/main/resources/stop_logo/rer_logo.png";
    private static final String TOOLTIP = "RER";

    public RailButton() {
        super(ICON_PATH, TOOLTIP);
    }

    @Override
    public void action() {
        List<Line> lines = LineDAO.findLineByType(RouteType.RAIL);
        notifyObservers(lines);
    }
}
