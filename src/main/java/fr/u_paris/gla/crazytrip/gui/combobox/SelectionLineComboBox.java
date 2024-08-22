package fr.u_paris.gla.crazytrip.gui.combobox;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import fr.u_paris.gla.crazytrip.gui.observer.ClearLineObserver;
import fr.u_paris.gla.crazytrip.gui.observer.DisplayLineObserver;
import fr.u_paris.gla.crazytrip.model.Line;

public class SelectionLineComboBox extends JComboBox<String> implements DisplayLineObserver, ClearLineObserver {
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

    @Override
    public void clear() {
        removeAllItems();
        lines.clear();
    }

    @Override
    public void update(List<Line> lines) {
        clear();

        this.lines = lines;
        addLineItems();
    }

    public Line getSelectedLine() {
        if (getSelectedIndex() == -1) return null;

        return lines.get(getSelectedIndex());
    }
}
