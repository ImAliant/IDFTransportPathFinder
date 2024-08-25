package fr.u_paris.gla.crazytrip.gui.combobox;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import javax.swing.JComboBox;

import fr.u_paris.gla.crazytrip.gui.observer.ClearLineObserver;
import fr.u_paris.gla.crazytrip.gui.observer.DisplayLineObserver;
import fr.u_paris.gla.crazytrip.gui.observer.LinePainterObserver;
import fr.u_paris.gla.crazytrip.model.Line;

public class SelectionLineComboBox extends JComboBox<String> implements DisplayLineObserver, ClearLineObserver {
    private transient List<LinePainterObserver> observers = new ArrayList<>();
    
    private transient List<Line> lines;

    public SelectionLineComboBox() {
        super();

        lines = new ArrayList<>();
    }

    public void addLineItems() {
        for (Line line : lines) {
            addItem(line.getName());
        }
    }

    private void sortLines(List<Line> lines) {
        Collections.sort(lines, (line1, line2) -> line1.getName().compareTo(line2.getName()));
        this.lines = lines;
    }

    @Override
    public void clearLine() {
        removeAllItems();
        lines.clear();
    }

    @Override
    public void update(List<Line> lines) {
        clearLine();

        sortLines(lines);

        addLineItems();
    }

    public Line getSelectedLine() {
        if (getSelectedIndex() == -1) return null;

        return lines.get(getSelectedIndex());
    }

    public void addObserver(LinePainterObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(Line line) {
        for (LinePainterObserver observer : observers) {
            observer.paintLine(line);
        }
    }
}
