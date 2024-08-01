package fr.u_paris.gla.crazytrip.model;

public abstract sealed class Node permits Station {
    private final String name;
    private final Coordinates coordinates;

    protected Node(final String name, final double latitude, final double longitude) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null");
        this.name = name;
        this.coordinates = new Coordinates(latitude, longitude);
    }

    public double distanceTo(Node node) {
        return coordinates.distanceTo(node.coordinates);
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return name.equals(node.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, coordinates);
    }
}
