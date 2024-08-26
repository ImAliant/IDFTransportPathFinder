package fr.u_paris.gla.crazytrip.model;

import java.util.Set;

import fr.u_paris.gla.crazytrip.dao.StationDAO;

public final class PersonalizedNode extends Node {
    private Set<Station> closeStations;

    public PersonalizedNode(double latitude, double longitude) {
        super("Personalized", latitude, longitude);

        closeStations = StationDAO.findCloseStations(this, 0.5);
    }
    
    public Set<Station> getCloseStations() {
        return closeStations;
    }
}
