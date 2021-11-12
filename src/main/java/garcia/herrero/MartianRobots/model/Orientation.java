package garcia.herrero.MartianRobots.model;

import java.util.Arrays;
import java.util.stream.Collectors;

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
    
    public static boolean existValue(String value){
    	 return Arrays.asList(values()).stream().map(element -> element.name()).collect(Collectors.toList()).contains(value);
    }

}
