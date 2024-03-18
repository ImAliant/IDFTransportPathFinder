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
        addFormComponents(container);

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

    private void addFormComponents(JPanel container) {
        // Create the form components
        JPanel formPanel = new JPanel(new GridLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        addDepartureAndDestinationComboBoxes(formPanel, gbc);
        addDepartureAndArrivalButtons(formPanel, gbc);

        container.add(formPanel, BorderLayout.NORTH);
    }

    private void addDepartureAndDestinationComboBoxes(JPanel formPanel, GridBagConstraints gbc) {
        List<String> allStations = new ArrayList<>();
        allStations.add("Station 1");
        allStations.add("Station 2");
        allStations.add("Station 3");
        allStations.add("Station 4");
        allStations.add("Bibliotheque Francois Mitterand");
        allStations.add("Bastille");
        allStations.add("Javel");

        DefaultComboBoxModel<String> departureModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> destinationModel = new DefaultComboBoxModel<>();

        JComboBox<String> departureComboBox = new JComboBox<>(departureModel);
        JComboBox<String> destinationComboBox = new JComboBox<>(destinationModel);

        departureComboBox.setEditable(true);
        destinationComboBox.setEditable(true);

        // Add DocumentListeners to filter stations dynamically
        JTextField departureTextField = (JTextField) departureComboBox.getEditor().getEditorComponent();
        JTextField destinationTextField = (JTextField) destinationComboBox.getEditor().getEditorComponent();

        departureTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateComboBox(departureTextField.getText(), allStations, departureModel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateComboBox(departureTextField.getText(), allStations, departureModel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateComboBox(departureTextField.getText(), allStations, departureModel);
            }
        });

        destinationTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateComboBox(destinationTextField.getText(), allStations, destinationModel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateComboBox(destinationTextField.getText(), allStations, destinationModel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateComboBox(destinationTextField.getText(), allStations, destinationModel);
            }
        });

        JLabel departureLabel = new JLabel("From:");
        formPanel.add(departureLabel, gbc);
        gbc.gridx++;
        formPanel.add(departureComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel destinationLabel = new JLabel("To:");
        formPanel.add(destinationLabel, gbc);
        gbc.gridx++;
        formPanel.add(destinationComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
    }

    private void addDepartureAndArrivalButtons(JPanel formPanel, GridBagConstraints gbc) {
        JToggleButton departureButton = new JToggleButton("Departure");
        JToggleButton arrivalButton = new JToggleButton("Arrival");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(departureButton);
        buttonGroup.add(arrivalButton);
        departureButton.setSelected(true);// sélectionné par défaut
        /*
         * SpinnerDateModel spinnerModel = new SpinnerDateModel();
         * JSpinner timeSpinner = new JSpinner(spinnerModel);
         * 
         * JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner,
         * "HH:mm");
         * 
         * // Définissez le modèle de l'éditeur de date pour n'afficher que les heures
         * et
         * // les minutes
         * timeEditor.getTextField().setEditable(false); // Empêche la modification du
         * champ de texte
         * ((JSpinner.DefaultEditor)
         * timeSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.
         * CENTER);
         * timeSpinner.setEditor(timeEditor);
         * 
         * LocalDateTime currentDateTime = LocalDateTime.now();
         * timeSpinner.setValue(currentDateTime);
         * 
         * // Ajoutez le JSpinner à votre formulaire
         * formPanel.add(new JLabel("Time:"));
         * formPanel.add(timeSpinner);
         */
        gbc.gridwidth = 2;
        JButton searchButton = new JButton("Search");
        formPanel.add(searchButton, gbc);
    }

    private void updateComboBox(String searchText, List<String> allStations, DefaultComboBoxModel<String> model) {
        // Filter stations based on search text
        List<String> filteredStations = allStations.stream()
                .filter(station -> station.toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());

        // Update combo box model
        model.removeAllElements();
        for (String station : filteredStations) {
            model.addElement(station);
        }
        ((JComboBox<?>) model.getComboBox()).setPopupVisible(true);
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
