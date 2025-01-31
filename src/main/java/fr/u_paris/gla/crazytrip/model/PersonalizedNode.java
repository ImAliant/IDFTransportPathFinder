package fr.u_paris.gla.crazytrip.model;

import java.util.Set;

import fr.u_paris.gla.crazytrip.dao.StationDAO;

public final class PersonalizedNode extends Node {
    /** The stations close to this personalized node */
    private Set<Station> closeStations;

    /**
     * Creates a new personalized node with the given coordinates.
     * Initializes the close stations to the stations within 0.5 km of this node.
     * 
     * @param latitude the latitude of the node
     * @param longitude the longitude of the node
     */
    public PersonalizedNode(double latitude, double longitude) {
        super("Personalized", latitude, longitude);

        closeStations = StationDAO.findCloseStations(this, 0.5);
    }
    
    /**
     * Getter for the stations close to this personalized node.
     * @return the stations close to this personalized node
     */
    public Set<Station> getCloseStations() {
        return closeStations;
    }
}
