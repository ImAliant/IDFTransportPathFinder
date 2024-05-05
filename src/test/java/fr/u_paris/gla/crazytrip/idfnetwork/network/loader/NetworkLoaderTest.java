package fr.u_paris.gla.crazytrip.idfnetwork.network.loader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.u_paris.gla.crazytrip.idfnetwork.network.Network;

class NetworkLoaderTest {
    private static final String TEST_PATH = "src/test/resources/fr/u_paris/gla/crazytrip/idfnetwork/test_output.csv";

    private Network instance = Network.getInstance();
    private static File file;

    @BeforeAll
    static void setupFile() {
        try {
            file = new File(TEST_PATH);
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
