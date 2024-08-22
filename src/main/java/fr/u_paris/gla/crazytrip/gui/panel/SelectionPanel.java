package fr.u_paris.gla.crazytrip.gui.panel;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.button.ValidateButton;
import fr.u_paris.gla.crazytrip.gui.combobox.SelectionLineComboBox;

public class SelectionPanel extends JPanel {
    private SelectionLineComboBox combobox;
    private ValidateButton button;

    public SelectionPanel() {
        super();

        initComponents();
        addComponents();
    }

    private void initComponents() {
        combobox = new SelectionLineComboBox();
        button = new ValidateButton();
    }

    private void addComponents() {
        add(combobox);
        add(button);
    }

    public SelectionLineComboBox getCombobox() {
        return combobox;
    }

    public ValidateButton getButton() {
        return button;
    }
} 
