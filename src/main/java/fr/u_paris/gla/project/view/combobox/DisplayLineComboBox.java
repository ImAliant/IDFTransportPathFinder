package fr.u_paris.gla.project.view.combobox;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.observer.CleanDisplayObserver;
import fr.u_paris.gla.project.observer.DisplayLineObserver;
import fr.u_paris.gla.project.observer.LinePaintObserver;
/**
 * The {@code DisplayLineComboBox} class extends {@code JComboBox<String>} to manage a list of transportation lines
 * in a GUI application. It allows users to select a line from the dropdown, handling the display and updates of line information.
 * It implements {@code DisplayLineObserver} and {@code CleanDisplayObserver} to receive updates about line data changes.
 * 
 * @author Diamant Alexandre
 */
public class DisplayLineComboBox extends JComboBox<String> implements DisplayLineObserver, CleanDisplayObserver {
    private transient List<LinePaintObserver> observers = new ArrayList<>();

    private transient List<Line> lines;
    /**
     * Constructs a new {@code DisplayLineComboBox} initially empty, ready to be populated with lines.
     */
    public DisplayLineComboBox() {
        super();

        lines = new ArrayList<>();
    }

    /**
     * Adds a line to the combobox, storing the line object for reference and displaying its name for user selection.
     *
     * @param line the line to be added to the combobox
     */
    public void addLine(Line line) {
        lines.add(line);
        addItem(line.getLineName());
    }

    /**
     * Clears all line information from the combobox and internal storage, resetting the display.
     */
    public void clean() {
        lines.clear();
        removeAllItems();
    }
    
    /**
     * Retrieves the currently selected {@code Line} object based on the selected item in the combobox.
     *
     * @return the selected {@code Line}, or {@code null} if no line is selected
     */
    public Line getSelectedLine() {
        if (getSelectedIndex() == -1) {
            return null;
        }

        return lines.get(getSelectedIndex());
    }

    /**
     * Responds to updates in the list of lines, refreshing the combobox items to reflect the current lines.
     *
     * @param lines the updated list of {@code Line} objects to display
     */
    @Override
    public void update(List<Line> lines) {
        clean();
        for (Line line : lines) {
            addLine(line);
        }
    }

    /**
     * Clears the display of the combobox, intended as a response to a 'clean display' command.
     */
    @Override
    public void cleanDisplay() {
        clean();
    }
    /**
     * Adds an observer to be notified when a line is selected within the combobox.
     *
     * @param observer the {@code LinePaintObserver} to be notified of line selections
     */
    public void addObserver(LinePaintObserver observer) {
        observers.add(observer);
    }
    /**
     * Notifies all registered observers of a selected line
     */
    public void notifyObservers(Line line) {
        for (LinePaintObserver observer : observers) {
            observer.showLine(line);
        }
    }
}
