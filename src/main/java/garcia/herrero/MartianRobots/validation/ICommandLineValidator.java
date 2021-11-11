package garcia.herrero.MartianRobots.validation;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public interface ICommandLineValidator {

	public static final long MAX_SIZE = 50L;
	
	boolean isValid(String input);
	
	public default boolean validateSize(String element) {
		return isNumeric(element) && Long.valueOf(element) < MAX_SIZE;
	}
}
