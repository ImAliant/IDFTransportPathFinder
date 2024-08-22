package fr.u_paris.gla.crazytrip.gui.button;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import fr.u_paris.gla.crazytrip.gui.observer.LineSelectionPanelObserver;
import fr.u_paris.gla.crazytrip.utils.IconUtils;

public class OpenLineSelectionPanelButton extends MiniButton {
    private static final String ICON_PATH = "src/main/resources/plan.png";
    private static final String TOOLTIP = "Ouvrir le panneau de sélection de ligne";
    private transient List<LineSelectionPanelObserver> observers = new ArrayList<>();

    public OpenLineSelectionPanelButton() {
        super(TOOLTIP);

        Icon icon = IconUtils.createIcon(ICON_PATH, SIZE, SIZE);
        if (icon != null) setIcon(icon);
    }

    public void addObserver(LineSelectionPanelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void action() {
        observers.forEach(LineSelectionPanelObserver::updateVisibility);
    }
}
