package fr.u_paris.gla.crazytrip.algorithm;

import java.awt.Color;

import fr.u_paris.gla.crazytrip.dao.LineDAO;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Node;
import fr.u_paris.gla.crazytrip.model.key.LineKey;
import fr.u_paris.gla.crazytrip.utils.ColorUtils;

public class Path {
    private final Node start;
    private final Node end;
    private final double weight;
    private final LineKey lineKey;

    private final boolean isWalk;

    public Path(Node start, Node end, double weight, LineKey lineKey) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        
        if (lineKey == null)
            throw new IllegalArgumentException("LineKey can't be null");

        this.lineKey = lineKey;
        this.isWalk = false;
    }

    public Path(Node start, Node end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        this.lineKey = null;
        this.isWalk = true;
    }

    public Color getColorFromPath() {
        if (this.isWalk()) {
            return Color.BLACK;
        }

        LineKey key = this.getLineKey();
        Line line = LineDAO.findLineByKey(key);
        return ColorUtils.decodeColor(line.getColor());
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public double getWeight() {
        return weight;
    }

    public LineKey getLineKey() {
        return lineKey;
    }

    public boolean isWalk() {
        return isWalk;
    }

    @Override
    public String toString() {
        if (isWalk) {
            return String.format("Walk from %s to %s", start.getName(), end.getName());
        }

        return String.format("From %s to %s with %s", start.getName(), end.getName(), lineKey);
    }
}
