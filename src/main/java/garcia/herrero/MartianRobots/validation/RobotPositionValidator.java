package garcia.herrero.MartianRobots.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import garcia.herrero.MartianRobots.model.Orientation;

@Component
public class RobotPositionValidator implements ICommandLineValidator {

	@Override
	public boolean isValid(String line) {
		
		if(StringUtils.isBlank(line)){
			return false;
		}
		String[] elements = line.split(StringUtils.SPACE);
		return elements.length == 3 && validateSize(elements[0]) && validateSize(elements[1]) && Orientation.valueOf(elements[2]) != null;
	}
}
