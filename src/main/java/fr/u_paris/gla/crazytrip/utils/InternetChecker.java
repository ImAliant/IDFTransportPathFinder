package fr.u_paris.gla.crazytrip.utils;

import java.io.IOException;

import java.net.Socket;
import java.net.SocketAddress;
import java.net.InetSocketAddress;

/**
 * Utility class to check internet connection
 */
public class InternetChecker {
    /** Prevent instantiation */
    private InternetChecker() {}

    /**
     * Check if internet is reachable
     * @return true if internet is reachable, false otherwise
     */
    public static boolean isInternetReachable() {
        try (Socket socket = new Socket()) {
            SocketAddress address = new InetSocketAddress("www.google.com", 80);
            socket.connect(address, 2000);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
