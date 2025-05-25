package fr.u_paris.gla.crazytrip.gui.button.transport_selection_button;

import java.util.List;

import fr.u_paris.gla.crazytrip.dao.LineDAO;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.line.RouteType;

/**
 * Class representing a metro button in the line selection panel.
 * 
 * It is used to create a button that will be used to select a metro transport type.
 * 
 * @see TransportButton
 */
public class MetroButton extends TransportButton {
    /** The path of the icon of the metro button */
    private static final String ICON_PATH = "src/main/resources/stop_logo/metro_logo.png";
    /** The text of the tooltip of the metro button */
    private static final String TOOLTIP = "Metro";

    /**
     * Constructor of the metro button.
     */
    public MetroButton() {
        super(ICON_PATH, TOOLTIP);
    }

    /**
     * Action of the metro button.
     * 
     * It finds the metro lines in the network and notifies the observers.
     */
    @Override
    public void action() {
        List<Line> lines = LineDAO.findLineByType(RouteType.METRO);
        notifyObservers(lines);
    }
}
