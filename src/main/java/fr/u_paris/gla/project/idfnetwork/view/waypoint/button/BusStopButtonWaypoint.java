package fr.u_paris.gla.project.idfnetwork.view.waypoint.button;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;

public class BusStopButtonWaypoint extends StopButtonWaypoint {
    private static final String BUS_ICON_PATH = "src/main/resources/fr/u_paris/gla/project/bus_stop_logo.png";

    public BusStopButtonWaypoint(Stop stop) {
        super(stop);
        
        this.iconPath = BUS_ICON_PATH;

        try {
            setupIcon();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
