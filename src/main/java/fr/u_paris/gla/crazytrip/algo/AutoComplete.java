package fr.u_paris.gla.crazytrip.algo;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.SwingUtilities;

import fr.u_paris.gla.crazytrip.idfnetwork.Stop;
import fr.u_paris.gla.crazytrip.idfnetwork.network.Network;
import fr.u_paris.gla.crazytrip.view.combobox.SuggestionStationsComboBox;
import fr.u_paris.gla.crazytrip.view.textfield.CustomTextField;

/**
 * This class provides methods to implement an autocomplete functionality for station search in a GUI application.
* @author Aziz Ghizlane
*/

public class AutoComplete {
private static Network instance = Network.getInstance();

private AutoComplete() {
    // Private constructor to prevent instantiation
}



/** if input is prefix of stop, then update suggestions with
 *  all stops starting with prefix ignoring upperCase
 * @param prefix 
 *
 * @param suggestions 
 */
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
                if(selectItem != null){
                String[] parts = selectItem.split("\\["); // we use \\[ because [ is a special character in regular expressions.
                String stopName = parts[0].trim();

                prefix.setText(stopName);
                
                SwingUtilities.invokeLater(() -> suggestions.clearSuggestion());
                }
            }
        }
    );
}

}
