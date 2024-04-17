package fr.u_paris.gla.project;

import javax.swing.*;
import java.awt.*;
import com.github.lgooddatepicker.components.TimePicker;

import fr.u_paris.gla.project.idfnetwork.Network;
import fr.u_paris.gla.project.idfnetwork.Stop;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;




public class ResearchPanel extends JPanel {
    private JTextField departureField;
    private JTextField arrivalField;
    private TimePicker timePicker;
    private JButton searchButton;
    Color backgroundColor = new Color (104, 157, 113);
    Color buttonForeground = Color.WHITE;
    Color buttonBackground = new Color (1, 121, 111);
    

    public ResearchPanel() {
        setLayout(new GridBagLayout()); // Utilisation de GridBagLayout
        setBackground(backgroundColor); 

        // Couleurs pour les étiquettes
        Color labelForeground = Color.WHITE;
        

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Toutes les colonnes ont le même index
        gbc.gridy = 0; // Ligne initiale
        gbc.fill = GridBagConstraints.HORIZONTAL; // Remplissage horizontal


        
        JLabel departureLabel = new JLabel("Départ:");
        departureLabel.setBackground(backgroundColor); 
        departureLabel.setForeground(labelForeground);
        departureLabel.setOpaque(true); // Permet d'afficher la couleur de fond
        addComponent(departureLabel, gbc);

        departureField = new JTextField(15);
        gbc.gridy++; // Passage à la ligne suivante
        addComponent(departureField, gbc);

        JLabel arrivalLabel = new JLabel("Arrivée:");
        arrivalLabel.setForeground(labelForeground);
        arrivalLabel.setBackground(backgroundColor); 
        arrivalLabel.setOpaque(true);
        gbc.gridy++; 
        addComponent(arrivalLabel, gbc);

        arrivalField = new JTextField();
        gbc.gridy++;
        addComponent(arrivalField, gbc);




        JLabel hourLabel = new JLabel("Heure:");
        hourLabel.setForeground(labelForeground);
        hourLabel.setBackground(backgroundColor); 
        hourLabel.setOpaque(true);
        gbc.gridy++; 
        addComponent(hourLabel, gbc);
        
        timePicker = new TimePicker(); // Initialisation du TimePicker
        gbc.gridy++; // Passage à la ligne suivante
        addComponent(timePicker, gbc);


        

        searchButton = new JButton("Recherche");
        searchButton.setForeground(buttonForeground);
        searchButton.setBackground(buttonBackground);
        gbc.gridy++; 
        addComponent(searchButton, gbc);
        
        
        
    }
    // Méthode pour ajouter un composant avec GridBagConstraints
    private void addComponent(Component component, GridBagConstraints gbc) {
        gbc.anchor = GridBagConstraints.WEST; // Alignement à gauche
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expansion horizontale
        gbc.insets = new Insets(18, 18, 18, 18); // Marge entre les composants
        add(component, gbc);
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
