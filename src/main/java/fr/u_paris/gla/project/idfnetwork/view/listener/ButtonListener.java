package fr.u_paris.gla.project.idfnetwork.view.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;

public class ButtonListener extends MouseAdapter {
    private Stop stop;

    public ButtonListener(Stop stop) {
        this.stop = stop;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Click on " + stop);
    }
}
