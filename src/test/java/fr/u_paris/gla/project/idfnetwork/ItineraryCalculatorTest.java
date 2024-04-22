package fr.u_paris.gla.project.idfnetwork;

import fr.u_paris.gla.project.App;
import fr.u_paris.gla.project.utils.GPS;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ItineraryCalculatorTest {
    private Network network = Network.getInstance();

    @Test
    public void Trip1() {


        App.initNetwork();

        Stop a = network.findSameStop("Charles de Gaulle - Etoile",	2.295927507409278,48.87494579313413);

        Stop b =  network.findSameStop("Alexandre Dumas", 2.3945914186721278, 48.856199097341126);

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
    public void Trip3() {


        Stop a = network.findSameStop("Charles de Gaulle - Etoile",	2.295927507409278,48.87494579313413);

        Stop b =  network.findSameStop("Alexandre Dumas", 2.3947127337554788, 48.85624467843737);

        Itinerary route = ItineraryCalculator.CalculateRoad(b, a);


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

//    @Test
//    public void Trip3() {
//
//
//        App.initNetwork();
//
//        Stop a = network.findSameStop("Charles de Gaulle - Etoile",	2.295927507409278,48.87494579313413);
//
//        Stop b =  network.findSameStop("Alexandre Dumas", 2.3947127337554788, 48.85624467843737);
//
//        Itinerary route = ItineraryCalculator.CalculateRoad(b, a);
//
//
//        System.out.println("Trajet trouvé :");
//        System.out.println("Départ : " + route.getStops().get(0).getStopName());
//        for ( int i = 0; i < route.getLines().size() - 1; i++ ) {
//            // if (!route.getLines().get(i).equals(route.getLines().get(i+1))  )
//            System.out.println("Prendre : " + route.getLines().get(i) + " -  Arret : " + route.getStops().get(i+1).getStopName());
//        }
//        System.out.println("Prendre : " + route.getLines().get(route.getLines().size() - 1) + " -  Arrivé à destination : " + route.getStops().get(route.getStops().size() - 1).getStopName());
//        System.out.println("Distance totale : " + route.getTotalDistance() + " km");
//        System.out.println("Durée totale : " + route.getTotalDuration() + " secondes");
//    }

    @Test
    public void Trip2() {

        Stop c = network.findSameStop("Hôpital Antoine Béclère",2.2537062315154497,  48.78670798237926);
        Stop d = network.findSameStop("Gare de Chaville - Vélizy",2.1851142141000532, 48.79922054281981);

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
    public void Trip4() {


        Stop a = network.findSameStop("Bibliothèque François Mitterrand"	, 2.376487371168301,48.829925765928905);

        Stop b =  network.findSameStop("Olympiades"	, 2.366992317018834,48.82703387034927);
        System.out.println("départ : " + a);
        System.out.println("départ : " + b);
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
    public void findSameStop(){
        Stop stop = network.findSameStop("Alexandre Dumas",2.3945914186721278,48.856199097341126);
        double distance = Math.sqrt(Math.pow(48.85624467843737 - stop.getLatitude(), 2) + Math.pow(2.3947127337554788 - stop.getLongitude(), 2));
        double dist = GPS.distance(48.85624467843737,2.3947127337554788,stop.getLatitude(),stop.getLongitude());
        System.out.println(stop);
        System.out.println("Distance :" + dist);
    }


    @Test
    public void getStopsFromAdressTest(){
        List<Stop> s= ItineraryCalculator.getStopsFromAdress(48.8483463,2.3573528);
        assertFalse(s.isEmpty());
        System.out.println(s.size());
        for (Stop stop : s) {
            System.out.println(stop);
        }
    }
}
