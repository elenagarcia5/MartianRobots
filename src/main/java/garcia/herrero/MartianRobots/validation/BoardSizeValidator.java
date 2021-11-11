package garcia.herrero.MartianRobots.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class BoardSizeValidator implements ICommandLineValidator {

	@Override
	public boolean isValid(String line) {
		
		if(StringUtils.isBlank(line)){
			return false;
		}
		String[] elements = line.split(StringUtils.SPACE);
		return elements.length == 2 && validateSize(elements[0]) && validateSize(elements[1]);
	}
}
