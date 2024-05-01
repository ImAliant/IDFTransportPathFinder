package fr.u_paris.gla.project;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import fr.u_paris.gla.project.idfnetwork.view.ArrivalByMapButton;
import fr.u_paris.gla.project.idfnetwork.view.CustomLabel;
import fr.u_paris.gla.project.idfnetwork.view.CustomTextField;
import fr.u_paris.gla.project.idfnetwork.view.DepartureByMapButton;
import fr.u_paris.gla.project.idfnetwork.view.ResearchButton;
import fr.u_paris.gla.project.observer.ResearchPanelObserver;
import fr.u_paris.gla.project.idfnetwork.view.SuggestionStationsComboBox;

public class ResearchPanel extends JPanel implements ResearchPanelObserver {
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);
    private static final int MARGIN = 18;
    private static final int WIDTH = 250;

    private CustomTextField departureField;
    private CustomTextField arrivalField;
    private DepartureByMapButton departureEnablingMapButton;
    private ArrivalByMapButton arrivalEnablingMapButton;
    
    public ResearchPanel() {
        setLayout(new GridBagLayout()); // Utilisation de GridBagLayout
        setBackground(BACKGROUND_COLOR); 
        setPreferredSize(new Dimension(WIDTH, getHeight()));

        GridBagConstraints gbc = initGridBagConstraints();

        CustomLabel departureLabel = new CustomLabel("Départ:");
        CustomLabel arrivalLabel = new CustomLabel("Arrivée:");

        SuggestionStationsComboBox departureSuggestion = new SuggestionStationsComboBox();
        SuggestionStationsComboBox arrivalSuggestion = new SuggestionStationsComboBox();

        departureField = new CustomTextField(departureSuggestion);
        arrivalField = new CustomTextField(arrivalSuggestion);
        ResearchButton searchButton = new ResearchButton("Recherche", departureField, arrivalField);
        departureEnablingMapButton = new DepartureByMapButton(2, 2);
        arrivalEnablingMapButton = new ArrivalByMapButton(1, 0);

        departureSuggestion.setVisible(false);
        arrivalSuggestion.setVisible(false);

        gbc.insets = new Insets(0, MARGIN, 0, MARGIN);
        addComponent(departureLabel, gbc);
        
        incrementGridY(gbc);
        addComponent(departureField, gbc);
        
        incrementGridY(gbc);
        addComponent(departureEnablingMapButton, gbc);
        
        gbc.insets = new Insets(MARGIN, MARGIN, MARGIN, MARGIN);
        incrementGridY(gbc);
        addComponent(departureSuggestion, gbc);
        
        gbc.insets = new Insets(MARGIN, MARGIN, 0, MARGIN);
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

        incrementGridY(gbc);
        addComponent(searchButton, gbc);
    }

    private GridBagConstraints initGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Toutes les colonnes ont le même index
        gbc.gridy = 0; // Ligne initiale
        gbc.anchor = GridBagConstraints.WEST; // Alignement à gauche
        gbc.fill = GridBagConstraints.HORIZONTAL; // Remplissage horizontal

        return gbc;
    }

    private int incrementGridY(GridBagConstraints gbc) {
        return gbc.gridy++;
    }

    // Méthode pour ajouter un composant avec GridBagConstraints
    private void addComponent(Component component, GridBagConstraints gbc) {
        this.add(component, gbc);
    }

    @Override
    public void updateVisibility() {
        setVisible(!isVisible());
    }

    public DepartureByMapButton getDepartureEnablingMapButton() {
        return departureEnablingMapButton;
    }
    
    public ArrivalByMapButton getArrivalEnablingMapButton() {
        return arrivalEnablingMapButton;
    }


    public CustomTextField getDepartureTextField() {
        return departureField;
    }
    public CustomTextField getArrivalTextField() {
        return arrivalField;
    }

}
