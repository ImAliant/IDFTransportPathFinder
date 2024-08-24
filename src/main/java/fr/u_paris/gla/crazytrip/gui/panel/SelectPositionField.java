package fr.u_paris.gla.crazytrip.gui.panel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.button.SearchByPositionButton;
import fr.u_paris.gla.crazytrip.gui.combobox.SuggestionComboBox;
import fr.u_paris.gla.crazytrip.gui.label.StyleLabel;
import fr.u_paris.gla.crazytrip.gui.observer.SuggestionSelectionObserver;
import fr.u_paris.gla.crazytrip.gui.textfield.LocationTextField;
import fr.u_paris.gla.crazytrip.model.Station;

public abstract class SelectPositionField extends JPanel implements SuggestionSelectionObserver {
    private static final int MARGIN = 10;
    
    private StyleLabel label;
    private LocationTextField textField;
    private SearchByPositionButton button;
    private SuggestionComboBox comboBox;

    private GridBagConstraints constraints;

    private transient Station selectedStation;

    protected SelectPositionField(String labelText) {
        super();

        setOpaque(false);

        initLayout();

        initComponents(labelText);
        addComponents();
        addObservers();
    }

    private void initLayout() {
        setLayout(new GridBagLayout());

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
    }

    private void initComponents(String labelText) {
        label = new StyleLabel(labelText);
        comboBox = new SuggestionComboBox();
        textField = new LocationTextField(comboBox);
        button = new SearchByPositionButton();
    }

    private void addComponents() {
        add(label);
        add(textField);
        add(button);

        constraints.insets.set(MARGIN, MARGIN, MARGIN, MARGIN);
        add(comboBox);
    }

    @Override
    public Component add(Component comp) {
        super.add(comp, constraints);
        constraints.gridy++;

        return comp;
    }

    @Override
    public void update(Station station) {
        clearSelectedStation();
        selectedStation = station;
    }

    public void addObservers() {
        comboBox.addObserver(this);
        comboBox.addObserver(textField);
    }

    public Station getSelectedStation() {
        return selectedStation;
    }

    public void clearSelectedStation() {
        selectedStation = null;
    }

    public LocationTextField getTextField() {
        return textField;
    }

    public SearchByPositionButton getButton() {
        return button;
    }

    public SuggestionComboBox getComboBox() {
        return comboBox;
    }
}
