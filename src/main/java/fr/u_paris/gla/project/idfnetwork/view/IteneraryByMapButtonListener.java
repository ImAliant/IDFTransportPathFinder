package fr.u_paris.gla.project.idfnetwork.view;

import fr.u_paris.gla.project.idfnetwork.Network;

import org.jxmapviewer.viewer.GeoBounds;
import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.project.idfnetwork.Itinerary;
import fr.u_paris.gla.project.idfnetwork.ItineraryCalculator;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;
import fr.u_paris.gla.project.idfnetwork.view.waypoint.StopWaypoint;
import fr.u_paris.gla.project.idfnetwork.view.waypoint.button.StopButtonWaypoint;

public class IteneraryByMapButtonListener {
    private CustomTextField departureField;
    private CustomTextField arrivalField;
    private GeoPosition geo;
    private StopWaypoint stop;
    

    public IteneraryByMapButtonListener(CustomTextField departureField, CustomTextField arrivalField) {
        this.departureField = departureField;
        this.arrivalField = arrivalField;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
    }


}
