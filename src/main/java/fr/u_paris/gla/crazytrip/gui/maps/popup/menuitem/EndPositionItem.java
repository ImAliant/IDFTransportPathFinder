package fr.u_paris.gla.crazytrip.gui.maps.popup.menuitem;

import fr.u_paris.gla.crazytrip.gui.maps.popup.SelectPositionPopupMenu;

/**
 * Class representing a menu item to set the end position of a path.
 * 
 * @see PositionItem
 */
public class EndPositionItem extends PositionItem {
    /** Text displayed by the item */
    private static final String ITEM_NAME = "Set as end position";

    /**
     * Constructor.
     * 
     * @param menu the menu to which the item belongs
     * 
     * @see SelectPositionPopupMenu
     */
    public EndPositionItem(SelectPositionPopupMenu menu) {
        super(menu, ITEM_NAME);
    }
}
