package fr.u_paris.gla.project.idfnetwork.view.button;

import javax.swing.Icon;
import javax.swing.SwingUtilities;

import fr.u_paris.gla.project.ResearchPanel;
import fr.u_paris.gla.project.utils.IconUtils;

public class OpenResearchPanelButton extends MiniButton {
    private static final String PATH = "src/main/resources/fr/u_paris/gla/project/button_logo/loupe_logo.png";
    
    public OpenResearchPanelButton(int x, int y, ResearchPanel panel) {
        super(x, y);

        Icon icon = IconUtils.createIcon(PATH, WIDTH, HEIGHT);
        setIcon(icon);

        addActionListener(e -> SwingUtilities.invokeLater(() ->
            panel.setVisible(!panel.isVisible())
        ));
    }
}
