package fr.u_paris.gla.crazytrip.gui.listener;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.u_paris.gla.crazytrip.gui.maps.Maps;
import fr.u_paris.gla.crazytrip.gui.maps.popup.SelectPositionPopupMenu;

/**
 * Listener for selecting a position on the map.
 * 
 * @see Maps
 */
public class SelectPositionListener extends MouseAdapter {
    /** The maps. */
    private Maps maps;

    /**
     * Constructor.
     * 
     * @param maps the maps
     * 
     * @see Maps
     */
    public SelectPositionListener(Maps maps) {
        this.maps = maps;
    }

    /**
     * Show the popup menu when the user right-clicks on the map.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        boolean isRightClick = e.getButton() == MouseEvent.BUTTON3;
        
        if (!isRightClick) return;

        SelectPositionPopupMenu popupMenu = maps.getPopupMenu();
        Point point = e.getPoint();
        popupMenu.showPopupMenu(maps, point);
    }
}
