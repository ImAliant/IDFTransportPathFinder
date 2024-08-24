package fr.u_paris.gla.crazytrip.gui.panel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import fr.u_paris.gla.crazytrip.gui.combobox.SuggestionComboBox;
import fr.u_paris.gla.crazytrip.gui.label.StyleLabel;
import fr.u_paris.gla.crazytrip.gui.observer.SuggestionSelectionObserver;
import fr.u_paris.gla.crazytrip.gui.textfield.LocationTextField;
import fr.u_paris.gla.crazytrip.model.Node;

public abstract class SelectPositionField extends JPanel implements SuggestionSelectionObserver {
    private static final int MARGIN = 10;
    
    private StyleLabel label;
    private LocationTextField textField;
    private SuggestionComboBox comboBox;

    private GridBagConstraints constraints;

    private transient Node selectedNode;

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
    }

    private void addComponents() {
        add(label);
        add(textField);

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
    public void update(Node station) {
        clearSelectedStation();
        selectedNode = station;
    }

    public void addObservers() {
        comboBox.addObserver(this);
        comboBox.addObserver(textField);
    }

    public Node getSelectedStation() {
        return selectedNode;
    }

    public void clearSelectedStation() {
        selectedNode = null;
    }

    public LocationTextField getTextField() {
        return textField;
    }

    public SuggestionComboBox getComboBox() {
        return comboBox;
    }
}
