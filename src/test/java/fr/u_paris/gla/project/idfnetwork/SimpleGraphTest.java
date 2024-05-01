package fr.u_paris.gla.project.idfnetwork;

import org.junit.jupiter.api.Test;

public class SimpleGraphTest {
    /* @Test
    public void Trip1() {
        Line l = new FunicularLine("", "");
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

        a.addPath(b, 1.5, 7,l);
        b.addPath(a, 1.5, 7, l);


        a.addPath(c, 1.5, 9, l);
        c.addPath(a, 1.5, 9, l);


        a.addPath(f, 1.5, 14, l);
        f.addPath(a, 1.5, 14, l);


        b.addPath(c, 2.0, 10,l);
        c.addPath(b, 2.0, 10,l);


        b.addPath(d, 1.8, 15,l);
        d.addPath(b, 1.8, 15,l);


        c.addPath(d, 1.8, 11,l);
        d.addPath(c, 1.8, 11,l);


        c.addPath(f, 1.8, 2,l);
        f.addPath(c, 1.8, 2,l);


        d.addPath(e, 2.2, 6,l);
        e.addPath(d, 2.2, 6,l);


        e.addPath(f, 2.2, 9, l);
        f.addPath(e, 2.2, 9,l);

        Itinerary route = ItineraryCalculator.CalculateRoad(a, e);

        System.out.println("Trajet trouvé :");
        System.out.println("Arrets : " + route.getStops());
        System.out.println("Distance totale : " + route.getTotalDistance() + " km");
        System.out.println("Durée totale : " + route.getTotalDuration() + " minutes");
} */

/*@Test
public void Trip2() {
    Line l = new FunicularLine("", "");
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


    a.addPath(b, 1.5, 4,l);
    b.addPath(a, 1.5, 4,l);

    a.addPath(h, 1.5, 7,l);
    h.addPath(a, 1.5, 7,l);

    b.addPath(c, 2.0, 8,l);
    c.addPath(b, 2.0, 8,l);


    b.addPath(h, 2.0, 11,l);
    h.addPath(b, 2.0, 11,l);

    c.addPath(d, 1.8, 7,l);
    d.addPath(c, 1.8, 7,l);


    c.addPath(f, 1.8, 4,l);
    f.addPath(c, 1.8, 4,l);

    c.addPath(i, 1.8, 2,l);
    i.addPath(c, 1.8, 2,l);

    d.addPath(e, 2.2, 9,l);
    e.addPath(d, 2.2, 9,l);

    d.addPath(f, 2.2, 14,l);
    f.addPath(d, 2.2, 14,l);

    f.addPath(e, 2.2, 10,l);
    e.addPath(f, 2.2, 10,l);

    g.addPath(f, 2.2, 2,l);
    f.addPath(g, 2.2, 2,l);

    g.addPath(i, 2.2, 6,l);
    i.addPath(g, 2.2, 6,l);

    h.addPath(g, 2.2, 1,l);
    g.addPath(h, 2.2, 1,l);

    h.addPath(i, 2.2, 7,l);
    i.addPath(h, 2.2, 7,l);



    Itinerary route = ItineraryCalculator.CalculateRoad(a, e);

    System.out.println("Trajet trouvé :");
    System.out.println("Arrets : " + route.getStops());
    System.out.println("Distance totale : " + route.getTotalDistance() + " km");
    System.out.println("Durée totale : " + route.getTotalDuration() + " minutes");


}

@Test
public void Trip3() {
    Line l = new FunicularLine("", "");
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

    a.addPath(b, 1.5, 7,l);
    b.addPath(a, 1.5, 7,l);


    a.addPath(c, 1.5, 9,l);
    c.addPath(a, 1.5, 9,l);


    a.addPath(f, 1.5, 14,l);
    f.addPath(a, 1.5, 14,l);


    b.addPath(c, 2.0, 10,l);
    c.addPath(b, 2.0, 10,l);



    c.addPath(f, 1.8, 2,l);
    f.addPath(c, 1.8, 2,l);

    e.addPath(d, 1.8, 2,l);
    d.addPath(e, 1.8, 2,l);



    Itinerary route = ItineraryCalculator.CalculateRoad(a, e);

    System.out.println("Trajet trouvé :");
    System.out.println("Arrets : " + route.getStops());
    System.out.println("Distance totale : " + route.getTotalDistance() + " km");
    System.out.println("Durée totale : " + route.getTotalDuration() + " minutes");


} */

}
