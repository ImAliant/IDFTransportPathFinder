package fr.u_paris.gla.crazytrip.utils;

import java.util.List;

import fr.u_paris.gla.crazytrip.algorithm.Path;

public class ItineraryPrinter {
    private final List<Path> paths;

    public ItineraryPrinter(List<Path> paths) {
        this.paths = paths;
    }

    public void print() {
        paths.forEach(System.out::println);
    }
}
