package fr.u_paris.gla.crazytrip.model;

import fr.u_paris.gla.crazytrip.model.key.LineKey;

public final class Station extends Node{
    private final LineKey linekey;

    public Station(String name, double latitude, double longitude, LineKey linekey) {
        super(name, latitude, longitude);
        this.linekey = linekey;
    }

    public LineKey getLineKey() {
        return linekey;
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.format("Line: %s %s", linekey.getName(), linekey.getRouteType());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station node = (Station) o;

        return super.equals(o) && linekey.equals(node.linekey);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + linekey.hashCode();
    }
}
