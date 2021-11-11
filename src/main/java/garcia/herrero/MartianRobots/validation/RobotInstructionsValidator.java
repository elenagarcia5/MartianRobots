package garcia.herrero.MartianRobots.validation;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Component;

import garcia.herrero.MartianRobots.model.Instruction;

@Component
public class RobotInstructionsValidator implements ICommandLineValidator {

	private static final int MAX_SIZE = 100;

	@Override
	public boolean isValid(String line) {
		
		if(StringUtils.isBlank(line) || line.length() > MAX_SIZE){
			return false;
		}
		
		return Arrays.asList(line.split(StringUtils.EMPTY)).stream()
				.allMatch(letter -> Arrays.asList(Instruction.values()).contains(letter));
	}
}
