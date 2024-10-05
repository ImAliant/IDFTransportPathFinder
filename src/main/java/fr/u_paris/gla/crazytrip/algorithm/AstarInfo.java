package fr.u_paris.gla.crazytrip.algorithm;

import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Node;

/**
 * Class used to store information about a node in the A* algorithm.
 */
public class AstarInfo implements Comparable<AstarInfo> {
    /** The node stored in this object. */
    private final Node node;
    /** The weight of the path to this node. */
    private double weight;
    /** The number of transfers to reach this node. */
    private int transfers;
    /** The line where the node is located. */
    private Line line;

    /**
     * Constructor.
     * @param node The node to store.
     * @param weight The weight of the path to this node.
     * @param transfers The number of transfers to reach this node.
     * @param line The line where the node is located.
     */
    public AstarInfo(Node node, double weight, int transfers, Line line) {
        this.node = node;
        this.weight = weight;
        this.transfers = transfers;
        this.line = line;
    }

    public Node getNode() {
        return node;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getTransfers() {
        return transfers;
    }

    public void setTransfers(int transfers) {
        this.transfers = transfers;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    /** Used to compare two AstarInfo objects. */ 
    @Override
    public int compareTo(AstarInfo dijkstraInfo) {
        return Double.compare(weight, dijkstraInfo.weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AstarInfo dijkstraInfo = (AstarInfo) obj;

        return node.equals(dijkstraInfo.node);
    }

    @Override
    public int hashCode() {
        return node.hashCode();
    }
}
