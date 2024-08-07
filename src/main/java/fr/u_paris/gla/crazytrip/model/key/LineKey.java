package fr.u_paris.gla.crazytrip.model.key;

import java.util.Objects;

import fr.u_paris.gla.crazytrip.model.line.RouteType;

public class LineKey {
    private final String name;
    private final RouteType routetype;
    private final String color;

    public LineKey(String name, RouteType routetype, String color) {
        this.name = name;
        this.routetype = routetype;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public RouteType getRouteType() {
        return routetype;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LineKey lineKey = (LineKey) obj;

        return name.equalsIgnoreCase(lineKey.name)
            && routetype.equals(lineKey.routetype)
            && color.equalsIgnoreCase(lineKey.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, routetype, color);
    }
}
