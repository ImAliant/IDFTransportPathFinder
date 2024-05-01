package fr.u_paris.gla.project;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JPanel;

import fr.u_paris.gla.project.idfnetwork.view.button.CleanDisplayButton;
import fr.u_paris.gla.project.idfnetwork.view.button.transport_button.BusButton;
import fr.u_paris.gla.project.idfnetwork.view.button.transport_button.MetroButton;
import fr.u_paris.gla.project.idfnetwork.view.button.transport_button.RERButton;
import fr.u_paris.gla.project.idfnetwork.view.button.transport_button.TramButton;
import fr.u_paris.gla.project.observer.LineDisplayPanelObserver;

public class LineDisplayPanel extends JPanel implements LineDisplayPanelObserver {
    private static final Color BACKGROUND_COLOR = new Color(100, 181, 246);

    private JPanel buttonPanel;
    private SelectionLineAndValidatePanel comboBoxAndValidate;
    private JPanel clearPanel;

    private MetroButton metroButton;
    private BusButton busButton;
    private RERButton rerButton;
    private TramButton tramButton;

    private CleanDisplayButton clear;

    public LineDisplayPanel() {
        super();
        
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
        setVisible(false);

        initializeComponents();

        addComponents();

        addObservers();
    }

    private void initializeComponents() {
        initializePanel();

        initializeButtons();
    }

    private void initializePanel() {
        buttonPanel = new JPanel();
        comboBoxAndValidate = new SelectionLineAndValidatePanel();
        clearPanel = new JPanel();

        buttonPanel.setOpaque(false);
        comboBoxAndValidate.setOpaque(false);
        clearPanel.setOpaque(false);
    }

    private void initializeButtons() {
        metroButton = new MetroButton();
        busButton = new BusButton();
        rerButton = new RERButton();
        tramButton = new TramButton();

        clear = new CleanDisplayButton();
    }

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

    private void addObservers() {
        metroButton.addObserver(comboBoxAndValidate.getComboBox());
        busButton.addObserver(comboBoxAndValidate.getComboBox());
        rerButton.addObserver(comboBoxAndValidate.getComboBox());
        tramButton.addObserver(comboBoxAndValidate.getComboBox());

        clear.addObserver(comboBoxAndValidate.getComboBox());
    }

    public CleanDisplayButton getClearButton(){ return clear; }

    @Override
    public void updateVisibility() {
        setVisible(!isVisible());
    }

    public SelectionLineAndValidatePanel getComboBoxAndValidate() {
        return comboBoxAndValidate;
    }
}
