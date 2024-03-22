package fr.u_paris.gla.project;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.*;

import java.time.LocalDateTime;

import javax.swing.*;
import javax.swing.event.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private Maps map;

    private JButton zoomIn;

    private JButton zoomOut;

    public AppWindow(String title) {
        super();
        this.map = new Maps(WIDTH, HEIGHT);
        init(title);
    }

    private void init(String title) {
        initFrame(title);

        JPanel container = new JPanel();
        addMapAndButtons(container);

    }

    private void addMapAndButtons(JPanel container) {
        /**
         * Container of the map and the buttons.
         */
        container.setLayout(new BorderLayout());

        // Add the map to the center of the container
        container.add(map, BorderLayout.CENTER);

        /**
         * Panel to put the buttons.
         */
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));

        /**
         * Button to zoom in the map.
         */
        zoomIn = new JButton("Zoom In");
        zoomIn.addActionListener(e -> map.zoomIn());
        /**
         * Button to zoom out the map.
         */
        zoomOut = new JButton("Zoom Out");
        zoomOut.addActionListener(e -> map.zoomOut());

        buttonsPanel.add(zoomOut);
        buttonsPanel.add(zoomIn);

        // Add the buttons to the bottom of the container
        container.add(buttonsPanel, BorderLayout.SOUTH);

        // Add the container to the frame
        add(container);

    }

    /**
     * Initialize the frame
     */
    private void initFrame(String title) {
        setTitle(title);
        // setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    // TESTABILITY
    /**
     * @return the map
     */
    public Maps getMap() {
        return map;
    }

    /**
     * @return the zoomInButton
     */
    public JButton getZoomInButton() {
        return zoomIn;
    }

    /**
     * @return the zoomOutButton
     */
    public JButton getZoomOutButton() {
        return zoomOut;
    }
}
