package fr.u_paris.gla.project.idfnetwork;

import java.util.List;

public class Itinerary {
    private List<Stop> stops;

    private List<Line> lines;
    private double totalDistance;
    private double totalDuration;

    public Itinerary(List<Stop> stops,List<Line> lines, double totalDistance, double totalDuration){
        this.stops = stops;
        this.lines = lines;
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;

    }

    public List<Stop> getStops(){
        return stops;
    }

    public List<Line> getLines(){
        return lines;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getTotalDuration() {
        return totalDuration;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Itinerary)) return false;
        Itinerary itinerary = (Itinerary) o;
        return Double.compare(itinerary.getTotalDistance(), getTotalDistance()) == 0
                && Double.compare(itinerary.getTotalDuration(), getTotalDuration()) == 0
                && stops.equals(itinerary.getStops());
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Route{");
        builder.append("Stop=" ).append(stops.get(0).getStopName());
        for ( int i = 0; i < stops.size(); i++ ) {
            builder.append("Stop=" ).append(stops.get(i+1).getStopName());
            builder.append("Path=" ).append(lines.get(i));
        }
        builder.append(", Total Distance=").append(totalDistance).append("km");
        builder.append(", Total Duration=").append(totalDuration).append(" minutes");
        builder.append("}");
        return builder.toString();
    }


}
