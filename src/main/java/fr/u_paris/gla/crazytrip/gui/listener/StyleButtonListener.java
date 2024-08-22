package fr.u_paris.gla.crazytrip.gui.listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.u_paris.gla.crazytrip.gui.button.StyleButton;

public class StyleButtonListener extends MouseAdapter {
    private final StyleButton button;
    private final Color defaultColor;
    private final Color hoverColor;

    public StyleButtonListener(StyleButton button, Color defaultColor, Color hoverColor) {
        this.button = button;
        this.defaultColor = defaultColor;
        this.hoverColor = hoverColor;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        button.setBackground(hoverColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        button.setBackground(defaultColor);
    }
}
