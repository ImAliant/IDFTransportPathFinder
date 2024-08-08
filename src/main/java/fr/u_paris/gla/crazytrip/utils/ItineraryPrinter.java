package fr.u_paris.gla.crazytrip.utils;

import java.util.List;

import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Network;
import fr.u_paris.gla.crazytrip.model.Segment;

public class ItineraryPrinter {
    private final List<Segment> segments;

    public ItineraryPrinter(List<Segment> segments) {
        this.segments = segments;
    }

    public void print() {
        Network network = Network.getInstance();

        segments.forEach(segment -> {
            Line line = network.getLineFromSegment(segment);
            String text = String.format("From %s to %s with %s", segment.getStartPoint().getName(), segment.getEndPoint().getName(), line.getName());
            System.out.println(text);
        });
    }
}
