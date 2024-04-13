package fr.u_paris.gla.project.idfnetwork.stop;

public class BusStop extends Stop {
    public BusStop(String lname, double longitude, double latitude) {
        super(lname, longitude, latitude);
    }

    @Override
    public String toString() {
        return "Bus stop " + super.toString();
    }
}