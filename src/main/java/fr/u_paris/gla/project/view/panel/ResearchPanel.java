package fr.u_paris.gla.project.view.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import fr.u_paris.gla.project.observer.ResearchPanelObserver;
import fr.u_paris.gla.project.observer.ResearchButtonObserver;
import fr.u_paris.gla.project.view.button.ArrivalByMapButton;
import fr.u_paris.gla.project.view.button.DepartureByMapButton;
import fr.u_paris.gla.project.view.button.ResearchButton;
import fr.u_paris.gla.project.view.combobox.SuggestionStationsComboBox;
import fr.u_paris.gla.project.view.label.CustomLabel;
import fr.u_paris.gla.project.view.textfield.CustomTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class ResearchPanel extends JPanel implements ResearchPanelObserver, ResearchButtonObserver {
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);
    private static final int MARGIN = 18;
    private static final int WIDTH = 250;

    private CustomTextField departureField;
    private CustomTextField arrivalField;
    private DepartureByMapButton departureEnablingMapButton;
    private ArrivalByMapButton arrivalEnablingMapButton;

    private ResearchButton searchButton;

    private JLabel errorLabel;

    public ResearchPanel() {
        setLayout(new GridBagLayout()); // Utilisation de GridBagLayout
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension(WIDTH, getHeight()));

        GridBagConstraints gbc = initGridBagConstraints();

        CustomLabel departureLabel = new CustomLabel("Départ:");
        CustomLabel arrivalLabel = new CustomLabel("Arrivée:");

        SuggestionStationsComboBox departureSuggestion = new SuggestionStationsComboBox();
        SuggestionStationsComboBox arrivalSuggestion = new SuggestionStationsComboBox();

        errorLabel = new JLabel("Renseigner les deux champs");

        departureField = new CustomTextField(departureSuggestion);
        arrivalField = new CustomTextField(arrivalSuggestion);
        searchButton = new ResearchButton("Recherche", departureField, arrivalField);
        departureEnablingMapButton = new DepartureByMapButton();
        arrivalEnablingMapButton = new ArrivalByMapButton();

        departureSuggestion.setVisible(false);
        arrivalSuggestion.setVisible(false);

        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);

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

        incrementGridY(gbc);
        addComponent(errorLabel, gbc);

        searchButton.addResearchObserver(this);
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
