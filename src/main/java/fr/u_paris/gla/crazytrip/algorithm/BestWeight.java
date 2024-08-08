package fr.u_paris.gla.crazytrip.algorithm;

import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Node;

public class BestWeight {
    private Node node;
    private double weigth;
    private Line line;

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
