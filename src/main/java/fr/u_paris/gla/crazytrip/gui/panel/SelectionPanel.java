package fr.u_paris.gla.crazytrip.gui.panel;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.button.ValidateLineDrawButton;
import fr.u_paris.gla.crazytrip.gui.combobox.SelectionLineComboBox;
import fr.u_paris.gla.crazytrip.gui.observer.LinePainterObserver;

/**
 * Panel used to select a line to paint.
 * 
 * <p>The combobox in the center will show the all the lines of the transportation mode selected. The button is here to confirm the selection.</p>
 * 
 * @see JPanel
 * @see SelectionLineComboBox
 * @see ValidateLineDrawButton
 */
public class SelectionPanel extends JPanel {
    /** Combobox to select a line to paint */
    private SelectionLineComboBox combobox;
    /** Button to confirm the selection */
    private ValidateLineDrawButton button;

    /**
     * Constructor
     */
    public SelectionPanel() {
        super();

        initComponents();
        addComponents();

        setOpaque(false);
    }

    /** Intialize the components */
    private void initComponents() {
        combobox = new SelectionLineComboBox();
        button = new ValidateLineDrawButton(combobox);
    }

    /** Add the components of the panel */
    private void addComponents() {
        add(combobox);
        add(button);
    }

    /**
     * Add an observer to the combo box
     * 
     * @param observer the observer to add. The observer will know the confirmed line to paint
     * 
     * @see LinePainterObserver
     */
    public void addLinePainterObserver(LinePainterObserver observer) {
        combobox.addObserver(observer);
    }

    /**
     * Getter for the selection line combobox
     * 
     * @return the selection line combobox
     * @see SelectionLineConboBox
     */
    public SelectionLineComboBox getCombobox() {
        return combobox;
    }

    /**
     * Getter for the validate button
     * 
     * @return the button
     * @see ValidateLineDrawButton
     */
    public ValidateLineDrawButton getButton() {
        return button;
    }
} 
