package fr.u_paris.gla.project.idfnetwork.view.button;

import javax.swing.JButton;

public class MiniButton extends JButton {
    protected static final int WIDTH = 25;
    protected static final int HEIGHT = 25;

    protected MiniButton(int x, int y) {
        super();
        
        setBounds(x, y, WIDTH, HEIGHT);
    }
}
