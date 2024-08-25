package fr.u_paris.gla.crazytrip.gui.maps.popup.menuitem;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;

import fr.u_paris.gla.crazytrip.gui.listener.PositionItemListener;
import fr.u_paris.gla.crazytrip.gui.maps.popup.SelectPositionPopupMenu;
import fr.u_paris.gla.crazytrip.gui.observer.SelectPositionObserver;

public abstract class PositionItem extends JMenuItem {
    private transient List<SelectPositionObserver> observers = new ArrayList<>();

    private SelectPositionPopupMenu menu;

    protected PositionItem(SelectPositionPopupMenu menu, String itemName) {
        super(itemName);

        this.menu = menu;

        addActionListener(new PositionItemListener(this));
    }

    public void addObserver(SelectPositionObserver observer) {
        observers.add(observer);
    }

    public void setPosition() {
        observers.forEach(observer -> observer.update(menu.getClickedPosition()));
    }
}
