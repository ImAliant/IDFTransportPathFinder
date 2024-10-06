package fr.u_paris.gla.crazytrip.gui.button;

import fr.u_paris.gla.crazytrip.gui.combobox.SelectionLineComboBox;
import fr.u_paris.gla.crazytrip.model.Line;

/**
 * Class representing a button to validate the line drawn in the LineSelectionPanel.
 * 
 * @see StyleButton
 * @see SelectionLineComboBox
 * @see fr.u_paris.gla.crazytrip.gui.panel.LineSelectionPanel
 */
public class ValidateLineDrawButton extends StyleButton {
    /** Text displayed by the button */
    private static final String TEXT = "Valider";
    /** Combo box to get the selected line */
    private SelectionLineComboBox combobox;

    /**
     * Constructor.
     * 
     * @param combobox the combo box
     * 
     * @see SelectionLineComboBox
     */
    public ValidateLineDrawButton(SelectionLineComboBox combobox) {
        super(TEXT);

        this.combobox = combobox;
    }

    /**
     * Notify the observers that the line drawn in the LineSelectionPanel has been validated.
     */
    @Override
    public void action() {
        Line line = combobox.getSelectedLine();
        if (line != null) {
            combobox.notifyObservers(line);
        }
    }
}
