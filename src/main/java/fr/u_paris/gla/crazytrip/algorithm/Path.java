package fr.u_paris.gla.crazytrip.algorithm;

import java.awt.Color;

import fr.u_paris.gla.crazytrip.dao.LineDAO;
import fr.u_paris.gla.crazytrip.model.Line;
import fr.u_paris.gla.crazytrip.model.Node;
import fr.u_paris.gla.crazytrip.model.key.LineKey;
import fr.u_paris.gla.crazytrip.utils.ColorUtils;

/**
 * This class represents a path between two nodes.
 * 
 * @see Node
 * @see LineKey
 */
public class Path {
    /** The start node */
    private final Node start;
    /** The destination node */
    private final Node end;
    /** The weight of the path */
    private final double weight;
    /** The line of the path. It can be null if the path is from a walk segment */
    private final LineKey lineKey;

    /** True if the path is a walk segment, false otherwise */
    private final boolean isWalk;

    /**
     * Constructor of the class.
     * 
     * If the path is not a walk segment, the lineKey can't be null.
     * 
     * @param start The start node
     * @param end The destination node
     * @param weight The weight of the path
     * @param lineKey The line of the path
     * 
     * @see Node
     * @see LineKey
     */
    public Path(Node start, Node end, double weight, LineKey lineKey) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        
        if (lineKey == null)
            throw new IllegalArgumentException("LineKey can't be null");

        this.lineKey = lineKey;
        this.isWalk = false;
    }

    /**
     * Constructor of the class.
     * 
     * This constructor is used for walk segments.
     * 
     * @param start The start node
     * @param end The destination node
     * @param weight The weight of the path
     * 
     * @see Node
     */
    public Path(Node start, Node end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        this.lineKey = null;
        this.isWalk = true;
    }

    /**
     * Get the color of the path.
     * 
     * If the path is a walk segment, the color is black.
     * 
     * @return The color of the path
     */
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
