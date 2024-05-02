package fr.u_paris.gla.project.view.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import fr.u_paris.gla.project.observer.ResearchPanelObserver;
import fr.u_paris.gla.project.observer.ResearchButtonObserver;
import fr.u_paris.gla.project.view.button.research_panel_button.ArrivalByMapButton;
import fr.u_paris.gla.project.view.button.research_panel_button.DepartureByMapButton;
import fr.u_paris.gla.project.view.button.research_panel_button.ResearchButton;
import fr.u_paris.gla.project.view.combobox.SuggestionStationsComboBox;
import fr.u_paris.gla.project.view.label.CustomLabel;
import fr.u_paris.gla.project.view.textfield.CustomTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Panel containing the research components.
 * 
 * @see JPanel
 * @see ResearchPanelObserver
 * 
 * @author DIAMANT Alexandre
 * @author DEDEOGLU Dilara
 * @author VELANGANNI Jean-Paul
 * @author AZIZ Ghizlane
 */
public class ResearchPanel extends JPanel implements ResearchPanelObserver, ResearchButtonObserver {
    /** Background color of the panel. */
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);
    /** Margin between the components. */
    private static final int MARGIN = 18;
    /** Width of the panel. */
    private static final int WIDTH = 250;

    /** Departure field. */
    private CustomTextField departureField;
    /** Arrival field. */
    private CustomTextField arrivalField;
    /**
     * Button to enable the possibility to select the departure coordinate on the
     * map.
     */
    private DepartureByMapButton departureEnablingMapButton;
    /**
     * Button to enable the possibility to select the arrival coordinate on the map.
     */
    private ArrivalByMapButton arrivalEnablingMapButton;
    /** Button to launch the research. */
    private ResearchButton searchButton;
    /** Warning for panel fields errors. */
    private JLabel errorLabel;

    /**
     * Constructor of the research panel.
     */
    public ResearchPanel() {
        setupPanel();
        initializeComponents();
    }

    /**
     * Setup the panel.
     */
    private void setupPanel() {
        setLayout(new GridBagLayout());
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension(WIDTH, getHeight()));
    }

    /**
     * Initialize the components of the panel.
     */
    private void initializeComponents() {
        GridBagConstraints gbc = initGridBagConstraints();
        initLabels(gbc);
        setupErrorLabel(gbc);
    }

    /**
     * Initialize the labels of the panel.
     * 
     * @param gbc GridBagConstraints
     */
    private void initLabels(GridBagConstraints gbc) {    
        initDepartureComponents(gbc);
        initArrivalComponents(gbc);
        searchButton = new ResearchButton("Recherche", departureField, arrivalField);
        incrementGridY(gbc);
        addComponent(searchButton, gbc);
    }
    /**
     * Initialize the departure components.
     * 
     * @param gbc GridBagConstraints
     */
    private void initDepartureComponents(GridBagConstraints gbc) {
        CustomLabel departureLabel = new CustomLabel("Départ:");
        SuggestionStationsComboBox departureSuggestion = new SuggestionStationsComboBox();
        departureField = new CustomTextField(departureSuggestion);
        departureEnablingMapButton = new DepartureByMapButton();
        departureSuggestion.setVisible(false);

        gbc.insets = new Insets(0, MARGIN, 0, MARGIN);
        addComponent(departureLabel, gbc);

        incrementGridY(gbc);
        addComponent(departureField, gbc);

        incrementGridY(gbc);
        addComponent(departureEnablingMapButton, gbc);

        gbc.insets = new Insets(MARGIN, MARGIN, MARGIN, MARGIN);
        incrementGridY(gbc);
        addComponent(departureSuggestion, gbc);
    }
    
    /**
     * Initialize the arrival components.
     * 
     * @param gbc GridBagConstraints
     */
    private void initArrivalComponents(GridBagConstraints gbc) {
        CustomLabel arrivalLabel = new CustomLabel("Arrivée:");
        SuggestionStationsComboBox arrivalSuggestion = new SuggestionStationsComboBox();
        arrivalField = new CustomTextField(arrivalSuggestion);
        arrivalEnablingMapButton = new ArrivalByMapButton();
        arrivalSuggestion.setVisible(false);

        gbc.insets = new Insets(0, MARGIN, 0, MARGIN);
        incrementGridY(gbc);
        addComponent(arrivalLabel, gbc);

        gbc.insets = new Insets(0, MARGIN, 0, MARGIN);
        incrementGridY(gbc);
        addComponent(arrivalField, gbc);

        incrementGridY(gbc);
        addComponent(arrivalEnablingMapButton, gbc);

        gbc.insets = new Insets(MARGIN, MARGIN, MARGIN, MARGIN);
        incrementGridY(gbc);
        addComponent(arrivalSuggestion, gbc);
    }

    /**
     * Setup the error label.
     * 
     * @param gbc GridBagConstraints
     */
    private void setupErrorLabel(GridBagConstraints gbc) {
        errorLabel = new JLabel("Recherche impossible");
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        incrementGridY(gbc);
        addComponent(errorLabel, gbc);
        searchButton.addResearchObserver(this);
    }

    /**
     * Initialize the GridBagConstraints.
     * 
     * @return GridBagConstraints
     */
    private GridBagConstraints initGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Toutes les colonnes ont le même index
        gbc.gridy = 0; // Ligne initiale
        gbc.anchor = GridBagConstraints.WEST; // Alignement à gauche
        gbc.fill = GridBagConstraints.HORIZONTAL; // Remplissage horizontal

        return gbc;
    }

    /**
     * Increment the grid Y of the GridBagConstraints.
     * 
     * @param gbc GridBagConstraints
     * @return int
     */
    private int incrementGridY(GridBagConstraints gbc) {
        return gbc.gridy++;
    }

    /**
     * Add a component to the panel.
     * 
     * @param component Component
     * @param gbc       GridBagConstraints
     */
    private void addComponent(Component component, GridBagConstraints gbc) {
        this.add(component, gbc);
    }

    @Override
    public void updateVisibility() {
        setVisible(!isVisible());
    }

    /**
     * Get the departure enabling map button.
     * 
     * @return the departure enabling map button
     */
    public DepartureByMapButton getDepartureEnablingMapButton() {
        return departureEnablingMapButton;
    }

    /**
     * Get the arrival enabling map button.
     * 
     * @return the arrival enabling map button
     */
    public ArrivalByMapButton getArrivalEnablingMapButton() {
        return arrivalEnablingMapButton;
    }

    /**
     * Get the departure text field.
     * 
     * @return the departure text field
     */
    public CustomTextField getDepartureTextField() {
        return departureField;
    }

    /**
     * Get the arrival text field.
     * 
     * @return the arrival text field
     */
    public CustomTextField getArrivalTextField() {
        return arrivalField;
    }

    /**
     * Get the search button.
     * 
     * @return the search button
     */
    public ResearchButton getSearchButton() {
        return searchButton;
    }

    @Override
    public void errorFields() {
        SwingUtilities.invokeLater(() -> {
            errorLabel.setVisible(true);
            repaint();

            Timer timer = new Timer(5000, e -> errorLabel.setVisible(false));
            timer.setRepeats(false);
            timer.start();

            repaint();
        });
    }
}
