package fr.u_paris.gla.crazytrip.algorithm;

import fr.u_paris.gla.crazytrip.model.Network;
import fr.u_paris.gla.crazytrip.model.Node;

import java.util.List;

public abstract class PathFinder {
    protected final Network network = Network.getInstance();

    protected Node start;
    protected Node end;

    protected PathFinder(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    public abstract List<Path> findPath();

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }
}
