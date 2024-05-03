package fr.u_paris.gla.crazytrip.view.button.research_panel_button;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.u_paris.gla.crazytrip.idfnetwork.Stop;
import fr.u_paris.gla.crazytrip.idfnetwork.network.Network;
import fr.u_paris.gla.crazytrip.observer.ItineraryObserver;
import fr.u_paris.gla.crazytrip.view.combobox.SuggestionStationsComboBox;
import fr.u_paris.gla.crazytrip.view.panel.ShowResultPanel;
import fr.u_paris.gla.crazytrip.view.textfield.CustomTextField;

import java.awt.Color;

class ResearchButtonTest {
    private static final Color BUTTON_FOREGROUND = Color.WHITE;
    private static final Color BUTTON_BACKGROUND = new Color(1, 121, 111);

    private static ResearchButton researchButton;
    private static CustomTextField ctx1;
    private static CustomTextField ctx2;
    private static SuggestionStationsComboBox ssc1;
    private static SuggestionStationsComboBox ssc2;

    private static ItineraryObserver observer;

    private Network network = Network.getInstance();

    @BeforeAll
    static void setUp() {
        ssc1 = new SuggestionStationsComboBox();
        ssc2 = new SuggestionStationsComboBox();
        ctx1 = new CustomTextField(ssc1);
        ctx2 = new CustomTextField(ssc2);
        researchButton = new ResearchButton("text", ctx1, ctx2);

        observer = new ShowResultPanel();
    }

    @BeforeEach
    void clear() {
        network.clear();
    }

    @Test
    void constructorTest() {
        assertEquals("text", researchButton.getText(),
                "The text of the button should be the same as the one given in the constructor");

        assertEquals(BUTTON_FOREGROUND, researchButton.getForeground(),
                "The foreground color of the button should be white");
            
        assertEquals(BUTTON_BACKGROUND, researchButton.getBackground(),
                "The background color of the button should be the same as the one given in the constructor");

        // Test the action listener
        researchButton.doClick();
    }

    @Test
    void addObserverTest() {
        researchButton.addObserver(observer);

        assertEquals(1, researchButton.countObservers(),
                "The number of observers should be 1 after adding one observer");
    }

    @Test
    void findStopByNameOrGeoPositionTest() {
        Stop stop = new Stop("stop1", 48.8588443, 2.2943506);
        network.addStop(stop);

        assertEquals(stop, researchButton.findStopByNameOrGeoPosition("stop1"),
                "The stop should be found by its name");

        assertEquals(stop, researchButton.findStopByNameOrGeoPosition("48.8588443, 2.2943506"),
                "The stop should be found by its geo position");
    }

    @Test
    void parseGeoPositionTest() {
        String position = "48.8588443, 2.2943506";

        double[] geoPosition = researchButton.parseGeoPosition(position);
        
        assertEquals(2, geoPosition.length, "The length of the array should be 2");

        assertEquals(48.8588443, geoPosition[0], "The latitude should be 48.8588443");
        assertEquals(2.2943506, geoPosition[1], "The longitude should be 2.2943506");
    }

    @Test
    void isGeoPositionTest() {
        String position = "48.8588443, 2.2943506";

        assertEquals(true, researchButton.isGeoPosition(position),
                "The string should be a geo position");

        position = "48,8588443, 2,2943506";

        assertEquals(false, researchButton.isGeoPosition(position),
                "The string should not be a geo position");
    }
}
