package fr.u_paris.gla.crazytrip.algorithm;

import java.util.HashMap;
import java.util.Map;

import fr.u_paris.gla.crazytrip.model.Node;

/**
 * This class represents an itinerary.
 * We use this class to store the best weights of the nodes.
 */
public class Itinerary {
    /** The best weights of the nodes */
    private Map<Node, BestWeight> bestWeights;

    /**
     * Constructor of the class.
     */
    public Itinerary() {
        bestWeights = new HashMap<>();
    }

    /**
     * Add a node and its best weight to the itinerary.
     * 
     * @param node The node
     * @param bestWeight The best weight of the node
     * 
     * @see Node
     * @see BestWeight
     */
    public void add(Node node, BestWeight bestWeight) {
        bestWeights.put(node, bestWeight);
    }

    /**
     * Get the best weight of a node.
     * 
     * @param node The node
     * 
     * @return The best weight of the node
     * 
     * @see Node
     * @see BestWeight
     */
    public BestWeight get(Node node) {
        return bestWeights.get(node);
    }

    /**
     * Check if the itinerary contains a node.
     * 
     * @param node The node
     * 
     * @return True if the itinerary contains the node, false otherwise
     * 
     * @see Node
     */
    public boolean contains(Node node) {
        return bestWeights.containsKey(node);
    }

    /**
     * Remove a node from the itinerary.
     * 
     * @param node The node
     * 
     * @see Node
     */
    public void remove(Node node) {
        bestWeights.remove(node);
    }

    /**
     * Clear the itinerary.
     */
    public void clear() {
        bestWeights.clear();
    }

    /**
     * Check if the itinerary is empty.
     * 
     * @return True if the itinerary is empty, false otherwise
     */
    public boolean isEmpty() {
        return bestWeights.isEmpty();
    }
}
