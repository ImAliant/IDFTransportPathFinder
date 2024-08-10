package fr.u_paris.gla.crazytrip.utils;

import java.util.List;

import fr.u_paris.gla.crazytrip.algorithm.DijkstraPath;

public class ItineraryPrinter {
    private final List<DijkstraPath> paths;

    public ItineraryPrinter(List<DijkstraPath> paths) {
        this.paths = paths;
    }

    public void print() {
        paths.forEach(System.out::println);
    }
}
