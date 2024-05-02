package fr.u_paris.gla.project.view.button.research_panel_button;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.u_paris.gla.project.observer.ArrivalMapButtonObserver;
import fr.u_paris.gla.project.view.panel.Maps;

class ArrivalByMapButtonTest {
    @Test
    void observerTest() {
        ArrivalMapButtonObserver observer = new Maps();
        ArrivalByMapButton arrivalByMapButton = new ArrivalByMapButton();

        arrivalByMapButton.addObserver(observer);

        assertEquals(1, arrivalByMapButton.countObservers(),
                "The number of observers should be 1");

        arrivalByMapButton.removeObserver(observer);

        assertEquals(0, arrivalByMapButton.countObservers(),
                "The number of observers should be 0");
    }
}
