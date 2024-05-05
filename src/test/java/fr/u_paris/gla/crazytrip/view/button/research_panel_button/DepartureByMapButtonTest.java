package fr.u_paris.gla.crazytrip.view.button.research_panel_button;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.u_paris.gla.crazytrip.observer.DepartureMapButtonObserver;
import fr.u_paris.gla.crazytrip.view.panel.Maps;

class DepartureByMapButtonTest {
    @Test
    void observerTest() {
        DepartureMapButtonObserver observer = new Maps();
        DepartureByMapButton departureByMapButton = new DepartureByMapButton();

        departureByMapButton.addObserver(observer);

        assertEquals(1, departureByMapButton.countObservers(),
                "The number of observers should be 1");

        departureByMapButton.removeObserver(observer);

        assertEquals(0, departureByMapButton.countObservers(),
                "The number of observers should be 0");
    }
}
