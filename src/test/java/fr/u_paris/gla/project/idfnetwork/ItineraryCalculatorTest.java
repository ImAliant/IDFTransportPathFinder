package fr.u_paris.gla.project.idfnetwork;

import fr.u_paris.gla.project.App;
import fr.u_paris.gla.project.idfnetwork.factory.LineFactory;
import fr.u_paris.gla.project.idfnetwork.stop.Stop;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ItineraryCalculatorTest {
    /* private Network network = Network.getInstance(); */

    /* @Test
    public void Trip1() {
        

        App.initNetwork();

        Stop a = network.findStop("Charles de Gaulle - Etoile",	2.295927507409278,48.87494579313413);

        Stop b =  network.findStop("Alexandre Dumas", 2.3945914186721278, 48.856199097341126);

        Itinerary route = ItineraryCalculator.calculateRoad(a, b);


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

        Itinerary route = ItineraryCalculator.calculateRoad(c, d);

        System.out.println("Trajet trouvé :");
        System.out.println("Départ : " + route.getStops().get(0).getStopName());
        for ( int i = 0; i < route.getLines().size() - 1; i++ ) {
            // if (!route.getLines().get(i).equals(route.getLines().get(i+1))  )
                System.out.println("Prendre : " + route.getLines().get(i) + " -  Arret : " + route.getStops().get(i+1).getStopName());
        }
        System.out.println("Prendre : " + route.getLines().get(route.getLines().size() - 1) + " -  Arrivé à destination : " + route.getStops().get(route.getStops().size() - 1).getStopName());
        System.out.println("Distance totale : " + route.getTotalDistance() + " km");
        System.out.println("Durée totale : " + route.getTotalDuration() + " secondes");
    } */


    @Test
    void testCalculateRoad() {
        Network.getInstance().clear();

        Stop a = new Stop("A", 0, 0);   
        Stop b = new Stop("B", 0, 1);
        Stop c = new Stop("C", 0, 2);
        Stop d = new Stop("D", 0, 3);

        Line l1 = LineFactory.createLine(LineType.METRO, "1", "NOIR");

        l1.addStop(a);
        l1.addStop(b);
        l1.addStop(c);
        l1.addStop(d);

        l1.addPath(a, b, 1, 1);
        l1.addPath(b, c, 1, 1);
        l1.addPath(c, d, 1, 1);

        a.addLine(l1);
        b.addLine(l1);
        c.addLine(l1);
        d.addLine(l1);

        Network.getInstance().addLine(l1);
        Network.getInstance().addStop(a);
        Network.getInstance().addStop(b);
        Network.getInstance().addStop(c);
        Network.getInstance().addStop(d);

        Itinerary route = ItineraryCalculator.CalculateRoad(a, d);
        assertNotNull(route, "Route should not be null");

        System.out.println(route);
    }


    /* @Test
    public void getStopsFromAdressTest(){
        List<Stop> s= Network.findStopFromGeoPosition(48.8483463,2.3573528, 0.1);
        assertFalse(s.isEmpty());
        System.out.println(s.size());
        for (Stop stop : s) {
            System.out.println(stop);
        }
    } */
}
