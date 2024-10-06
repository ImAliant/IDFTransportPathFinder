package fr.u_paris.gla.crazytrip.gui.maps.popup.menuitem;

import fr.u_paris.gla.crazytrip.gui.maps.popup.SelectPositionPopupMenu;

/**
 * Class representing a menu item to set the start position of a path.
 * 
 * @see PositionItem
 */
public class StartPositionItem extends PositionItem {
    /** Text displayed by the item */
    private static final String ITEM_NAME = "Set as start position";

    /**
     * Constructor.
     * 
     * @param menu the menu to which the item belongs
     * 
     * @see SelectPositionPopupMenu
     */
    public StartPositionItem(SelectPositionPopupMenu menu) {
        super(menu, ITEM_NAME);
    }
}
