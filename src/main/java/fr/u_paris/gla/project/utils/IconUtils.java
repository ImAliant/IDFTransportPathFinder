package fr.u_paris.gla.project.utils;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class IconUtils {
    private IconUtils() {}

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
