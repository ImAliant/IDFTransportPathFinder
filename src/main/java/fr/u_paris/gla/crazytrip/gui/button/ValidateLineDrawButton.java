package fr.u_paris.gla.crazytrip.gui.button;

import fr.u_paris.gla.crazytrip.gui.combobox.SelectionLineComboBox;
import fr.u_paris.gla.crazytrip.model.Line;

public class ValidateLineDrawButton extends StyleButton {
    private static final String TEXT = "Valider";

    private SelectionLineComboBox combobox;

    public ValidateLineDrawButton(SelectionLineComboBox combobox) {
        super(TEXT);

        this.combobox = combobox;
    }

    @Override
    public void action() {
        Line line = combobox.getSelectedLine();
        if (line != null) {
            combobox.notifyObservers(line);
        }
    }
}
