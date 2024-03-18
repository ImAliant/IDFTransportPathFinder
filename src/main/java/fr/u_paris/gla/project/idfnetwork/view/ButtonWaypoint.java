package fr.u_paris.gla.project.idfnetwork.view;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Cursor;

import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class ButtonWaypoint extends JLabel {
    private static final long serialVersionUID = 1L;

    public ButtonWaypoint() {
        try {
            BufferedImage img = ImageIO.read(new File("src/main/resources/fr/u_paris/gla/project/stop_logo.png"));
            BufferedImage resized = new BufferedImage(20, 20, img.getType());
            resized.createGraphics().drawImage(img, 0, 0, 20, 20, null);
            setIcon(new ImageIcon(resized));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
