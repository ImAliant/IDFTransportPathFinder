package fr.u_paris.gla.crazytrip.gui.panel;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.button.ClearLineButton;
import fr.u_paris.gla.crazytrip.gui.observer.ClearLineObserver;

public class ClearPanel extends JPanel {
    private ClearLineButton button;

    public ClearPanel() {
        super();

        initComponents();
        addComponents();

        setOpaque(false);
    }

    private void initComponents() {
        button = new ClearLineButton();
    }

    public void addObserver(ClearLineObserver observer) {
        button.addObserver(observer);
    }

    private void addComponents() {
        add(button);
    }

    public ClearLineButton getButton() {
        return button;
    }
}
