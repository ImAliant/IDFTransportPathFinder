package fr.u_paris.gla.project.idfnetwork.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import fr.u_paris.gla.project.idfnetwork.Line;
import fr.u_paris.gla.project.observer.CleanDisplayObserver;
import fr.u_paris.gla.project.observer.DisplayLineObserver;

public class DisplayLineComboBox extends JComboBox<String> implements DisplayLineObserver, CleanDisplayObserver {
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
}
