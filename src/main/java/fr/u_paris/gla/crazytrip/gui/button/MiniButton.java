package fr.u_paris.gla.crazytrip.gui.button;

import java.awt.Dimension;

import javax.swing.JButton;

import fr.u_paris.gla.crazytrip.gui.listener.MiniButtonListener;

public abstract class MiniButton extends JButton{
    /** Size of the button */
    protected static final int SIZE = 25;

    private final String tooltipText;

    protected MiniButton(String text) {
        super();
        setToolTipText(text);
        setPreferredSize(new Dimension(SIZE, SIZE));

        this.tooltipText = text;

        addMouseListener(new MiniButtonListener(this));
    }

    public abstract void action();

    public String getTooltipText() {
        return tooltipText;
    }
}
