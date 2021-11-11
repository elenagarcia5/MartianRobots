package garcia.herrero.MartianRobots.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import garcia.herrero.MartianRobots.error.FunctionalException;

@Component
public class InputValidator {

	@Autowired
	private BoardSizeValidator boardSizeValidator;

	@Autowired
	private RobotPositionValidator robotPositionValidator;

	@Autowired
	private RobotInstructionsValidator robotInstructionsValidator;

	public void validate(List<String> lines) throws FunctionalException {

		if (CollectionUtils.isEmpty(lines)) {
			throw new FunctionalException("No input has been provided");
		}

		boolean isValid = boardSizeValidator.isValid(lines.get(0));
		if (!isValid) {
			throw new FunctionalException("Provided board size is invalid");
		}
		
		List<List<String>> eachRobotInformation = Lists.partition(lines.subList(1, lines.size()), 2);
		for (List<String> robotInformation : eachRobotInformation) {
			if (robotInformation.size() != 2) {
				throw new FunctionalException("Two lines should be provided per robot");
			}

			isValid = robotPositionValidator.isValid(robotInformation.get(0));
			if (!isValid) {
				throw new FunctionalException("The position provided for the robot is not valid ("+robotInformation.get(0)+")");
			}
			
			isValid = robotInstructionsValidator.isValid(robotInformation.get(1));
			if (!isValid) {
				throw new FunctionalException("The instructions provided for the robot are not valid ("+robotInformation.get(1)+")");
			}
		}
	}
}
