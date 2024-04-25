package fr.u_paris.gla.project;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.JLabel;


import fr.u_paris.gla.project.idfnetwork.Network;
import fr.u_paris.gla.project.idfnetwork.Stop;
import fr.u_paris.gla.project.idfnetwork.view.CustomTextField;
import fr.u_paris.gla.project.idfnetwork.view.SuggestionStationsScrollPane;

public class AutoComplete {
    private AutoComplete() {}

    public static void showSuggestions(CustomTextField prefix, SuggestionStationsScrollPane suggestions) {
        List<Stop> stops = Network.getInstance().getStops();
        JPanel allStations = suggestions.getSuggestions();

        allStations.removeAll();
        Border blackline = BorderFactory.createLineBorder(Color.black);
        for (Stop stop : stops) {
            String station = stop.getStopName();
            String linesForStop = stop.getLines().toString();
            String stopWithLines = station + "[" + linesForStop +"]";
            if (station.toLowerCase().contains(prefix.getText().toLowerCase())) {

                JLabel suggestionLabel = new JLabel(stopWithLines, SwingConstants.LEFT);

                suggestionLabel.setBorder(blackline);
                suggestionLabel.addMouseListener(
                        new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                prefix.setText(stopWithLines);
                                allStations.removeAll();

                            }
                        });
                allStations.add(suggestionLabel);
            }
        }
        allStations.revalidate();
        allStations.repaint();
    }
}
