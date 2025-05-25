package fr.u_paris.gla.crazytrip.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.u_paris.gla.crazytrip.gui.maps.popup.menuitem.PositionItem;

/**
 * Listener for a position item.
 * 
 * @see PositionItem
 */
public class PositionItemListener implements ActionListener {
    /** The item. */
    private PositionItem item;

    /**
     * Constructor.
     * 
     * @param item the item
     * 
     * @see PositionItem
     */
    public PositionItemListener(PositionItem item) {
        this.item = item;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        item.setPosition();
    }
}
