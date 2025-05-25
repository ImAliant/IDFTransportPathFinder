package fr.u_paris.gla.crazytrip.model;

/**
 * A node in the graph representing the network of stations.
 * 
 * A node has a name and coordinates.
 * A node can be a station or a personalized node.
 * 
 * @see Station
 * @see PersonalizedNode
 * @see Coordinates
 */
public abstract sealed class Node permits Station, PersonalizedNode {
    /** The name of the node */
    private final String name;
    /** The coordinates of the node */
    private final Coordinates coordinates;

    /**
     * Creates a new node with the given name and coordinates.
     * 
     * @param name the name of the node
     * @param latitude the latitude of the node
     * @param longitude the longitude of the node
     * @throws IllegalArgumentException if the name is null
     */
    protected Node(final String name, final double latitude, final double longitude) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null");
        this.name = name;
        this.coordinates = new Coordinates(latitude, longitude);
    }

    /**
     * Returns the distance between this node and the given node.
     * 
     * @param node the node to compute the distance to
     * @return the distance between this node and the given node
     */
    public double distanceTo(Node node) {
        return coordinates.distanceTo(node.coordinates);
    }

    /**
     * Getter for the name of the node.
     * @return the name of the node
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the coordinates of the node.
     * @return the coordinates of the node
     */
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
