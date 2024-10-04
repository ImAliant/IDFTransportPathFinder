package fr.u_paris.gla.crazytrip.model.wrapper;

import java.awt.Color;

public abstract class RouteWrapper<T> {
    private T start;
    private T end;
    private Color color;
    
    protected RouteWrapper(T start, T end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    public T getStart() {
        return start;
    }

    public T getEnd() {
        return end;
    }

    public Color getColor() {
        return color;
    }
}
