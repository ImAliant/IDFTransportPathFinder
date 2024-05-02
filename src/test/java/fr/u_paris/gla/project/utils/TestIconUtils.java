package fr.u_paris.gla.project.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.Test;

public class TestIconUtils {
    
    @Test   
    public void testGetIcon() {
        ImageIcon imageIcon = IconUtils.createIcon("src/test/resources/utils/loupe_icon.png", 10, 10);
        assertTrue(imageIcon.getIconWidth() == 10);
        assertTrue(imageIcon.getIconHeight() == 10);
    }

    @Test void testGetIconWithNullPath() {
        ImageIcon imageIcon = IconUtils.createIcon(null, 10, 10);
        assertTrue(imageIcon == null);
    }


}
