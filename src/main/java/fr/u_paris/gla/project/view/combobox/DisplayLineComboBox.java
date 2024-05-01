package fr.u_paris.gla.project.view.combobox;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import fr.u_paris.gla.project.idfnetwork.line.Line;
import fr.u_paris.gla.project.observer.CleanDisplayObserver;
import fr.u_paris.gla.project.observer.DisplayLineObserver;
import fr.u_paris.gla.project.observer.LinePaintObserver;

public class DisplayLineComboBox extends JComboBox<String> implements DisplayLineObserver, CleanDisplayObserver {
    private transient List<LinePaintObserver> observers = new ArrayList<>();

    private transient List<Line> lines;

    public DisplayLineComboBox() {
        super();

        lines = new ArrayList<>();
    }

    public void addLine(Line line) {
        lines.add(line);
        addItem(line.getLineName());
    }

    public void clean() {
        lines.clear();
        removeAllItems();
    }

    public Line getSelectedLine() {
        if (getSelectedIndex() == -1) {
            return null;
        }

        return lines.get(getSelectedIndex());
    }

    @Override
    public void update(List<Line> lines) {
        clean();
        for (Line line : lines) {
            addLine(line);
        }
    }

    @Override
    public void cleanDisplay() {
        clean();
    }

    public void addObserver(LinePaintObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(Line line) {
        for (LinePaintObserver observer : observers) {
            observer.showLine(line);
        }
    }
}
