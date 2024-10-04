package fr.u_paris.gla.crazytrip.gui.button;

import javax.swing.SwingUtilities;

import fr.u_paris.gla.crazytrip.gui.panel.ItineraryResultPanel;

public class CloseButton extends StyleButton {
    private static final String TEXT = "Fermer";

    private ItineraryResultPanel panel;

    public CloseButton(ItineraryResultPanel panel) {
        super(TEXT);

        this.panel = panel;
    }

    @Override
    public void action() {
        SwingUtilities.invokeLater(() -> panel.setVisible(false));
    }
}
