package fr.u_paris.gla.crazytrip.gui.listener;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.u_paris.gla.crazytrip.gui.maps.Maps;
import fr.u_paris.gla.crazytrip.gui.maps.popup.SelectPositionPopupMenu;

public class SelectPositionListener extends MouseAdapter {
    private Maps maps;

    public SelectPositionListener(Maps maps) {
        this.maps = maps;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        boolean isRightClick = e.getButton() == MouseEvent.BUTTON3;
        
        if (!isRightClick) return;

        SelectPositionPopupMenu popupMenu = maps.getPopupMenu();
        Point point = e.getPoint();
        popupMenu.showPopupMenu(maps, point);
    }
}
