package fr.u_paris.gla.project.idfnetwork.view.waypoint.button;

import javax.swing.JLabel;

import fr.u_paris.gla.project.idfnetwork.LineType;
import fr.u_paris.gla.project.idfnetwork.stop.Stop;
import fr.u_paris.gla.project.idfnetwork.view.listener.ButtonListener;

import javax.swing.ImageIcon;

import java.io.File;
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public abstract class StopButtonWaypoint extends JLabel {
    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_PATH = "src/main/resources/fr/u_paris/gla/project/stop_logo/stop_logo.png";

    protected String iconPath = DEFAULT_PATH;
    protected transient Stop stop;
    protected int zoom;

    public static StopButtonWaypoint createButton(Stop stop, LineType lineType) {
        if (lineType == null) {
            return new DefaultStopButtonWaypoint(stop);
        }
        
        StopButtonWaypoint button;
        switch (lineType) {
            case BUS:
                button = new BusStopButtonWaypoint(stop);
                break;
            case RER:
                button = new RERStopButtonWaypoint(stop);
                break;
            case TRAMWAY:
                button = new TramStopButtonWaypoint(stop);
                break;
            case FUNICULAIRE:
                button = new FunicularStopButtonWaypoint(stop);
                break;
            case METRO:
                button = new MetroStopButtonWaypoint(stop);
                break;
            default:
                button = new DefaultStopButtonWaypoint(stop);
                break;
        }

        return button;
    }

    protected StopButtonWaypoint(Stop stop) {
        this.stop = stop;
        setCursor(new Cursor(Cursor.HAND_CURSOR));
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
            System.exit(1);
        }
    }

    public abstract void updateVisibility(int zoom);

    protected void addListener() {
        addMouseListener(new ButtonListener(stop));
    }
}
