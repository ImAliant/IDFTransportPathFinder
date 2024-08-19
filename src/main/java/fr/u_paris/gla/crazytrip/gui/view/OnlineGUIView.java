package fr.u_paris.gla.crazytrip.gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.listener.ZoomInListener;
import fr.u_paris.gla.crazytrip.gui.listener.ZoomOutListener;
import fr.u_paris.gla.crazytrip.gui.maps.Maps;

public class OnlineGUIView extends JFrame implements View {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JPanel container;
    private JPanel buttonContainer;
    private Maps map;
    private JButton zoomIn;
    private JButton zoomOut;

    public OnlineGUIView(String title) {
        super(title);

        this.container = new JPanel();
        this.buttonContainer = new JPanel();
        this.map = new Maps();
        this.zoomIn = new JButton("Zoom In");
        this.zoomOut = new JButton("Zoom Out");
    }

    @Override
    public void start() {
        init();

        container.add(map, BorderLayout.CENTER);
        buttonContainer.add(zoomIn, BorderLayout.WEST);
        buttonContainer.add(zoomOut, BorderLayout.EAST);    
        add(container, BorderLayout.CENTER);
        add(buttonContainer, BorderLayout.SOUTH);

        pack();
    }
    
    private void init() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);

        container.setLayout(new BorderLayout());
        buttonContainer.setLayout(new BorderLayout());
    }

    public void addZoomInListener(ZoomInListener listener) {
        zoomIn.addActionListener(listener);
    }

    public void addZoomOutListener(ZoomOutListener listener) {
        zoomOut.addActionListener(listener);
    }

    public Maps getMap() {
        return map;
    }
}
