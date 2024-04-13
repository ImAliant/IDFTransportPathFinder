package fr.u_paris.gla.project.idfnetwork.stop;

public class MetroStop extends Stop {
    public MetroStop(String lname, double longitude, double latitude) {
        super(lname, longitude, latitude);
    }

    @Override
    public String toString() {
        return "Metro stop " + super.toString();
    }
}