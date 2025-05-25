package fr.u_paris.gla.crazytrip.gui.panel;

/**
 * Used to select the departure position of a trip.
 * 
 * @see SelectPositionField
 */
public class DepartureField extends SelectPositionField {
    /** The label text of the field */
    private static final String LABEL_TEXT = "Départ";
    
    /**
     * Constructor.
     */
    public DepartureField() {
        super(LABEL_TEXT);
    }
}
