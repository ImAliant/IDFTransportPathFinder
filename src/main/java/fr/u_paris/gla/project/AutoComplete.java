package fr.u_paris.gla.project;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.JLabel;


import fr.u_paris.gla.project.idfnetwork.Network;
import fr.u_paris.gla.project.idfnetwork.Stop;

public class AutoComplete {
    private AutoComplete() {}

    public static void showSuggestions(JTextField prefix, JPanel allStations) {
        List<Stop> stops = Network.getInstance().getStops();

        allStations.removeAll();
        Border blackline = BorderFactory.createLineBorder(Color.black);
        for (Stop stop : stops) {
            String station = stop.getStopName();
            if (station.toLowerCase().contains(prefix.getText().toLowerCase())) {

                JLabel suggestionLabel = new JLabel(station, SwingConstants.CENTER);

                suggestionLabel.setBorder(blackline);
                suggestionLabel.addMouseListener(
                        new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                prefix.setText(station);
                                allStations.removeAll();

                            }
                        });
                allStations.add(suggestionLabel);
            }
        }
        allStations.revalidate();

        /* List<String> stationNames = new ArrayList<>();
        stops.forEach(stop -> stationNames.add(stop.getStopName()));

        String text = prefix.getText();
        allStations.removeAll();
        Border blackline = BorderFactory.createLineBorder(Color.black);
        for (String station : stationNames) {
            if (station.toLowerCase().contains(text.toLowerCase())) {

                JLabel suggestionLabel = new JLabel(station, SwingConstants.CENTER);

                suggestionLabel.setBorder(blackline);
                suggestionLabel.addMouseListener(
                        new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                prefix.setText(station);
                                allStations.removeAll();

                            }
                        });
                allStations.add(suggestionLabel);
            }
        }
        allStations.revalidate();
        allStations.repaint(); */
    }

    public static DocumentListener myDocumentListener(JTextField t, JPanel j) {
        // TODO Create a listener to show suggestions when the text changes

        DocumentListener res =
                new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        SwingUtilities.invokeLater(() -> showSuggestions(t, j));
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        SwingUtilities.invokeLater(() -> showSuggestions(t, j));
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        SwingUtilities.invokeLater(() -> showSuggestions(t, j));
                    }
                };
        return res;
    }
}
