package fr.u_paris.gla.project.utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class StyleButton {

    /** Button colors. */
    private static final Color BUTTON_COLOR = new Color(1, 121, 111);
    private static final Color BUTTON_COLOR_HOVER = new Color(1, 101, 91);

    public static void styleButton(JButton button) {
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BUTTON_COLOR_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_COLOR);
            }
        });
    }
}