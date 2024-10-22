package fr.u_paris.gla.crazytrip.algo;

import java.util.Collections;
import java.util.List;

import fr.u_paris.gla.crazytrip.idfnetwork.Stop;
import fr.u_paris.gla.crazytrip.idfnetwork.line.Line;
/**
 * Represents an itinerary consisting of multiple stops and transit lines, along with the total distance and duration.
 * This class encapsulates all relevant details necessary to describe a complete route.
 * @author Diamant Alexandre
 * @author Frarma Yanis
 */
public class Itinerary {
    private List<Stop> stops;

    private List<Line> lines;
    private double totalDistance;
    private double totalDuration;

    /**
     * Constructs an itinerary with specified stops, lines, total distance, and total duration.
     * @param stops the list of stops along the itinerary
     * @param lines the list of transit lines used in the itinerary
     * @param totalDistance the total distance covered by the itinerary in kilometers
     * @param totalDuration the total duration of the itinerary in minutes
     */
    public Itinerary(List<Stop> stops, List<Line> lines, double totalDistance, double totalDuration) {
        this.stops = stops;
        this.lines = lines;
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;

    }

     /**
     * Returns an unmodifiable list of stops in the itinerary.
     * @return a list of stops
     */
    public List<Stop> getStops(){
        return Collections.unmodifiableList(stops);
    }

    /**
     * Returns an unmodifiable list of lines used in the itinerary.
     * @return a list of lines
     */
    public List<Line> getLines(){
        return Collections.unmodifiableList(lines);
    }

    /**
     * Returns the total distance of the itinerary.
     * @return total distance in kilometers
     */
    public double getTotalDistance() {
        return totalDistance;
    }

    /**
     * Returns the total duration of the itinerary.
     * @return total duration in minutes
     */
    public double getTotalDuration() {
        return totalDuration;
    }

    @Override
    public int hashCode() {
        return stops.hashCode() + lines.hashCode() + Double.hashCode(totalDistance) + Double.hashCode(totalDuration);
    }

    /**
     * Compares this itinerary with another object for equality, based on distance, duration, and stops.
     * @param o the object to compare with
     * @return true if the given object represents an itinerary equivalent to this itinerary, false otherwise
     */
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

    /**
     * Provides a string representation of the itinerary, detailing each step of the route including stops, lines, distance and duration.
     * @return a string representation of the itinerary
     */
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
            if (i < lines.size()-1) {
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
