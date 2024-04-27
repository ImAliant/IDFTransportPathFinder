package fr.u_paris.gla.project.idfnetwork.view.button;

import javax.swing.JButton;

public abstract class MiniButton extends JButton {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 25;

    protected MiniButton(int x, int y) {
        super();
        
        setBounds(x, y, WIDTH, HEIGHT);

        addActionListener(e -> onClick());
    }

    /**
     * Method called when the button is clicked.
     */
    public abstract void onClick();
}
