package fr.u_paris.gla.project;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.SwingUtilities;

import fr.u_paris.gla.project.idfnetwork.Network;
import fr.u_paris.gla.project.idfnetwork.stop.Stop;
import fr.u_paris.gla.project.idfnetwork.view.CustomTextField;
import fr.u_paris.gla.project.idfnetwork.view.SuggestionStationsComboBox;


public class AutoComplete {
private static Network instance = Network.getInstance();

private AutoComplete() {
}

public static void showSuggestions(CustomTextField prefix, SuggestionStationsComboBox suggestions) {
    List<Stop> stops = instance.getStops();

    suggestions.clearSuggestion();
    for (Stop stop : stops) {
        String station = stop.getStopName();
        String linesForStop = stop.getLines().toString();
        String stopWithLines = station + "[" + linesForStop +"]";
        if (station.toLowerCase().contains(prefix.getText().toLowerCase())) {
            
            suggestions.addItem(stopWithLines);
        }
    }
    suggestions.addMouseListener(
        new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectItem = (String) suggestions.getSelectedItem();
                
                String[] parts = selectItem.split("\\["); // on utilise \\[ car [ est un caractère spécial dans les expressions régulières.
                String stopName = parts[0].trim();

                prefix.setText(stopName);
                
                SwingUtilities.invokeLater(() -> suggestions.clearSuggestion());
                
            }
        }
    );
}

}
