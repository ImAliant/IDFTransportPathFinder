package fr.u_paris.gla.project.idfnetwork.stop;

public class TramwayStop extends Stop {
    public TramwayStop(String lname, double longitude, double latitude) {
        super(lname, longitude, latitude);
    }

    @Override
    public String toString() {
        return "Tramway stop " + super.toString();
    }
}