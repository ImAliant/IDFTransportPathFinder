package fr.u_paris.gla.crazytrip.view.panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.utils.StyleButton;
import fr.u_paris.gla.crazytrip.view.combobox.DisplayLineComboBox;

/**
 * Panel containing the combo box and the validate button.
 * 
 * @see JPanel
 * 
 * @author DIAMANT Alexandre
 */
public class SelectionLineAndValidatePanel extends JPanel {
    /** Text of the validate button. */
    private static final String TEXT = "Valider";

    /** Combo box containing the lines. */
    private DisplayLineComboBox comboBox;
    /** Button to validate the selection. */
    private JButton validate;

    /**
     * Constructor of the selection line and validate panel.
     */
    public SelectionLineAndValidatePanel() {
        super();

        comboBox = new DisplayLineComboBox();
        validate = new JButton(TEXT);
        StyleButton.styleButton(validate);

        add(comboBox);
        add(validate);

        // When the button is clicked, the selected line is sent to the observers
        validate.addActionListener(e -> {
            if (comboBox.getSelectedLine() != null) {
                comboBox.notifyObservers(comboBox.getSelectedLine());
            }
        });
    }

    /**
     * Getter of the combo box.
     * 
     * @return the combo box
     */
    public DisplayLineComboBox getComboBox() {
        return comboBox;
    }
}
