package fr.u_paris.gla.crazytrip.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * This class provides utility methods for managing icons in a GUI application.
 * It offers a functionality to create an {@code ImageIcon} from an image file, 
 * resizing it to specified dimensions.
 * 
 * @author Diamant Alexandre
 */
public class IconUtils {
     /**
     * Private constructor to prevent instantiation of utility class.
     */
    private IconUtils() {}

    /**
     * Creates an ImageIcon from the specified path, resizing it to the given width and height.
     * 
     * @param path the path to the image file
     * @param width the desired width of the icon
     * @param height the desired height of the icon
     * @return an ImageIcon of the specified dimensions, or null if the image could not be read or processed
     * 
     * @throws IOException if an error occurs during reading the image file
     */
    public static ImageIcon createIcon(String path, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            BufferedImage resized = new BufferedImage(width, height, img.getType());
            resized.createGraphics().drawImage(img, 0, 0, width, height, null);
            return new ImageIcon(resized);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
