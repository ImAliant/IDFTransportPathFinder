package fr.u_paris.gla.project.view.panel;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.u_paris.gla.project.view.combobox.DisplayLineComboBox;

public class SelectionLineAndValidatePanel extends JPanel {
    private static final String TEXT = "Valider";

    private DisplayLineComboBox comboBox;
    private JButton validate;

    public SelectionLineAndValidatePanel() {
        super();

        comboBox = new DisplayLineComboBox();
        validate = new JButton(TEXT);

        add(comboBox);
        add(validate);

        validate.addActionListener(e -> {
            if (comboBox.getSelectedLine() != null) {
                comboBox.notifyObservers(comboBox.getSelectedLine());
            }
        });
    }

    public DisplayLineComboBox getComboBox() {
        return comboBox;
    }
}
