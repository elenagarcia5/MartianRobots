package garcia.herrero.MartianRobots.validation;


import org.assertj.core.api.Assertions;
import org.junit.Test;

import garcia.herrero.MartianRobots.validation.RobotPositionValidator;

public class RobotPositionValidatorTest {
	
	
	RobotPositionValidator robotPositionValidator = new RobotPositionValidator();

	@Test
	public void testRobotPositionValidator() {
		boolean isValid = robotPositionValidator.isValid("1 1 E");
		Assertions.assertThat(isValid).isTrue();
	}

}
