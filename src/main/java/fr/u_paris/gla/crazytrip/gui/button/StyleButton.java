package fr.u_paris.gla.crazytrip.gui.button;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import fr.u_paris.gla.crazytrip.gui.listener.StyleButtonListener;

public abstract class StyleButton extends JButton {
    /** Button colors */
    private static final Color BUTTON_COLOR = new Color(1, 121, 111);
    private static final Color BUTTON_HOVER_COLOR = new Color(1, 101, 91);
    private static final Color FOREGROUND_COLOR = Color.WHITE;

    protected StyleButton(String text) {
        super(text);

        setBackground(BUTTON_COLOR);
        setForeground(FOREGROUND_COLOR);
        setBorder(BorderFactory.createRaisedBevelBorder());
        
        addMouseListener(new StyleButtonListener(this, BUTTON_COLOR, BUTTON_HOVER_COLOR));
    }

    public abstract void action();
}
