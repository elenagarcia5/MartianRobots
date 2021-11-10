package garcia.herrero.MartianRobots.model;

public class Coordinate {

    private int x;
    private int y;

    public static Coordinate of(int x, int y) {
        return new Coordinate(x, y);
    }

    private Coordinate(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(Coordinate.class) && this.x == ((Coordinate) obj).getX()
                && this.y == ((Coordinate) obj).getY();
    }
}
