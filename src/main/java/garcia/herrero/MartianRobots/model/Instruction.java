package garcia.herrero.MartianRobots.model;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Instruction {

    R,
    L,
    F;
    
	 public static boolean existValue(String value){
    	 return Arrays.asList(values()).stream().map(element -> element.toString().trim()).collect(Collectors.toList()).contains(value);
    }
}
