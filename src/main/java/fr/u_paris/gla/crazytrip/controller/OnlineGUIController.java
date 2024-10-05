package fr.u_paris.gla.crazytrip.controller;

import java.util.Set;

import javax.swing.SwingUtilities;

import fr.u_paris.gla.crazytrip.dao.StationDAO;
import fr.u_paris.gla.crazytrip.gui.loadingscreen.LoadingScreen;
import fr.u_paris.gla.crazytrip.gui.view.OnlineGUIView;
import fr.u_paris.gla.crazytrip.model.Station;

/**
 * This class represents an online GUI controller.
 * 
 * It is used to start the application with an online graphical user interface.
 * The user will be able to search for trips, visualize lines and stations and travel on the map.
 * 
 * @see Controller
 * @see OnlineGUIView
 */
public class OnlineGUIController implements Controller {
    /** The view of the application. */
    private OnlineGUIView view;

    /**
     * Creates a new online GUI controller.
     * 
     * @param view The view of the application.
     */
    public OnlineGUIController(OnlineGUIView view) {
        this.view = view;
    }

    /**
     * Starts the application.
     * 
     * It displays the map and the different panels of the application.
     * The user can search for trips, visualize lines and stations and travel on the map.
     */
    @Override
    public void start() {
        startView();

        addObservers();
    }

    /**
     * Starts the view of the application.
     */
    private void startView() {
        SwingUtilities.invokeLater(() -> {
            view.start();

            addStationMarkers();

            LoadingScreen.getInstance().stop();

            view.setVisible(true);
        });
    }

    /**
     * Adds the observers to the view.
     */
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
        view.addPathDrawerObserver(view.getItineraryResultPanel());
        view.addErrorObserver(view.getResearchPanel().getErrorLabel());
    }

    /**
     * Adds the station markers to the map.
     */
    private void addStationMarkers() {
        Set<Station> stations = StationDAO.getAllStations();

        stations.parallelStream().forEach(station -> view.getMap().addStationMarker(station));

        view.getMap().initPainter();
    }
}
