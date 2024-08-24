package fr.u_paris.gla.crazytrip.gui.maps.popup;

import java.awt.Point;

import javax.swing.JPopupMenu;

import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.crazytrip.gui.maps.Maps;
import fr.u_paris.gla.crazytrip.gui.maps.popup.menuitem.EndPositionItem;
import fr.u_paris.gla.crazytrip.gui.maps.popup.menuitem.StartPositionItem;
import fr.u_paris.gla.crazytrip.gui.observer.SelectPositionObserver;

public class SelectPositionPopupMenu extends JPopupMenu {
    private StartPositionItem startPositionItem;
    private EndPositionItem endPositionItem;

    private GeoPosition clickedPosition;

    public SelectPositionPopupMenu() {
        super();
        
        init();

        setVisible(false);
    }

    private void init() {
        this.startPositionItem = new StartPositionItem(this);
        this.endPositionItem = new EndPositionItem(this);

        add(startPositionItem);
        add(endPositionItem);
    }

    public void addObserverForStartPosition(SelectPositionObserver observer) {
        this.startPositionItem.addObserver(observer);
    }

    public void addObserverForEndPosition(SelectPositionObserver observer) {
        this.endPositionItem.addObserver(observer);
    }

    public void showPopupMenu(Maps maps, Point point) {
        show(maps, point.x, point.y);
        clickedPosition = maps.convertPointToGeoPosition(point);
    }

    public GeoPosition getClickedPosition() {
        return clickedPosition;
    }
}
