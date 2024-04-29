package fr.u_paris.gla.project.idfnetwork.view.button.transportButton;

import java.util.List;

import fr.u_paris.gla.project.idfnetwork.Line;
import fr.u_paris.gla.project.idfnetwork.view.button.TransportButton;

public class PlansButton extends TransportButton {
    public PlansButton() {
        super("src/main/resources/fr/u_paris/gla/project/button_icon/plan.png", 50, 50, "Plans");
        addActionListener(e -> onClick());
    }

    protected void onClick() {
        // Logic to display metro lines
    }

    @Override
    protected void showClickedLineType(List<Line> lineTypeLines) {
        // TODO
    }

}