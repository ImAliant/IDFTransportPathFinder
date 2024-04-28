package fr.u_paris.gla.project.idfnetwork;

import fr.u_paris.gla.project.App;
import fr.u_paris.gla.project.idfnetwork.stop.Stop;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ItineraryCalculatorTest {
    private Network network = Network.getInstance();

    @Test
    public void Trip1() {
        

        App.initNetwork();

        Stop a = network.findStop("Charles de Gaulle - Etoile",	2.295927507409278,48.87494579313413);

        Stop b =  network.findStop("Alexandre Dumas", 2.3945914186721278, 48.856199097341126);

        Itinerary route = ItineraryCalculator.CalculateRoad(a, b);


        System.out.println("Trajet trouvé :");
        System.out.println("Départ : " + route.getStops().get(0).getStopName());
        for ( int i = 0; i < route.getLines().size() - 1; i++ ) {
            // if (!route.getLines().get(i).equals(route.getLines().get(i+1))  )
            System.out.println("Prendre : " + route.getLines().get(i) + " -  Arret : " + route.getStops().get(i+1).getStopName());
        }
        System.out.println("Prendre : " + route.getLines().get(route.getLines().size() - 1) + " -  Arrivé à destination : " + route.getStops().get(route.getStops().size() - 1).getStopName());
        System.out.println("Distance totale : " + route.getTotalDistance() + " km");
        System.out.println("Durée totale : " + route.getTotalDuration() + " secondes");
    }

    @Test
    public void Trip2() {

        Stop c = network.findStop("Hôpital Antoine Béclère",2.2537062315154497,  48.78670798237926);
        Stop d = network.findStop("Gare de Chaville - Vélizy",2.1851142141000532, 48.79922054281981);

        Itinerary route = ItineraryCalculator.CalculateRoad(c, d);

        System.out.println("Trajet trouvé :");
        System.out.println("Départ : " + route.getStops().get(0).getStopName());
        for ( int i = 0; i < route.getLines().size() - 1; i++ ) {
            // if (!route.getLines().get(i).equals(route.getLines().get(i+1))  )
                System.out.println("Prendre : " + route.getLines().get(i) + " -  Arret : " + route.getStops().get(i+1).getStopName());
        }
        System.out.println("Prendre : " + route.getLines().get(route.getLines().size() - 1) + " -  Arrivé à destination : " + route.getStops().get(route.getStops().size() - 1).getStopName());
        System.out.println("Distance totale : " + route.getTotalDistance() + " km");
        System.out.println("Durée totale : " + route.getTotalDuration() + " secondes");
    }






    @Test
    public void getStopsFromAdressTest(){
        List<Stop> s= Network.findStopFromGeoPosition(48.8483463,2.3573528, 0.1);
        assertFalse(s.isEmpty());
        System.out.println(s.size());
        for (Stop stop : s) {
            System.out.println(stop);
        }
    }
}
