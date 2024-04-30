package fr.u_paris.gla.project.idfnetwork;

import java.util.Collections;
import java.util.List;

import fr.u_paris.gla.project.idfnetwork.stop.Stop;

public class Itinerary {
    private List<Stop> stops;

    private List<Line> lines;
    private double totalDistance;
    private double totalDuration;

    public Itinerary(List<Stop> stops, List<Line> lines, double totalDistance, double totalDuration) {
        this.stops = stops;
        this.lines = lines;
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;

    }

    public List<Stop> getStops(){
        return Collections.unmodifiableList(stops);
    }

    public List<Line> getLines(){
        return Collections.unmodifiableList(lines);
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getTotalDuration() {
        return totalDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Itinerary))
            return false;
        Itinerary itinerary = (Itinerary) o;
        return Double.compare(itinerary.getTotalDistance(), getTotalDistance()) == 0
                && Double.compare(itinerary.getTotalDuration(), getTotalDuration()) == 0
                && stops.equals(itinerary.getStops());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Route:\n");

        builder.append("Prendre ")
            .append(lines.get(0))
            .append(" à l'arrêt ")
            .append(stops.get(0).getStopName())
            .append("\n");
        for (int i = 1; i < stops.size(); i++) {
            if (i < lines.size()) {
                builder.append("Prendre ")
                    .append(lines.get(i))
                    .append(" à l'arrêt ")
                    .append(stops.get(i).getStopName())
                    .append("\n");
            } else {
                builder.append("Arrivé à destination: ")
                    .append(stops.get(i).getStopName())
                    .append("\n");
            }
        }
        builder.append("Distance totale: ").append(totalDistance).append(" km\n");
        builder.append("Durée totale: ").append(totalDuration).append(" minutes\n");

        return builder.toString();
    }
}
