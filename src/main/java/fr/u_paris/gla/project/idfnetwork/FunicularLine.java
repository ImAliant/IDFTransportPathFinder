package fr.u_paris.gla.project.idfnetwork;

public class FunicularLine extends Line {
    private static final double AVERAGE_SPEED = 10.0;

    public FunicularLine(String name, String color) {
        super(name, color);
        this.speed = AVERAGE_SPEED;
    }

    @Override
    public LineType getType() {
        return LineType.FUNICULAIRE;
    }
}
