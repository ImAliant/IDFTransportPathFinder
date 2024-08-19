package fr.u_paris.gla.crazytrip.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.u_paris.gla.crazytrip.gui.maps.Maps;

public class ZoomOutListener implements ActionListener {
    private Maps map;

    public ZoomOutListener(Maps map) {
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        map.zoomOut();
    }
    
}
