package fr.u_paris.gla.crazytrip.gui.panel;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.button.ValidateLineDrawButton;
import fr.u_paris.gla.crazytrip.gui.combobox.SelectionLineComboBox;
import fr.u_paris.gla.crazytrip.gui.observer.LinePainterObserver;

public class SelectionPanel extends JPanel {
    private SelectionLineComboBox combobox;
    private ValidateLineDrawButton button;

    public SelectionPanel() {
        super();

        initComponents();
        addComponents();

        setOpaque(false);
    }

    private void initComponents() {
        combobox = new SelectionLineComboBox();
        button = new ValidateLineDrawButton(combobox);
    }

    private void addComponents() {
        add(combobox);
        add(button);
    }

    public void addLinePainterObserver(LinePainterObserver observer) {
        combobox.addObserver(observer);
    }

    public SelectionLineComboBox getCombobox() {
        return combobox;
    }

    public ValidateLineDrawButton getButton() {
        return button;
    }
} 
