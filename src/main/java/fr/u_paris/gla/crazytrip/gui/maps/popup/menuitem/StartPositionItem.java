package fr.u_paris.gla.crazytrip.gui.maps.popup.menuitem;

import fr.u_paris.gla.crazytrip.gui.maps.popup.SelectPositionPopupMenu;

public class StartPositionItem extends PositionItem {
    private static final String ITEM_NAME = "Set as start position";

    public StartPositionItem(SelectPositionPopupMenu menu) {
        super(menu, ITEM_NAME);
    }
}
