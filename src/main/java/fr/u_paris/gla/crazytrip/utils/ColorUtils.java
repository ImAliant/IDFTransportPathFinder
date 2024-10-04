package fr.u_paris.gla.crazytrip.utils;

import java.awt.Color;

public class ColorUtils {
    private ColorUtils() {}

    public static Color decodeColor(String color) {
        return Color.decode("#" + color);
    }

    public static String toHex(Color color) {
        return Integer.toHexString(color.getRGB()).substring(2);
    }
}
