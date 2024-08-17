package fr.u_paris.gla.crazytrip.gui.maps;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.crazytrip.model.Station;

public class StationWaypoint extends DefaultWaypoint {
    private final Station station;
    private final StationButton button;

    public StationWaypoint(Station station) {
        super(new GeoPosition(station.getCoordinates().getLatitude(), station.getCoordinates().getLongitude()));
        
        this.station = station;
        this.button = StationButton.create(station);
    }

    public Station getStation() {
        return station;
    }

    public StationButton getButton() {
        return button;
    }
}
