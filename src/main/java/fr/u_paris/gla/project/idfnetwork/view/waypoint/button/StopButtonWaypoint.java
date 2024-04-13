package fr.u_paris.gla.project.idfnetwork.view.waypoint.button;

import javax.swing.JLabel;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;
import fr.u_paris.gla.project.idfnetwork.view.listener.ButtonListener;

import javax.swing.ImageIcon;
import java.awt.Cursor;

import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class StopButtonWaypoint extends JLabel {
    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_PATH = "src/main/resources/fr/u_paris/gla/project/stop_logo.png";

    protected String iconPath = DEFAULT_PATH;

    private final transient Stop stop;

    public StopButtonWaypoint(Stop stop) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.stop = stop;

        addListener();
    }

    protected void setupIcon() {
        try {
            BufferedImage img = ImageIO.read(new File(iconPath));
            BufferedImage resized = new BufferedImage(20, 20, img.getType());
            resized.createGraphics().drawImage(img, 0, 0, 20, 20, null);
            setIcon(new ImageIcon(resized));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addListener() {
        addMouseListener(new ButtonListener(stop));
    }
}
