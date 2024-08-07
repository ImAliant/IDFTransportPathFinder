package fr.u_paris.gla.crazytrip.model;

public final class Station extends Node{
    private String routetype;

    public Station(String name, double latitude, double longitude, String routetype) {
        super(name, latitude, longitude);
        this.routetype = routetype;
    }

    public String getRouteType() {
        return routetype;
    }

    @Override
    public String toString() {
        return super.toString() + " " + routetype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station node = (Station) o;
        return super.equals(o) && routetype.equals(node.routetype);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + routetype.hashCode();
    }
}
