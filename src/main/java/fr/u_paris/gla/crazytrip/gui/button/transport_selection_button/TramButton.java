package fr.u_paris.gla.crazytrip.gui.button.transport_selection_button;

import java.util.List;

import fr.u_paris.gla.crazytrip.dao.LineDAO;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.line.RouteType;

/**
 * Class representing a tram button in the line selection panel.
 * 
 * It is used to create a button that will be used to select a tram transport type.
 * 
 * @see TransportButton
 */
public class TramButton extends TransportButton {
    /** The path of the icon of the tram button */
    private static final String ICON_PATH = "src/main/resources/stop_logo/tram_logo.png";
    /** The text of the tooltip of the tram button */
    private static final String TOOLTIP = "Tramway";

    /**
     * Constructor of the tram button.
     */
    public TramButton() {
        super(ICON_PATH, TOOLTIP);
    }

    /**
     * Action of the tram button.
     * 
     * It finds the tram lines in the network and notifies the observers.
     */
    @Override
    public void action() {
        List<Line> lines = LineDAO.findLineByType(RouteType.TRAMWAY);
        notifyObservers(lines);
    }
}
