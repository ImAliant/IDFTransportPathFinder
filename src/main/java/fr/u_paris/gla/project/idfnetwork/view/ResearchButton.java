package fr.u_paris.gla.project.idfnetwork.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import fr.u_paris.gla.project.idfnetwork.Itinerary;
import fr.u_paris.gla.project.observer.ItineraryObserver;

public class ResearchButton extends JButton {
    private static final Color BUTTON_FOREGROUND = Color.WHITE;
    private static final Color BUTTON_BACKGROUND = new Color(1, 121, 111);

    private List<ItineraryObserver> observers = new ArrayList<>();

    public ResearchButton(String text, CustomTextField departureTF, CustomTextField arrivalTF) {
        super(text);
        setForeground(BUTTON_FOREGROUND);
        setBackground(BUTTON_BACKGROUND);

        ResearchButtonListener listener = new ResearchButtonListener(departureTF, arrivalTF);
        addListener(listener);

    }

    public void addObserver(ItineraryObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Itinerary itinerary) {
        observers.forEach(observer -> observer.showItinerary(itinerary));
    }

    private void addListener(ResearchButtonListener listener) {
        this.addActionListener(listener);
    }
}
