package fr.u_paris.gla.project;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import com.github.lgooddatepicker.components.TimePicker;

import fr.u_paris.gla.project.idfnetwork.view.CustomLabel;
import fr.u_paris.gla.project.idfnetwork.view.CustomTextField;
import fr.u_paris.gla.project.idfnetwork.view.ResearchButton;
import fr.u_paris.gla.project.idfnetwork.view.SuggestionStationsScrollPane;



public class ResearchPanel extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);
    private static final int MARGIN = 18;
    
    public ResearchPanel() {
        setLayout(new GridBagLayout()); // Utilisation de GridBagLayout
        setBackground(BACKGROUND_COLOR); 

        GridBagConstraints gbc = initGridBagConstraints();

        CustomLabel departureLabel = new CustomLabel("Départ:");
        CustomLabel arrivalLabel = new CustomLabel("Arrivée:");
        CustomLabel hourLabel = new CustomLabel("Heure:");

        SuggestionStationsScrollPane departureSuggestion = new SuggestionStationsScrollPane();
        SuggestionStationsScrollPane arrivalSuggestion = new SuggestionStationsScrollPane();

        CustomTextField departureField = new CustomTextField(departureSuggestion);
        CustomTextField arrivalField = new CustomTextField(arrivalSuggestion);
        TimePicker timePicker = new TimePicker();
        ResearchButton searchButton = new ResearchButton("Recherche");

        addComponent(departureLabel, gbc);

        incrementGridY(gbc);
        addComponent(departureField, gbc);

        incrementGridY(gbc);
        addComponent(departureSuggestion, gbc);
        
        incrementGridY(gbc);
        addComponent(arrivalLabel, gbc);

        incrementGridY(gbc);
        addComponent(arrivalField, gbc);

        incrementGridY(gbc);
        addComponent(arrivalSuggestion, gbc);
        
        incrementGridY(gbc);
        addComponent(hourLabel, gbc);

        incrementGridY(gbc);
        addComponent(timePicker, gbc);

        incrementGridY(gbc);
        addComponent(searchButton, gbc);
    }

    private GridBagConstraints initGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Toutes les colonnes ont le même index
        gbc.gridy = 0; // Ligne initiale
        gbc.anchor = GridBagConstraints.WEST; // Alignement à gauche
        gbc.fill = GridBagConstraints.HORIZONTAL; // Remplissage horizontal
        gbc.insets = new Insets(MARGIN, MARGIN, MARGIN, MARGIN); // Marge entre les composants

        return gbc;
    }

    private int incrementGridY(GridBagConstraints gbc) {
        return gbc.gridy++;
    }

    // Méthode pour ajouter un composant avec GridBagConstraints
    private void addComponent(Component component, GridBagConstraints gbc) {
        this.add(component, gbc);
    }
}
