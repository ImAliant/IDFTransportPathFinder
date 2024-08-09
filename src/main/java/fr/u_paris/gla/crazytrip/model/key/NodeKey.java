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

    /* private final String name;
    private final double latitude;
    private final double longitude;
    private final String routetype;

    public NodeKey(final String name, final double latitude, final double longitude, final String routetype) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.routetype = routetype;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NodeKey nodeKey = (NodeKey) obj;

        return name.equalsIgnoreCase(nodeKey.name)
            && routetype.equalsIgnoreCase(nodeKey.routetype)
            && Double.compare(nodeKey.latitude, latitude) == 0
            && Double.compare(nodeKey.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude, routetype);
    } */
}
