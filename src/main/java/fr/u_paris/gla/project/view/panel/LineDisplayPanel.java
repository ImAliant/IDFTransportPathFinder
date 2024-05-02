package fr.u_paris.gla.project.view.panel;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JPanel;

import fr.u_paris.gla.project.observer.LineDisplayPanelObserver;
import fr.u_paris.gla.project.view.button.CleanDisplayButton;
import fr.u_paris.gla.project.view.button.transport_button.BusButton;
import fr.u_paris.gla.project.view.button.transport_button.MetroButton;
import fr.u_paris.gla.project.view.button.transport_button.RERButton;
import fr.u_paris.gla.project.view.button.transport_button.TramButton;

/**
 * This panel is used to display line on the map.
 * 
 * @see JPanel
 * @see LineDisplayPanelObserver
 * 
 * @author VELANGANNI Jean-Paul
 * @author DIAMANT Alexandre
 * @author DEDEOGLU Dilara
 */
public class LineDisplayPanel extends JPanel implements LineDisplayPanelObserver {
    /** Background color of the panel. */
    private static final Color BACKGROUND_COLOR = new Color(100, 181, 246);

    /** Panel containing the buttons. */
    private JPanel buttonPanel;
    /** Panel containing the combo box and the validate button. */
    private SelectionLineAndValidatePanel comboBoxAndValidate;
    /** Panel containing the clear button. */
    private JPanel clearPanel;

    /** Buttons to select the type of transport. */
    private MetroButton metroButton;
    /** Buttons to select the type of transport. */
    private BusButton busButton;
    /** Buttons to select the type of transport. */
    private RERButton rerButton;
    /** Buttons to select the type of transport. */
    private TramButton tramButton;

    /** Button to clear the display. */
    private CleanDisplayButton clear;

    /**
     * Constructor of the line display panel.
     */
    public LineDisplayPanel() {
        super();
        
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
        setVisible(false);

        initializeComponents();

        addComponents();

        addObservers();
    }

    /**
     * Initialize the components of the panel.
     */
    private void initializeComponents() {
        initializePanel();

        initializeButtons();
    }

    /**
     * Initialize the panels of the panel.
     */
    private void initializePanel() {
        buttonPanel = new JPanel();
        comboBoxAndValidate = new SelectionLineAndValidatePanel();
        clearPanel = new JPanel();

        buttonPanel.setOpaque(false);
        comboBoxAndValidate.setOpaque(false);
        clearPanel.setOpaque(false);
    }

    /**
     * Initialize the buttons of the panel.
     */
    private void initializeButtons() {
        metroButton = new MetroButton();
        busButton = new BusButton();
        rerButton = new RERButton();
        tramButton = new TramButton();

        clear = new CleanDisplayButton();
    }

    /**
     * Add the components to the panel.
     */
    private void addComponents() {
        buttonPanel.add(metroButton);
        buttonPanel.add(busButton);
        buttonPanel.add(rerButton);
        buttonPanel.add(tramButton);

        clearPanel.add(clear);
        
        add(buttonPanel, BorderLayout.WEST);
        add(comboBoxAndValidate, BorderLayout.CENTER);
        add(clearPanel, BorderLayout.EAST);
    }

    /**
     * Add the observers to the buttons.
     */
    private void addObservers() {
        metroButton.addObserver(comboBoxAndValidate.getComboBox());
        busButton.addObserver(comboBoxAndValidate.getComboBox());
        rerButton.addObserver(comboBoxAndValidate.getComboBox());
        tramButton.addObserver(comboBoxAndValidate.getComboBox());

        clear.addObserver(comboBoxAndValidate.getComboBox());
    }

    /**
     * Get the metro button.
     * 
     * @return the metro button
     */
    public CleanDisplayButton getClearButton(){ 
        return clear; 
    }

    @Override
    public void updateVisibility() {
        setVisible(!isVisible());
    }

    /**
     * Get the combo box and the validate button.
     * 
     * @return the combo box and the validate button
     */
    public SelectionLineAndValidatePanel getComboBoxAndValidate() {
        return comboBoxAndValidate;
    }
}
