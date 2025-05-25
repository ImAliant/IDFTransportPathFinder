package fr.u_paris.gla.crazytrip.gui.panel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import org.jxmapviewer.viewer.GeoPosition;

import fr.u_paris.gla.crazytrip.gui.combobox.SuggestionComboBox;
import fr.u_paris.gla.crazytrip.gui.label.StyleLabel;
import fr.u_paris.gla.crazytrip.gui.observer.SelectPositionObserver;
import fr.u_paris.gla.crazytrip.gui.textfield.LocationTextField;
import fr.u_paris.gla.crazytrip.model.Node;
import fr.u_paris.gla.crazytrip.model.PersonalizedNode;

/**
 * Abstract class. Field to select a position on a map.
 * 
 * <p>Composed of a label, a text field where the location will be written and a combobox to show suggestion from the text writted by the user.</p>
 * 
 * @see SelectPositionObserver
 * @see StyleLabel
 * @see LocationTextField
 * @see SuggestionComboBox
 */
public abstract class SelectPositionField extends JPanel implements SelectPositionObserver {
    /** Margin between components */
    private static final int MARGIN = 10;
    
    /** Label of the field */
    private StyleLabel label;
    /** Field to write the localisation */
    private LocationTextField textField;
    /** Suggestion of stations */
    private SuggestionComboBox comboBox;
    /** Constraints of the panel */
    private GridBagConstraints constraints;

    /** The selected node */
    private transient Node selectedNode;

    /**
     * Constructor
     * 
     * @param labelText the text of the panel
     */
    protected SelectPositionField(String labelText) {
        super();

        setOpaque(false);

        initLayout();

        initComponents(labelText);
        addComponents();
        addObservers();
    }

    /** Initialize the layout of the panel */
    private void initLayout() {
        setLayout(new GridBagLayout());

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
    }

    /**
     * Initialize the components of the panel.
     * 
     * @param labelText the text of the label
     */
    private void initComponents(String labelText) {
        label = new StyleLabel(labelText);
        comboBox = new SuggestionComboBox();
        textField = new LocationTextField(comboBox);
    }

    /**
     * Add the components to the panel and set the constraints.
     */
    private void addComponents() {
        add(label);
        add(textField);

        constraints.insets.set(MARGIN, MARGIN, MARGIN, MARGIN);
        add(comboBox);
    }

    /**
     * Override add. Update the y grid for each component addition.
     * 
     * @param comp the component to add
     * @return the component added
     */
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

    @Override
    public void update(GeoPosition position) {
        clearSelectedStation();
        selectedNode = new PersonalizedNode(position.getLatitude(), position.getLongitude());
    }

    /**
     * Add the observers to the combobox.
     */
    public void addObservers() {
        comboBox.addObserver(this);
        comboBox.addObserver(textField);
    }

    /**
     * Getter for the selected node.
     * 
     * @return the selected node
     */
    public Node getSelectedNode() {
        return selectedNode;
    }

    /**
     * Clear the selected station.
     */
    public void clearSelectedStation() {
        selectedNode = null;
    }

    /**
     * Getter for the text field.
     * 
     * @return the text field
     * 
     * @see LocationTextField
     */
    public LocationTextField getTextField() {
        return textField;
    }

    /**
     * Getter for the combobox.
     * 
     * @return the combobox
     * 
     * @see SuggestionComboBox
     */
    public SuggestionComboBox getComboBox() {
        return comboBox;
    }
}
