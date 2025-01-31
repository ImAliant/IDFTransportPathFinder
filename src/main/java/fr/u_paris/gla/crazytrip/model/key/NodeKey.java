package fr.u_paris.gla.crazytrip.model.key;

import java.util.Objects;

/** Key class to identify a node */
public class NodeKey {
    /** The name of the node */
    private final String nodeName;
    /** The key of the line */
    private final LineKey lineKey;
    
    /**
     * Create a node key
     * 
     * @param nodeName the name of the node
     * @param lineKey the key of the line
     */
    public NodeKey(final String nodeName, final LineKey lineKey) {
        this.nodeName = nodeName;
        this.lineKey = lineKey;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NodeKey nodeKey = (NodeKey) obj;

        return nodeName.equalsIgnoreCase(nodeKey.nodeName)
            && lineKey.equals(nodeKey.lineKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeName, lineKey);
    }
}
