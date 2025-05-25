package fr.u_paris.gla.crazytrip.algorithm;

import fr.u_paris.gla.crazytrip.model.Network;
import fr.u_paris.gla.crazytrip.model.Node;

/**
 * This class represents a path finder.
 * It is an abstract class that who can be extended by different path finding algorithms.
 * 
 * @see Node
 * @see Network
 */
public abstract class PathFinder {
    /** The instance of the network */
    protected final Network network = Network.getInstance();

    /** The start node */
    protected Node start;
    /** The destination node */
    protected Node end;

    /**
     * Constructor of the class.
     * 
     * @param start The start node
     * @param end The destination node
     * 
     * @see Node
     */
    protected PathFinder(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Find the best path between the start and the destination nodes.
     * 
     * @return The best path between the start and the destination nodes
     * 
     * @see ItineraryResult
     */
    public abstract ItineraryResult findPath();

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }
}
