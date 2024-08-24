package fr.u_paris.gla.crazytrip.gui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.button.ResearchButton;
import fr.u_paris.gla.crazytrip.gui.observer.PanelObserver;

public class ResearchPanel extends JPanel implements PanelObserver {
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);
    private static final int WIDTH = 250;
    private static final int MARGIN = 10;

    private transient DepartureField departureField;
    private transient ArrivalField arrivalField;

    private transient ResearchButton researchButton;

    private GridBagConstraints constraints;

    public ResearchPanel() {
        super();

        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension(WIDTH, getHeight()));
        setVisible(false);

        initLayout();
        initComponents();
        addComponents();
    }

    @Override
    public void updateVisibility() {
        setVisible(!isVisible());
    }

    private void initLayout() {
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets.set(0, MARGIN, 0, MARGIN);
        constraints.gridx = 0;
        constraints.gridy = 0;
    }

    private void initComponents() {
        departureField = new DepartureField();
        arrivalField = new ArrivalField();
        researchButton = new ResearchButton();
    }

    private void addComponents() {
        add(departureField);
        add(arrivalField);

        constraints.insets.set(MARGIN, MARGIN, MARGIN, MARGIN);
        add(researchButton);
    }

    @Override
    public Component add(Component comp) {
        super.add(comp, constraints);
        constraints.gridy++;

        return comp;
    }

    public DepartureField getDepartureField() {
        return departureField;
    }

    public ArrivalField getArrivalField() {
        return arrivalField;
    }
}
