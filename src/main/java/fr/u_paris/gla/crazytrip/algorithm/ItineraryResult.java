package fr.u_paris.gla.crazytrip.algorithm;

import java.util.List;

public class ItineraryResult {
    private List<Path> paths;
    private double duration;

    public ItineraryResult(List<Path> paths, double duration) {
        this.paths = paths;
        this.duration = duration;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Duration: ").append(duration).append(" min\n");
        sb.append("Path:\n");
        for (Path path : paths) {
            sb.append(path).append("\n");
        }
        return sb.toString();
    }
}
