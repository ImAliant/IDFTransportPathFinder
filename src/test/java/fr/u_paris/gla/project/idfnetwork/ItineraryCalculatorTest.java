package fr.u_paris.gla.project.idfnetwork;

import org.junit.jupiter.api.Test;

public class ItineraryCalculatorTest {
    @Test
    public void Trip1() {

        Stop a = new Stop("1", 0, 0);
        Stop b = new Stop("2", 1, 1);
        Stop c = new Stop("3", 2, 2);
        Stop d = new Stop("4", 3, 3);
        Stop e = new Stop("5", 4, 4);
        Stop f = new Stop("6", 4, 4);

        Network net = Network.getInstance();
        net.addStop(a);
        net.addStop(b);
        net.addStop(c);
        net.addStop(e);
        net.addStop(d);
        net.addStop(f);

        a.addPath(b, 1.5, 7);
        b.addPath(a, 1.5, 7);


        a.addPath(c, 1.5, 9);
        c.addPath(a, 1.5, 9);


        a.addPath(f, 1.5, 14);
        f.addPath(a, 1.5, 14);


        b.addPath(c, 2.0, 10);
        c.addPath(b, 2.0, 10);


        b.addPath(d, 1.8, 15);
        d.addPath(b, 1.8, 15);


        c.addPath(d, 1.8, 11);
        d.addPath(c, 1.8, 11);


        c.addPath(f, 1.8, 2);
        f.addPath(c, 1.8, 2);


        d.addPath(e, 2.2, 6);
        e.addPath(d, 2.2, 6);


        e.addPath(f, 2.2, 9);
        f.addPath(e, 2.2, 9);

        Itinerary route = ItineraryCalculator.CalculateRoad(a, e);

        System.out.println("Trajet trouvé :");
        System.out.println("Arrets : " + route.getStops());
        System.out.println("Distance totale : " + route.getTotalDistance() + " km");
        System.out.println("Durée totale : " + route.getTotalDuration() + " minutes");


    }

    @Test
    public void Trip2() {

        Stop a = new Stop("0", 0, 0);
        Stop b = new Stop("1", 1, 1);
        Stop c = new Stop("2", 2, 2);
        Stop d = new Stop("3", 3, 3);
        Stop e = new Stop("4", 4, 4);
        Stop f = new Stop("5", 4, 4);
        Stop g = new Stop("6", 4, 4);
        Stop h = new Stop("7", 4, 4);
        Stop i = new Stop("8", 4, 4);

        Network net = Network.getInstance();
        net.addStop(a);
        net.addStop(b);
        net.addStop(c);
        net.addStop(e);
        net.addStop(d);
        net.addStop(f);
        net.addStop(g);
        net.addStop(h);
        net.addStop(i);


        a.addPath(b, 1.5, 4);
        b.addPath(a, 1.5, 4);

        a.addPath(h, 1.5, 7);
        h.addPath(a, 1.5, 7);

        b.addPath(c, 2.0, 8);
        c.addPath(b, 2.0, 8);


        b.addPath(h, 2.0, 11);
        h.addPath(b, 2.0, 11);

        c.addPath(d, 1.8, 7);
        d.addPath(c, 1.8, 7);


        c.addPath(f, 1.8, 4);
        f.addPath(c, 1.8, 4);

        c.addPath(i, 1.8, 2);
        i.addPath(c, 1.8, 2);

        d.addPath(e, 2.2, 9);
        e.addPath(d, 2.2, 9);

        d.addPath(f, 2.2, 14);
        f.addPath(d, 2.2, 14);

        f.addPath(e, 2.2, 10);
        e.addPath(f, 2.2, 10);

        g.addPath(f, 2.2, 2);
        f.addPath(g, 2.2, 2);

        g.addPath(i, 2.2, 6);
        i.addPath(g, 2.2, 6);

        h.addPath(g, 2.2, 1);
        g.addPath(h, 2.2, 1);

        h.addPath(i, 2.2, 7);
        i.addPath(h, 2.2, 7);



        Itinerary route = ItineraryCalculator.CalculateRoad(a, e);

        System.out.println("Trajet trouvé :");
        System.out.println("Arrets : " + route.getStops());
        System.out.println("Distance totale : " + route.getTotalDistance() + " km");
        System.out.println("Durée totale : " + route.getTotalDuration() + " minutes");


    }

    @Test
    public void Trip3() {

        Stop a = new Stop("1", 0, 0);
        Stop b = new Stop("2", 1, 1);
        Stop c = new Stop("3", 2, 2);
        Stop d = new Stop("4", 3, 3);
        Stop e = new Stop("5", 4, 4);
        Stop f = new Stop("6", 4, 4);

        Network net = Network.getInstance();
        net.addStop(a);
        net.addStop(b);
        net.addStop(c);
        net.addStop(e);
        net.addStop(d);
        net.addStop(f);

        a.addPath(b, 1.5, 7);
        b.addPath(a, 1.5, 7);


        a.addPath(c, 1.5, 9);
        c.addPath(a, 1.5, 9);


        a.addPath(f, 1.5, 14);
        f.addPath(a, 1.5, 14);


        b.addPath(c, 2.0, 10);
        c.addPath(b, 2.0, 10);



        c.addPath(f, 1.8, 2);
        f.addPath(c, 1.8, 2);

        e.addPath(d, 1.8, 2);
        d.addPath(e, 1.8, 2);



        Itinerary route = ItineraryCalculator.CalculateRoad(a, e);

        System.out.println("Trajet trouvé :");
        System.out.println("Arrets : " + route.getStops());
        System.out.println("Distance totale : " + route.getTotalDistance() + " km");
        System.out.println("Durée totale : " + route.getTotalDuration() + " minutes");


    }


}
