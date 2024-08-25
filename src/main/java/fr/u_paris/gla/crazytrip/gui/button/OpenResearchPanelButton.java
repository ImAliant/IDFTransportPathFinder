package fr.u_paris.gla.crazytrip.gui.button;

import java.util.List;
import java.util.ArrayList;

import javax.swing.Icon;

import fr.u_paris.gla.crazytrip.gui.observer.PanelObserver;
import fr.u_paris.gla.crazytrip.utils.IconUtils;

public class OpenResearchPanelButton extends MiniButton {
    private static final String ICON_PATH = "src/main/resources/icons/loupe_icon.png";
    private static final String TOOLTIP = "Ouvrir le panneau de recherche";
    private transient List<PanelObserver> observers = new ArrayList<>();

    public OpenResearchPanelButton() {
        super(TOOLTIP);

        Icon icon = IconUtils.createIcon(ICON_PATH, SIZE, SIZE);
        if (icon != null) setIcon(icon);
    }

    public void addObserver(PanelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void action() {
        observers.forEach(PanelObserver::updateVisibility);
    }
}
