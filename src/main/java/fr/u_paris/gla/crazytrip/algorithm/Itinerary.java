package fr.u_paris.gla.crazytrip.algorithm;

import java.util.HashMap;
import java.util.Map;

import fr.u_paris.gla.crazytrip.model.Node;

public class Itinerary {
    private Map<Node, BestWeight> bestWeights;

    public Itinerary() {
        bestWeights = new HashMap<>();
    }

    public void add(Node node, BestWeight bestWeight) {
        bestWeights.put(node, bestWeight);
    }

    public BestWeight get(Node node) {
        return bestWeights.get(node);
    }

    public boolean contains(Node node) {
        return bestWeights.containsKey(node);
    }

    public void remove(Node node) {
        bestWeights.remove(node);
    }

    public void clear() {
        bestWeights.clear();
    }

    public boolean isEmpty() {
        return bestWeights.isEmpty();
    }
}
