package fr.u_paris.gla.project.idfnetwork.view.button.transportButton;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

import fr.u_paris.gla.project.LineDisplayPanel;
import fr.u_paris.gla.project.idfnetwork.Line;
import fr.u_paris.gla.project.idfnetwork.LineType;
import fr.u_paris.gla.project.idfnetwork.view.button.TransportButton;

public class RERButton extends TransportButton {
    public RERButton() {
        super("src/main/resources/fr/u_paris/gla/project/button_icon/paris_transit_icons/20221022173330!Paris_transit_icons_-_RER.svg.png",
                50, 50, "RER");
    }

    protected void onClick() {
        showLines(LineType.RER);
    }

    @Override
    protected void showClickedLineType(List<Line> lineTypeLines) {
        removeAll();// TO DO : remove all doesn't work, debug
        JComboBox<String> comboBox = new JComboBox<>();
        for (Line line : lineTypeLines) {
            comboBox.addItem(line.getLineName());
        }
        this.add(comboBox);
        this.setVisible(true);
        // TO DO :add a button to validate the choice
        // TO DO :when the button is clicked, the selected line is displayed with the
        // function
        // TO DO :void drawLine(line)
        // TO DO :which found in Maps class
    }
}
