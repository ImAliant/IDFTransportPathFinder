package fr.u_paris.gla.crazytrip.gui.maps;

import javax.swing.Icon;

import fr.u_paris.gla.crazytrip.model.Station;
import fr.u_paris.gla.crazytrip.utils.IconUtils;

public class DefaultStationButton extends StationButton {
    private static final int ZOOM_THRESHOLD = 0;

    public DefaultStationButton(Station station) {
        super(station);

        try {
            Icon icon = IconUtils.createIcon(path, WIDTH, HEIGHT);
            setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateVisibility(int zoom) {
        setVisible(zoom >= ZOOM_THRESHOLD);
    }
}
