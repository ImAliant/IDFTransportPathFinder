package fr.u_paris.gla.project.idfnetwork.network.loader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.u_paris.gla.project.idfnetwork.network.Network;

class NetworkLoaderTest {
    private Network instance = Network.getInstance();
    private static File file;

    @BeforeAll
    static void setupFile() {
        try {
            file = new File("src/test/resources/idfnetwork/test_output.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setup() {
        instance.clear();
    }

    @Test
    void loadTest() {
        NetworkLoader.load(file);

        assertEquals(2, instance.getLines().size(),
        "The number of lines in the network should be 2, but it is " 
        + instance.getLines().size());

        assertEquals(8, instance.getStops().size(),
        "The number of stations in the network should be 8, but it is "
        + instance.getStops().size());
    }
}
