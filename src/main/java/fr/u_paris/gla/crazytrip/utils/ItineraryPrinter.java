package fr.u_paris.gla.crazytrip.utils;

import java.util.List;

import fr.u_paris.gla.crazytrip.algorithm.DijkstraPath;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Network;
import fr.u_paris.gla.crazytrip.model.Segment;
import fr.u_paris.gla.crazytrip.model.SegmentWalk;

public class ItineraryPrinter {
    private final List<DijkstraPath> paths;

    public ItineraryPrinter(List<DijkstraPath> paths) {
        this.paths = paths;
    }

    public void print() {
        /* Network network = Network.getInstance(); */

        /* for (Segment segment : segments) {
            if (segment == null) {
                System.out.println("Segment is null");
                continue;
            }

            if (segment instanceof SegmentWalk) {
                String text = String.format("Walk from %s to %s", segment.getStartPoint().getName(), segment.getEndPoint().getName());
                System.out.println(text);
                return;
            }
            else {
                Line line = network.getLineFromSegment(segment);
                String text = String.format("From %s to %s with %s", segment.getStartPoint().getName(), segment.getEndPoint().getName(), line.getName());
                System.out.println(text);
            }
        } */

        paths.forEach(System.out::println);
    }
}
