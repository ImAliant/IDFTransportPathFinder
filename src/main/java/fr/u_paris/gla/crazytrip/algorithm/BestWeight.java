package fr.u_paris.gla.crazytrip.algorithm;

import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Node;

/**
 * This class represents the best weight of a node.
 */
public class BestWeight {
    /** The node */
    private Node node;
    /** The weight of the node */
    private double weigth;
    /** The line that leads to this node */
    private Line line;

    /**
     * Constructor of the class.
     * 
     * @param node The node
     * @param weigth The weight of the node
     * @param line The line that leads to this node
     * 
     * @see Node
     * @see Line
     */
    public BestWeight(Node node, double weigth, Line line) {
        this.node = node;
        this.weigth = weigth;
        this.line = line;
    }

    public Node getNode() {
        return node;
    }

    public double getWeight() {
        return weigth;
    }

    public Line getLine() {
        return line;
    }
}
