package fr.u_paris.gla.crazytrip.gui.combobox;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import javax.swing.JComboBox;

import fr.u_paris.gla.crazytrip.gui.observer.ClearLineObserver;
import fr.u_paris.gla.crazytrip.gui.observer.DisplayLineObserver;
import fr.u_paris.gla.crazytrip.gui.observer.LinePainterObserver;
import fr.u_paris.gla.crazytrip.model.Line;

/**
 * <p>Class representing a combo box to select a line.</p>
 * 
 * <p>It is used in the LineSelectionPanel class.</p>
 * <p>The combo box is filled with the lines of the network.</p>
 * <p>When a line is selected, it notifies observers that the line should be painted on the map.</p>
 * 
 * @see JComboBox
 * @see DisplayLineObserver
 * @see ClearLineObserver
 * @see LinePainterObserver
 */
public class SelectionLineComboBox extends JComboBox<String> implements DisplayLineObserver, ClearLineObserver {
    /** List of observers to notify when a line is selected */
    private transient List<LinePainterObserver> observers = new ArrayList<>();
    /** List of the lines shown in the combo box */
    private transient List<Line> lines;

    /**
     * Constructor.
     */
    public SelectionLineComboBox() {
        super();

        lines = new ArrayList<>();
    }

    /**
     * Add all the lines present in the list to the combo box.
     */
    public void addLineItems() {
        for (Line line : lines) {
            addItem(line.getName());
        }
    }

    /**
     * Sort the lines by their name.
     * 
     * @param lines the list of lines to sort
     */
    private void sortLines(List<Line> lines) {
        Collections.sort(lines, (line1, line2) -> line1.getName().compareTo(line2.getName()));
        this.lines = lines;
    }

    /**
     * Remove all the items from the combo box and clear the list of lines.
     */
    @Override
    public void clearLine() {
        removeAllItems();
        lines.clear();
    }

    /**
     * Update the combo box with the new list of lines.
     * 
     * @param lines the new list of lines
     */
    @Override
    public void update(List<Line> lines) {
        clearLine();

        sortLines(lines);

        addLineItems();
    }

    /**
     * Get the selected line.
     * 
     * @return the selected line
     */
    public Line getSelectedLine() {
        if (getSelectedIndex() == -1) return null;

        return lines.get(getSelectedIndex());
    }

    /**
     * Add an observer to the list of observers.
     * 
     * @param observer the observer to add
     */
    public void addObserver(LinePainterObserver observer) {
        observers.add(observer);
    }

    /**
     * Notify all observers that the line has been selected.
     * 
     * @param line the line to paint
     */
    public void notifyObservers(Line line) {
        for (LinePainterObserver observer : observers) {
            observer.paintLine(line);
        }
    }
}
