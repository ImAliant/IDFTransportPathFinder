package fr.u_paris.gla.crazytrip.gui.button.transport_selection_button;

import java.util.List;

import fr.u_paris.gla.crazytrip.dao.LineDAO;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.line.RouteType;

/**
 * Class representing a bus button in the line selection panel.
 * 
 * It is used to create a button that will be used to select a bus transport type.
 * 
 * @see TransportButton
 */
public class BusButton extends TransportButton {
    /** The path of the icon of the bus button */
    private static final String ICON_PATH = "src/main/resources/stop_logo/bus_logo.png";
    /** The text of the tooltip of the bus button */
    private static final String TOOLTIP = "Bus";

    /**
     * Constructor of the bus button.
     */
    public BusButton() {
        super(ICON_PATH, TOOLTIP);
    }

    /**
     * Action of the bus button.
     * 
     * It finds the bus lines in the network and notifies the observers.
     */
    @Override
    public void action() {
        List<Line> lines = LineDAO.findLineByType(RouteType.BUS);
        notifyObservers(lines);
    }
}
