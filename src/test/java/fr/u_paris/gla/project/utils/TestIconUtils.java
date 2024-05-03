package fr.u_paris.gla.project.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.Test;

class TestIconUtils {
    private static String PATH = "src/test/resources/utils/loupe_icon.png";
    
    @Test   
    void testGetIcon() {
        ImageIcon imageIcon = IconUtils.createIcon(PATH, 10, 10);
        assertEquals(10, imageIcon.getIconWidth(),
                "The width of the icon should be 10");
        assertEquals(10, imageIcon.getIconHeight(),
                "The height of the icon should be 10");
    }

    @Test void testGetIconWithNullPath() {
        try {
            IconUtils.createIcon(null, 10, 10);
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException, "The exception should be a NullPointerException");
        }
    }


}