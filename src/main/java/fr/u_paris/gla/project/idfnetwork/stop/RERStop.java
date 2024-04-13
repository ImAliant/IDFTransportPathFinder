package fr.u_paris.gla.project.idfnetwork.stop;

public class RERStop extends Stop {
    public RERStop(String lname, double longitude, double latitude) {
        super(lname, longitude, latitude);
    }

    @Override
    public String toString() {
        return "RER stop " + super.toString();
    }
}