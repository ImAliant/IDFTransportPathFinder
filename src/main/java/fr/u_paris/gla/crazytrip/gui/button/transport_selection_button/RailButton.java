package fr.u_paris.gla.crazytrip.gui.button.transport_selection_button;

import java.util.List;

import fr.u_paris.gla.crazytrip.dao.LineDAO;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.line.RouteType;

/**
 * Class representing a rail button in the line selection panel.
 * 
 * It is used to create a button that will be used to select a rail transport type.
 * 
 * @see TransportButton
 */
public class RailButton extends TransportButton{
    /** The path of the icon of the rail button */
    private static final String ICON_PATH = "src/main/resources/stop_logo/rer_logo.png";
    /** The text of the tooltip of the rail button */
    private static final String TOOLTIP = "RER";

    /**
     * Constructor of the rail button.
     */
    public RailButton() {
        super(ICON_PATH, TOOLTIP);
    }

    /**
     * Action of the rail button.
     * 
     * It finds the rail lines in the network and notifies the observers.
     */
    @Override
    public void action() {
        List<Line> lines = LineDAO.findLineByType(RouteType.RAIL);
        notifyObservers(lines);
    }
}
