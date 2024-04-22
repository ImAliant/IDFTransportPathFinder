package fr.u_paris.gla.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.github.lgooddatepicker.components.TimePicker;

import fr.u_paris.gla.project.idfnetwork.Itinerary;
import fr.u_paris.gla.project.idfnetwork.ItineraryCalculator;
import fr.u_paris.gla.project.idfnetwork.Network;
import fr.u_paris.gla.project.idfnetwork.Stop;

public class ResearchPanel extends JPanel {
    private JPanel suggestionStations;
    private JTextField departureField;
    private JTextField arrivalField;
    private TimePicker timePicker;
    private JButton searchButton;
    Color backgroundColor = new Color(104, 157, 113);
    Color buttonForeground = Color.WHITE;
    Color buttonBackground = new Color(1, 121, 111);

    public ResearchPanel() {
        setLayout(new GridBagLayout()); // UtifindStopByNamelisation de GridBagLayout
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

        departureField = new JTextField();
        gbc.gridy++; // Passage à la ligne suivante
        addComponent(departureField, gbc);

        suggestionStations = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane1 = new JScrollPane(suggestionStations);
        scrollPane1.setPreferredSize(new Dimension(150, 50));

        gbc.gridy++;
        addComponent(scrollPane1, gbc);

        departureField
                .getDocument()
                .addDocumentListener(AutoComplete.myDocumentListener(departureField, suggestionStations));

        JLabel arrivalLabel = new JLabel("Arrivée:");
        arrivalLabel.setForeground(labelForeground);
        arrivalLabel.setBackground(backgroundColor);
        arrivalLabel.setOpaque(true);
        gbc.gridy++;
        addComponent(arrivalLabel, gbc);

        arrivalField = new JTextField();
        gbc.gridy++;
        addComponent(arrivalField, gbc);

        suggestionStations = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane2 = new JScrollPane(suggestionStations);
        scrollPane2.setPreferredSize(new Dimension(150, 50));
        gbc.gridy++;
        addComponent(scrollPane2, gbc);
        arrivalField.getDocument()
                .addDocumentListener(AutoComplete.myDocumentListener(arrivalField, suggestionStations));

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
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String departName = departureField.getText();
                String arriveName = arrivalField.getText();

                Stop startStop = Network.getInstance().findStopByName(departName);/* new Stop(departName); */
                Stop destinationStop = Network.getInstance().findStopByName(arriveName);
                if (startStop == null || destinationStop == null) {
                    System.out.println("Un des arrêts n'est pas trouvé.");
                    return;
                }
                Itinerary route = ItineraryCalculator.CalculateRoad(startStop, destinationStop);
                if (route == null || route.getStops().isEmpty() || route.getLines().isEmpty()) {
                    System.out.println("Aucun trajet trouvé entre : " + startStop +" et "+ destinationStop + "ou trajet incomplet.");
                    return;
                }

                System.out.println("Trajet trouvé :");
                System.out.println("Départ : " + route.getStops().get(0).getStopName());
                for (int i = 0; i < route.getLines().size(); i++) {
                    System.out.println("Prendre : " + route.getLines().get(i) + " -  Arret : "
                            + route.getStops().get(i + 1).getStopName());
                }
                System.out.println(
                        "Arrivé à destination : " + route.getStops().get(route.getStops().size() - 1).getStopName());
                System.out.println("Distance totale : " + route.getTotalDistance() + " km");
                System.out.println("Durée totale : " + route.getTotalDuration() + " secondes");
            }
        });
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
