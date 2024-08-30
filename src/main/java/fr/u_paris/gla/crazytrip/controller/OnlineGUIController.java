package fr.u_paris.gla.crazytrip.controller;

import java.util.Set;

import javax.swing.SwingUtilities;

import fr.u_paris.gla.crazytrip.dao.StationDAO;
import fr.u_paris.gla.crazytrip.gui.loadingscreen.LoadingScreen;
import fr.u_paris.gla.crazytrip.gui.view.OnlineGUIView;
import fr.u_paris.gla.crazytrip.model.Station;

public class OnlineGUIController implements Controller {
    private OnlineGUIView view;

    public OnlineGUIController(OnlineGUIView view) {
        this.view = view;
    }

    @Override
    public void start() {
        startView();

        addObservers();
    }

    private void startView() {
        SwingUtilities.invokeLater(() -> {
            view.start();

            addStationMarkers();

            SwingUtilities.invokeLater(LoadingScreen.getInstance()::stop);

            view.setVisible(true);
        });
    }

    private void addObservers() {
        view.addZoomInObserver(view.getMap().getZoomHandler());
        view.addZoomOutObserver(view.getMap().getZoomHandler());
        view.addOpenLineButtonObserver(view.getLineSelectionPanel());
        view.addTransportButtonsObservers(view.getLineSelectionPanel().getSelectionPanel().getCombobox());
        view.addClearButtonObserver(view.getLineSelectionPanel().getSelectionPanel().getCombobox());
        view.addClearButtonObserver(view.getMap().getPathPaiter());
        view.addLinePainterObserver(view.getMap().getPathPaiter());
        view.addOpenResearchButtonObserver(view.getResearchPanel());
        view.addPopupStartPositionObserver(view.getResearchPanel().getDepartureField().getTextField());
        view.addPopupEndPositionObserver(view.getResearchPanel().getArrivalField().getTextField());
        view.addPopupStartPositionObserver(view.getResearchPanel().getDepartureField());
        view.addPopupEndPositionObserver(view.getResearchPanel().getArrivalField());
        view.addPathDrawerObserver(view.getMap().getPathPaiter());
    }

    private void addStationMarkers() {
        Set<Station> stations = StationDAO.getAllStations();

        stations.parallelStream().forEach(station -> view.getMap().addStationMarker(station));

        view.getMap().initPainter();
    }
}
