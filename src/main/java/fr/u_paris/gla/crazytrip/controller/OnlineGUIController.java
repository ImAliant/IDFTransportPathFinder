package fr.u_paris.gla.crazytrip.controller;

import java.util.Set;

import javax.swing.SwingUtilities;

import fr.u_paris.gla.crazytrip.dao.StationDAO;
import fr.u_paris.gla.crazytrip.gui.listener.ZoomInListener;
import fr.u_paris.gla.crazytrip.gui.listener.ZoomOutListener;
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

            addStationMarkers();
        });

        this.view.addZoomInListener(new ZoomInListener(view.getMap()));
        this.view.addZoomOutListener(new ZoomOutListener(view.getMap()));
    }

    private void addStationMarkers() {
        Set<Station> stations = StationDAO.getAllStations();

        stations.parallelStream().forEach(station -> view.getMap().addStationMarker(station));

        view.getMap().initPainter();
    }
}
