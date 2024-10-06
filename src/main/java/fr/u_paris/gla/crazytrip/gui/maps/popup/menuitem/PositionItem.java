package fr.u_paris.gla.crazytrip.gui.maps.popup.menuitem;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;

import fr.u_paris.gla.crazytrip.gui.listener.PositionItemListener;
import fr.u_paris.gla.crazytrip.gui.maps.popup.SelectPositionPopupMenu;
import fr.u_paris.gla.crazytrip.gui.observer.SelectPositionObserver;

/**
 * Abstract class representing a menu item to set a position.
 * 
 * @see JMenuItem
 * @see SelectPositionObserver
 */
public abstract class PositionItem extends JMenuItem {
    /** List of observers to notify when the item is clicked */
    private transient List<SelectPositionObserver> observers = new ArrayList<>();
    /** The menu to which the item belongs */
    private SelectPositionPopupMenu menu;

    /**
     * Constructor.
     * 
     * @param menu     the menu to which the item belongs
     * @param itemName the name of the item
     * 
     * @see SelectPositionPopupMenu
     */
    protected PositionItem(SelectPositionPopupMenu menu, String itemName) {
        super(itemName);

        this.menu = menu;

        addActionListener(new PositionItemListener(this));
    }

    /**
     * Add an observer to the list of observers.
     * 
     * @param observer the observer to add
     */
    public void addObserver(SelectPositionObserver observer) {
        observers.add(observer);
    }

    /**
     * Notify all observers that the item has been clicked, abd send them the clicked position.
     */
    public void setPosition() {
        observers.forEach(observer -> observer.update(menu.getClickedPosition()));
    }
}
