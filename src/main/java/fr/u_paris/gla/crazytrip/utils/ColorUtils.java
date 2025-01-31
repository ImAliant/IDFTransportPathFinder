package fr.u_paris.gla.crazytrip.utils;

import java.awt.Color;

/**
 * Utility class to manipulate colors
 */
public class ColorUtils {
    /** Prevent instantiation */
    private ColorUtils() {}

    /**
     * Decode a color from a hexadecimal string
     * @param color hexadecimal string representing a color
     * @return Color object representing the color
     */
    public static Color decodeColor(String color) {
        return Color.decode("#" + color);
    }

    /**
     * Convert a color to a hexadecimal string
     * @param color Color object to convert
     * @return hexadecimal string representing the color
     */
    public static String toHex(Color color) {
        return Integer.toHexString(color.getRGB()).substring(2);
    }
}
