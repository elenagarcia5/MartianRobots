package garcia.herrero.MartianRobots.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.tngtech.jgiven.junit.ScenarioTest;

import garcia.herrero.MartianRobots.dto.GivenMartianRobotInput;
import garcia.herrero.MartianRobots.error.FunctionalException;

@RunWith(MockitoJUnitRunner.class)
public class MartianRobotServiceUnitTest
		extends ScenarioTest<GivenMartianRobotInput<?>, WhenMartianRobotService<?>, ThenMartianRobotService<?>> {

	@Test
	public void when_no_input_is_provided_and_error_is_thrown() throws FunctionalException {

		given().an_martian_robot_input()
			.with().no_lines();

		when().the_playMartinRobot_method_is_called();

		then().the_playMartinRobot_method_has_been_called()
			.and().an_functional_exception_has_been_thrown()
			.with().error_message("No input has been provided")
			.and().output_is_null();
	}
}
