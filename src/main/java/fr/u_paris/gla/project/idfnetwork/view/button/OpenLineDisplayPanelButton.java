package fr.u_paris.gla.project.idfnetwork.view.button;

import javax.swing.Icon;
import javax.swing.SwingUtilities;

import fr.u_paris.gla.project.LineDisplayPanel;
import fr.u_paris.gla.project.utils.IconUtils;


public class OpenLineDisplayPanelButton extends MiniButton {
    private static final String PATH = "";

    public OpenLineDisplayPanelButton(int x, int y/* , LineDisplayPanel panel */) {
        super(x, y);

        /* Icon icon = IconUtils.createIcon(PATH, WIDTH, HEIGHT);
        setIcon(icon); */

        addActionListener(e -> /* SwingUtilities.invokeLater(() ->
            panel.setVisible(!panel.isVisible())
        ) */ System.out.println("OpenLineDisplayPanelButton"));
    }
}
