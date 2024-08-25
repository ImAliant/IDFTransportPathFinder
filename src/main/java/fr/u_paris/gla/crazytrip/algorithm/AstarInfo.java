package fr.u_paris.gla.crazytrip.algorithm;

import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Node;

public class AstarInfo implements Comparable<AstarInfo> {
    private final Node node;
    private double weight;
    private int lineChanges;
    private Line line;

    public AstarInfo(Node node, double weight, int lineChanges, Line line) {
        this.node = node;
        this.weight = weight;
        this.lineChanges = lineChanges;
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

    public int getLineChanges() {
        return lineChanges;
    }

    public void setLineChanges(int lineChanges) {
        this.lineChanges = lineChanges;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

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
