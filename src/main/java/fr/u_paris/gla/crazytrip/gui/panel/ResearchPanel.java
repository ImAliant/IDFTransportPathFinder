package fr.u_paris.gla.crazytrip.gui.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.button.ResearchButton;
import fr.u_paris.gla.crazytrip.gui.label.ErrorLabel;
import fr.u_paris.gla.crazytrip.gui.observer.PanelObserver;

/**
 * Panel where the user can do itinerary research.
 * 
 * <p>There is two fields, one for the departure and one for the arrival.</p>
 * <p>When the research button is clicked, it run the algorithm from departure to arrival. 
 * If there is an error on the algorithm, an error message will be printed</p>
 * 
 * @see PanelObserver
 * @see DepartureField
 * @see ArrivalField
 * @see ErrorLabel
 * @see ResearchButton
 */
public class ResearchPanel extends JPanel implements PanelObserver {
    /** Background color of the panel */
    private static final Color BACKGROUND_COLOR = new Color(104, 157, 113);
    /** Width of the panel */
    private static final int WIDTH = 250;
    /** Margin between components */
    private static final int MARGIN = 10;

    /** Used to selected the departure of an itinerary.*/
    private transient DepartureField departureField;
    /** Used to selected the arrival of an itinerary. */
    private transient ArrivalField arrivalField;
    /** Label to let know the user if there is a problem on the use of the research */
    private transient ErrorLabel errorLabel;
    /** Button to confirm the research */
    private transient ResearchButton researchButton;

    /** Constraints for the components of the panel. */
    private GridBagConstraints constraints;

    /**
     * Constructor
     */
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

    /** Initialize the layout of panel */
    private void initLayout() {
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets.set(0, MARGIN, 0, MARGIN);
        constraints.gridx = 0;
        constraints.gridy = 0;
    }

    /** Initialize the components of the panel */
    private void initComponents() {
        departureField = new DepartureField();
        arrivalField = new ArrivalField();
        researchButton = new ResearchButton(departureField, arrivalField);

        errorLabel = new ErrorLabel();
    }

    /** Add the components on the panel and set the contraints */
    private void addComponents() {
        add(departureField);
        add(arrivalField);

        constraints.insets.set(MARGIN, MARGIN, MARGIN, MARGIN);
        add(researchButton);
        
        add(errorLabel);
    }

    /** Override add. Update the y grid for each component addition */
    @Override
    public Component add(Component comp) {
        super.add(comp, constraints);
        constraints.gridy++;

        return comp;
    }

    /**
     * Getter for the departure field
     * 
     * @return the departure field
     * 
     * @see DepartureField
     */
    public DepartureField getDepartureField() {
        return departureField;
    }

    /**
     * Getter for the arrival field
     * 
     * @return the arrival field
     * 
     * @see ArrivalField
     */
    public ArrivalField getArrivalField() {
        return arrivalField;
    }

    /**
     * Getter for the research button
     * 
     * @return the research button
     * 
     * @see ResearchButton
     */
    public ResearchButton getResearchButton() {
        return researchButton;
    }

    /**
     * Getter for the error label
     * 
     * @return the error label
     * 
     * @see ErrorLabel
     */
    public ErrorLabel getErrorLabel() {
        return errorLabel;
    }
}
