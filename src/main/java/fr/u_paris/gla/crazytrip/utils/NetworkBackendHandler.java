package fr.u_paris.gla.crazytrip.utils;

import java.io.IOException;

import fr.u_paris.gla.crazytrip.idfm.IDFMNetworkExtractor;

public class NetworkBackendHandler {
    private NetworkBackendHandler() {}

    public static void extraction() throws IOException {
        IDFMNetworkExtractor.extract();
    }
}
