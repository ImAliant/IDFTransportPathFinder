package fr.u_paris.gla.crazytrip.gui.panel;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.button.ClearLineButton;
import fr.u_paris.gla.crazytrip.gui.observer.ClearLineObserver;

/**
 * Panel used to clear the maps from the lines.
 * 
 * @see ClearLineButton
 */
public class ClearPanel extends JPanel {
    /** The clear button */
    private ClearLineButton button;

    /**
     * Constructor.
     */
    public ClearPanel() {
        super();

        initComponents();
        addComponents();

        setOpaque(false);
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
        button = new ClearLineButton();
    }

    /**
     * Add an observer to the button.
     * 
     * @param observer The observer to add.
     * 
     * @see ClearLineObserver
     */
    public void addObserver(ClearLineObserver observer) {
        button.addObserver(observer);
    }

    /**
     * Add the components to the panel.
     */
    private void addComponents() {
        add(button);
    }

    /**
     * Getter for the clear button.
     * 
     * @return The clear button.
     * 
     * @see ClearLineButton
     */
    public ClearLineButton getButton() {
        return button;
    }
}
