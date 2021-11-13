package garcia.herrero.MartianRobots.service;

import org.apache.commons.lang3.StringUtils;
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
	public void when_no_input_is_provided_an_error_is_thrown() throws FunctionalException {

		given().a_martian_robot_input()
			.with().no_lines();

		when().the_playMartinRobot_method_is_called();

		then().the_playMartinRobot_method_has_been_called()
			.with().martian_robot_input()
			.with().no_lines()
			.and().a_functional_exception_has_been_thrown()
			.with().error_message("No input has been provided")
			.and().output_is_null();
	}
	
	@Test
	public void when_an_input_with_no_valid_board_size_an_error_is_thrown() throws FunctionalException {

		given().a_martian_robot_input()
			.with().line("51 2");

		when().the_playMartinRobot_method_is_called();

		then().the_playMartinRobot_method_has_been_called()
			.with().martian_robot_input()
			.and().input_has_$_lines(1)
			.and().line_$_is_equal_to(0,"51 2")
			.and().a_functional_exception_has_been_thrown()
			.with().error_message("Provided board size is invalid")
			.and().output_is_null();
	}
	
	@Test
	public void when_an_input_with_no_valid_pair_of_robot_instruction_is_provided_an_error_is_thrown() throws FunctionalException {

		given().a_martian_robot_input()
			.with().line("5 2")
			.and().line("1 1 E");

		when().the_playMartinRobot_method_is_called();

		then().the_playMartinRobot_method_has_been_called()
			.with().martian_robot_input()
			.and().input_has_$_lines(2)
			.and().line_$_is_equal_to(0,"5 2")
			.and().line_$_is_equal_to(1,"1 1 E")
			.and().a_functional_exception_has_been_thrown()
			.with().error_message("Two lines should be provided per robot")
			.and().output_is_null();
	}
	
	@Test
	public void when_an_input_with_no_valid_robot_position_is_provided_an_error_is_thrown() throws FunctionalException {
		
		given().a_martian_robot_input()
		.with().line("5 2")
		.and().line("1 1 T")
		.and().line("LRFRL");
		
		when().the_playMartinRobot_method_is_called();
		
		then().the_playMartinRobot_method_has_been_called()
		.with().martian_robot_input()
		.and().input_has_$_lines(3)
		.and().line_$_is_equal_to(0,"5 2")
		.and().line_$_is_equal_to(1,"1 1 T")
		.and().line_$_is_equal_to(2,"LRFRL")
		.and().a_functional_exception_has_been_thrown()
		.with().error_message("The position provided for the robot is not valid (1 1 T)")
		.and().output_is_null();
	}
	
	@Test
	public void when_an_input_with_no_valid_robot_instruction_is_provided_an_error_is_thrown() throws FunctionalException {
		
		given().a_martian_robot_input()
		.with().line("5 2")
		.and().line("1 1 E")
		.and().line("LRFRLS");
		
		when().the_playMartinRobot_method_is_called();
		
		then().the_playMartinRobot_method_has_been_called()
		.with().martian_robot_input()
		.and().input_has_$_lines(3)
		.and().line_$_is_equal_to(0,"5 2")
		.and().line_$_is_equal_to(1,"1 1 E")
		.and().line_$_is_equal_to(2,"LRFRLS")
		.and().a_functional_exception_has_been_thrown()
		.with().error_message("The instructions provided for the robot are not valid (LRFRLS)")
		.and().output_is_null();
	}
	
	@Test
	public void when_an_input_with_no_valid_robot_instruction_length_is_provided_an_error_is_thrown() throws FunctionalException {
		
		given().a_martian_robot_input()
		.with().line("5 2")
		.and().line("1 1 E")
		.and().line(StringUtils.repeat("L",101));
		
		when().the_playMartinRobot_method_is_called();
		
		then().the_playMartinRobot_method_has_been_called()
		.with().martian_robot_input()
		.and().input_has_$_lines(3)
		.and().line_$_is_equal_to(0,"5 2")
		.and().line_$_is_equal_to(1,"1 1 E")
		.and().line_$_is_equal_to(2,StringUtils.repeat("L",101))
		.and().a_functional_exception_has_been_thrown()
		.with().error_message("The instructions provided for the robot are not valid ("+StringUtils.repeat("L",101)+")")
		.and().output_is_null();
	}
	
	@Test
	public void when_providing_a_correct_input_output_is_as_expected() throws FunctionalException {

		given().a_martian_robot_input()
			.with().line("5 3")
			.and().line("1 1 E")
			.and().line("RFRFRFRF")
			.and().line("3 2 N")
			.and().line("FRRFLLFFRRFLL")
			.and().line("0 3 W")
			.and().line("LLFFFRFLFL");

		when().the_playMartinRobot_method_is_called();

		then().the_playMartinRobot_method_has_been_called()
			.with().martian_robot_input()
			.and().input_has_$_lines(7)
			.and().line_$_is_equal_to(0, "5 3")
			.and().line_$_is_equal_to(1, "1 1 E")
			.and().line_$_is_equal_to(2, "RFRFRFRF")
			.and().line_$_is_equal_to(3, "3 2 N")
			.and().line_$_is_equal_to(4, "FRRFLLFFRRFLL")
			.and().line_$_is_equal_to(5, "0 3 W")
			.and().line_$_is_equal_to(6, "LLFFFRFLFL")
			.and().no_exception_has_been_thrown()
			.and().output_has_$_results(3)
			.and().output_line_$_is_equal_to(0, "1 1 E")
			.and().output_line_$_is_equal_to(1, "3 3 N LOST")
			.and().output_line_$_is_equal_to(2, "4 2 N");
	}
	
}
