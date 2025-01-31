package fr.u_paris.gla.crazytrip.gui.maps.popup;

import java.awt.Point;

import javax.swing.JPopupMenu;

import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.crazytrip.gui.maps.Maps;
import fr.u_paris.gla.crazytrip.gui.maps.popup.menuitem.EndPositionItem;
import fr.u_paris.gla.crazytrip.gui.maps.popup.menuitem.StartPositionItem;
import fr.u_paris.gla.crazytrip.gui.observer.SelectPositionObserver;

/**
 * <p>Class representing the popup menu that appears when the user right-clicks on the map.</p>
 * 
 * <p>This popup menu allows the user to select a start or end position for a trip.</p>
 * 
 * @see StartPositionItem
 * @see EndPositionItem
 */
public class SelectPositionPopupMenu extends JPopupMenu {
    /** Menu item to select the start position */
    private StartPositionItem startPositionItem;
    /** Menu item to select the end position */
    private EndPositionItem endPositionItem;
    /** The position where the user clicked on the map */
    private GeoPosition clickedPosition;

    /**
     * <p>Constructor for the SelectPositionPopupMenu class.</p>
     * 
     * <p>Initializes the popup menu and its items.</p>
     */
    public SelectPositionPopupMenu() {
        super();
        
        init();

        setVisible(false);
    }

    /**
     * <p>Initializes the popup menu and its items.</p>
     * 
     * <p>Creates the start and end position items and adds them to the popup menu.</p>
     * 
     * @see StartPositionItem
     * @see EndPositionItem
     */
    private void init() {
        this.startPositionItem = new StartPositionItem(this);
        this.endPositionItem = new EndPositionItem(this);

        add(startPositionItem);
        add(endPositionItem);
    }

    /**
     * <p>Adds an observer to the start position item.</p>
     * 
     * @param observer The observer to add
     */
    public void addObserverForStartPosition(SelectPositionObserver observer) {
        this.startPositionItem.addObserver(observer);
    }

    /**
     * <p>Adds an observer to the end position item.</p>
     * 
     * @param observer The observer to add
     */
    public void addObserverForEndPosition(SelectPositionObserver observer) {
        this.endPositionItem.addObserver(observer);
    }

    /**
     * <p>Shows the popup menu at the specified position on the map.</p>
     * 
     * @param maps The map on which the popup menu should be shown
     * @param point The position where the popup menu should be shown
     * 
     * @see Maps
     */
    public void showPopupMenu(Maps maps, Point point) {
        show(maps, point.x, point.y);
        clickedPosition = maps.convertPointToGeoPosition(point);
    }

    public GeoPosition getClickedPosition() {
        return clickedPosition;
    }
}
