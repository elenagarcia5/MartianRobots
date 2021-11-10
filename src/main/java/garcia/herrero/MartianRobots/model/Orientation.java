package garcia.herrero.MartianRobots.model;

public enum Orientation {

    N("W", "E"),
    S("E", "W"),
    E("N", "S"),
    W("S", "N");
    
    private final String left;
    private final String rigth;
    
    private Orientation(String left, String rigth) {
        this.left = left;
        this.rigth = rigth;
    }

    public Orientation getLeft() {
        return Orientation.valueOf(left);
    }

    public Orientation getRigth() {
        return Orientation.valueOf(rigth);
    }

}
