package fr.u_paris.gla.crazytrip.gui.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import fr.u_paris.gla.crazytrip.gui.maps.Maps;

public class OnlineGUIView extends JFrame implements View {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Maps map;

    public OnlineGUIView(String title) {
        super(title);

        this.map = new Maps();
    }

    @Override
    public void start() {
        init();

        add(map);

        pack();
    }
    
    private void init() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
    }

    public Maps getMap() {
        return map;
    }
}
