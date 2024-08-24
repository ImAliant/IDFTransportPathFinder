package fr.u_paris.gla.crazytrip.gui.button;

import java.awt.Dimension;

public class SearchByPositionButton extends MiniButton {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 10;

    public SearchByPositionButton() {
        super("Position by click");

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void action() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'action'");
    }
}
