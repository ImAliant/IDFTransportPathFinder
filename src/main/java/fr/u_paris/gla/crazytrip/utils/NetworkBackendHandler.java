package fr.u_paris.gla.crazytrip.utils;

import java.io.IOException;

import fr.u_paris.gla.crazytrip.idfm.IDFMNetworkExtractor;

/**
 * Utility class to handle network backend
 */
public class NetworkBackendHandler {
    /** Prevent instantiation */
    private NetworkBackendHandler() {}

    /**
     * Extract network data
     * @throws IOException if an I/O error occurs
     */
    public static void extraction() throws IOException {
        IDFMNetworkExtractor.extract();
    }
}
