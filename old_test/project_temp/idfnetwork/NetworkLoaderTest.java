package fr.u_paris.gla.project_temp.idfnetwork;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fr.u_paris.gla.project.App;
import fr.u_paris.gla.project.idfnetwork.network.Network;
import fr.u_paris.gla.project_temp.idfnetwork.stop.Stop;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NetworkLoaderTest {

    @BeforeAll
    static void setup(){
        App.initNetwork();
    }

    @Test
    public void getStopFromStopArray(){
        List<Stop> stop = Network.getInstance().getStops();
        assertNotNull(stop);
        System.out.println(stop.get(0));
    }


    @Test
    public void getStopInNetwork(){
        assertNotNull(Network.getInstance().findStop("Petit Morin",3.1225367926082375,48.94340574072161));
    }
    
}
