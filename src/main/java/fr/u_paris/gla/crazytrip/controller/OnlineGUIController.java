package fr.u_paris.gla.crazytrip.controller;

import java.util.Set;

import javax.swing.SwingUtilities;

import fr.u_paris.gla.crazytrip.dao.StationDAO;
import fr.u_paris.gla.crazytrip.gui.view.OnlineGUIView;
import fr.u_paris.gla.crazytrip.model.Station;

public class OnlineGUIController implements Controller {
    private OnlineGUIView view;

    public OnlineGUIController(OnlineGUIView view) {
        this.view = view;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            view.start();
            view.setVisible(true);

            Set<Station> stations = StationDAO.getAllStations();

            stations.parallelStream().forEach(station -> view.getMap().addStationMarker(station));

            view.getMap().initPainter();
        });
    }
}
