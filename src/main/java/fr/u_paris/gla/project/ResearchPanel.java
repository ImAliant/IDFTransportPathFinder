package fr.u_paris.gla.project;

import javax.swing.*;
import java.awt.*;
import com.github.lgooddatepicker.components.TimePicker;

public class ResearchPanel extends JPanel {
    private JTextField departureField;
    private JTextField arrivalField;
    private TimePicker timePicker;
    private JButton searchButton;

    public ResearchPanel() {
        setLayout(new GridLayout(1, 4));
        JLabel departureLabel = new JLabel("Départ:");
        departureField = new JTextField();
        add(departureLabel);
        add(departureField);

        JLabel arrivalLabel = new JLabel("Arrivée:");
        arrivalField = new JTextField();
        add(arrivalLabel);
        add(arrivalField);

        JLabel hourLabel = new JLabel("Heure:");
        add(hourLabel);
        
        timePicker = new TimePicker(); // Initialisation du TimePicker
        add(timePicker); // Ajout du TimePicker à votre panneau de recherche


        searchButton = new JButton("Recherche");
        add(searchButton);
    }

    public JTextField getDepartureField() {
        return departureField;
    }

    public JTextField getArrivalField() {
        return arrivalField;
    }

    public String getSelectedTime() {
        return timePicker.getTime().toString();
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}
