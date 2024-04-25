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

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }



//        System.out.println("Trajet trouvé :");
//        System.out.println("Départ : " + route.getStops().get(0).getStopName());
//        for ( int i = 0; i < route.getLines().size() - 1; i++ ) {
//            // if (!route.getLines().get(i).equals(route.getLines().get(i+1))  )
//            System.out.println("Prendre : " + route.getLines().get(i) + " -  Arret : " + route.getStops().get(i+1).getStopName());
//        }
//        System.out.println("Prendre : " + route.getLines().get(route.getLines().size() - 1) + " -  Arrivé à destination : " + route.getStops().get(route.getStops().size() - 1).getStopName());
//        System.out.println("Distance totale : " + route.getTotalDistance() + " km");
//        System.out.println("Durée totale : " + route.getTotalDuration() + " secondes");
    }

    @Test
    public void Trip3() {


        Stop a = network.findSameStop("Charles de Gaulle - Etoile",	2.295927507409278,48.87494579313413);

        Stop b =  network.findSameStop("Alexandre Dumas", 2.3947127337554788, 48.85624467843737);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }


//        System.out.println("Trajet trouvé :");
//        System.out.println("Départ : " + route.getStops().get(0).getStopName());
//        for ( int i = 0; i < route.getLines().size() - 1; i++ ) {
//            // if (!route.getLines().get(i).equals(route.getLines().get(i+1))  )
//            System.out.println("Prendre : " + route.getLines().get(i) + " -  Arret : " + route.getStops().get(i+1).getStopName());
//        }
//        System.out.println("Prendre : " + route.getLines().get(route.getLines().size() - 1) + " -  Arrivé à destination : " + route.getStops().get(route.getStops().size() - 1).getStopName());
//        System.out.println("Distance totale : " + route.getTotalDistance() + " km");
//        System.out.println("Durée totale : " + route.getTotalDuration() + " secondes");
    }


    @Test
    public void Trip2() {

        Stop a = network.findSameStop("Hôpital Antoine Béclère",2.2537062315154497,  48.78670798237926);
        Stop b = network.findSameStop("Gare de Chaville - Vélizy",2.1851142141000532, 48.79922054281981);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }

//        System.out.println("Trajet trouvé :");
//        System.out.println("Départ : " + route.getStops().get(0).getStopName());
//        for ( int i = 0; i < route.getLines().size() - 1; i++ ) {
//            // if (!route.getLines().get(i).equals(route.getLines().get(i+1))  )
//                System.out.println("Prendre : " + route.getLines().get(i) + " -  Arret : " + route.getStops().get(i+1).getStopName());
//        }
//        System.out.println("Prendre : " + route.getLines().get(route.getLines().size() - 1) + " -  Arrivé à destination : " + route.getStops().get(route.getStops().size() - 1).getStopName());
//        System.out.println("Distance totale : " + route.getTotalDistance() + " km");
//        System.out.println("Durée totale : " + route.getTotalDuration() + " secondes");
    }

    @Test
    public void Trip4() {


        Stop a = network.findSameStop("Bibliothèque François Mitterrand"	, 2.376487371168301,48.829925765928905);

        Stop b =  network.findSameStop("Olympiades"	, 2.366992317018834,48.82703387034927);
        System.out.println("départ : " + a);
        System.out.println("départ : " + b);
        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }


//        System.out.println("Trajet trouvé :");
//        System.out.println("Départ : " + route.getStops().get(0).getStopName());
//        for ( int i = 0; i < route.getLines().size() - 1; i++ ) {
//            // if (!route.getLines().get(i).equals(route.getLines().get(i+1))  )
//            System.out.println("Prendre : " + route.getLines().get(i) + " -  Arret : " + route.getStops().get(i+1).getStopName());
//        }
//        System.out.println("Prendre : " + route.getLines().get(route.getLines().size() - 1) + " -  Arrivé à destination : " + route.getStops().get(route.getStops().size() - 1).getStopName());
//        System.out.println("Distance totale : " + route.getTotalDistance() + " km");
//        System.out.println("Durée totale : " + route.getTotalDuration() + " secondes");
    }


    @Test
    public void Trip5() {

        Stop c = new Stop("Start",2.2537062315154495,  48.78670798237922);
        Stop d = new Stop("Destination",2.1851142141000534, 48.79922054281987);

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
    public void Trip6() {

        Stop a = network.findSameStop("Bibliothèque François Mitterrand"	, 2.376487371168301,48.829925765928905);

        Stop b =  network.findSameStop("Olympiades"	, 2.366992317018834,48.82703387034927);

        Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
        assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        assertEquals(a, route.getStops().get(0));

    }

    @Test
    public void Trip7() {

        Stop a = network.findSameStop("Cugny", 2.769840230139393, 48.318574679957464);

        Stop b =  network.findSameStop("Stade Finaltéri", 2.1419185396482656, 48.90079992562314);

        Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
        assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        assertEquals(a, route.getStops().get(0));

    }


    @Test
    public void Trip8() {

        Stop a = Network.findSameStop("Centre de Loisirs", 2.1936579249797945, 49.00041409292872);

        Stop b =  Network.findSameStop("Gare de Meulan - Hardricourt", 1.9004020172260057, 49.00578318132421);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
            assertEquals(a, route.getStops().get(0));
        }
        else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip9() {

        Stop a = network.findSameStop("Jules Vallès", 2.4297566084970623, 48.63557037115407);

        Stop b =  network.findSameStop("Hautes Bovettes", 1.7901996708123653, 48.98755505046128);

        if (a != null && b != null) {
        Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
        assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        assertEquals(a, route.getStops().get(0));
        }
        else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip10() {

        Stop a = network.findSameStop("Maison des Arts", 1.7394336020145267, 48.98928968460954);

        Stop b =  network.findSameStop("Cour Saint-Emilion", 2.386617850214058, 48.833319303843425);

        Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
        assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        assertEquals(a, route.getStops().get(0));

    }

    @Test
    public void Trip11() {

        Stop a = network.findSameStop("Mélanie Bonis", 2.541814399940484, 48.71493825728372);
        Stop b = network.findSameStop("Saint-Germain - Odéon", 2.339703371802993, 48.852158501395905);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
            assertEquals(a, route.getStops().get(0));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip12() {

        Stop a = network.findSameStop("Glacière - Auguste Blanqui", 2.3434048590849215, 48.83095387731193);
        Stop b = network.findSameStop("Danton", 2.4066174610492608, 48.92276400906354);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
            assertEquals(a, route.getStops().get(0));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip13() {

        Stop a = network.findSameStop("Fay", 2.6276564902041217, 48.47535876202672);
        Stop b = network.findSameStop("Place de la Gare / Villiers - Neauphle - Pontchartrain", 1.8768643305540473, 48.81542996173005);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
            assertEquals(a, route.getStops().get(0));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip14() {

        Stop a = network.findSameStop("Le Hameau", 2.0363990555042157, 48.77424283814268);
        Stop b = network.findSameStop("Coteaux de l'Orge", 2.3626624490510024, 48.66530998182266);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
            assertEquals(a, route.getStops().get(0));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip15() {

        Stop a = network.findSameStop("Gare Saint-Lazare - Rome", 2.323655927472191, 48.875802659065286);
        Stop b = network.findSameStop("Noveos", 2.250066396091131, 48.77668362906781);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
            assertEquals(a, route.getStops().get(0));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip16() {

        Stop a = network.findSameStop("Quai des Vallées 3", 2.736945403522953, 48.48741802638061);
        Stop b = network.findSameStop("Sybilles", 2.1709721224181573, 48.43144774761372);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip17() {

        Stop a = network.findSameStop("La Poste", 2.1465041495787114, 48.435484814857524);
        Stop b = network.findSameStop("Bas de Pringy", 2.5510476454372513, 48.527671279709345);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip18() {

        Stop a = network.findSameStop("Les Estudines", 2.427550820898078, 48.62009700288923);
        Stop b = network.findSameStop("Montflix", 2.1074131671009715, 48.51288347517418);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip19() {

        Stop a = network.findSameStop("8 Mai 1945", 2.569976416102219, 48.95449532033728);
        Stop b = network.findSameStop("Gare de Vigneux-sur-Seine", 2.4154254645194997, 48.70842258103002);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip20() {

        Stop a = network.findSameStop("Gare Saint-Lazare", 2.3244386552397684, 48.87747568635212);
        Stop b = network.findSameStop("Épinay - Orgemont", 2.2946559643463096, 48.95530504015839);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip21() {

        Stop a = network.findSameStop("Poste", 1.874247444816905, 48.963758934234725);
        Stop b = network.findSameStop("Le Pérou", 1.449730588616204, 48.861071646373844);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip22() {

        Stop a = network.findSameStop("Rue des Vignes", 2.547926018101929, 49.00452851569328);
        Stop b = network.findSameStop("Carrefour 307", 1.9238716299899248, 48.88637595883795);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip23() {

        Stop a = network.findSameStop("Transformateur", 1.8731100747326357, 48.92297212471166);
        Stop b = network.findSameStop("Gallieni", 2.4174032853043643, 48.86502696086302);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip24() {

        Stop a = network.findSameStop("Louis Perrein", 2.406069933051497, 49.00181516173552);
        Stop b = network.findSameStop("Ikéa", 2.2123042918668223, 48.98751676696912);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }
    @Test
    public void Trip25() {

        Stop a = network.findSameStop("Victor Hugo", 1.9180621892194942, 48.95002490751078);
        Stop b = network.findSameStop("République", 1.9223798672222463, 48.94857091177559);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip26() {

        Stop a = network.findSameStop("Gare des Mureaux (C3)", 1.9127047243854884, 48.99335920190281);
        Stop b = network.findSameStop("Avenue Marcel Martinie", 2.292613030042328, 48.82340624455537);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }
    @Test
    public void Trip27() {

        Stop a = network.findSameStop("Guy Môquet", 2.331226337476044, 48.66528168056854);
        Stop b = network.findSameStop("Châtelet - Quai de Gesvres", 2.348380846441519, 48.85681076199451);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }
    @Test
    public void Trip28() {

        Stop a = network.findSameStop("ZA du Bois des Roches", 2.08648282840628, 48.74129335777861);
        Stop b = network.findSameStop("Rue de Metz", 3.082025052850925, 48.94736102970073);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip29() {

        Stop a = network.findSameStop("Hoche - Jean Lolive", 2.400650408464748, 48.89107874997465);
        Stop b = network.findSameStop("Angélique Compoint", 2.3371023952971557, 48.89806429404234);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip30() {

        Stop a = network.findSameStop("Cluny - La Sorbonne", 2.3450218824744566, 48.850793731720536);
        Stop b = network.findSameStop("Fontaine au Roi", 2.7507751831555542, 48.87804737993599);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }

    @Test
    public void Trip31() {

        Stop a = network.findSameStop("Henri IV / Île-de-France", 2.573327793879671, 48.935460938366084);
        Stop b = network.findSameStop("Parc du Château", 2.419191509329898, 48.69361774506078);

        if (a != null && b != null) {
            Itinerary route = ItineraryCalculator.CalculateRoad(a, b);
            assertEquals(a, route.getStops().get(0));
            assertEquals(b, route.getStops().get(route.getStops().size() - 1));
        } else {
            fail("L'un des arrêts est introuvable");
        }
    }




    @Test
    public void findSameStop(){
        Stop stop = network.findSameStop("Alexandre Dumas",2.3945914186721278,48.856199097341126);
        double distance = Math.sqrt(Math.pow(48.85624467843737 - stop.getLatitude(), 2) + Math.pow(2.3947127337554788 - stop.getLongitude(), 2));
        double dist = GPS.distance(48.85624467843737,2.3947127337554788,stop.getLatitude(),stop.getLongitude());
        System.out.println(stop);
        System.out.println("Distance gps:" + dist);
        System.out.println("Distance math:" + distance);
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
