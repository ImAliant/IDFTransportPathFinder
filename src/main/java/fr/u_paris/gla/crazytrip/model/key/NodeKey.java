package fr.u_paris.gla.crazytrip.model.key;

import java.util.Objects;

public class NodeKey {
    private final String nodeName;
    private final LineKey lineKey;
    
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
