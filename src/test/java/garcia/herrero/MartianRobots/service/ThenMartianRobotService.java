package garcia.herrero.MartianRobots.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

import garcia.herrero.MartianRobots.dto.MartianRobotInputDto;
import garcia.herrero.MartianRobots.error.FunctionalException;

public class ThenMartianRobotService<SELF extends ThenMartianRobotService<?>> extends Stage<SELF> {

	@ExpectedScenarioState
	MartinRobotsService martinRobotsService;

	@ExpectedScenarioState
	List<String> playMartinRobotOutput;

	@ExpectedScenarioState
	FunctionalException functionalException;
	
	ArgumentCaptor<MartianRobotInputDto> captorMartianRobotInput = ArgumentCaptor.forClass(MartianRobotInputDto.class);

	MartianRobotInputDto martianRobotInput;

	public SELF the_playMartinRobot_method_has_been_called() throws FunctionalException {
		Mockito.verify(martinRobotsService, Mockito.times(1)).playMartinRobot(captorMartianRobotInput.capture());
		return self();
	}

	public SELF martian_robot_input() {
		martianRobotInput = captorMartianRobotInput.getValue();
		return self();
	}
	
	public SELF no_lines() {
		Assertions.assertThat(martianRobotInput.getLines()).isNull();
		return self();
	}
	
	public SELF input_has_$_lines(int size) {
		Assertions.assertThat(martianRobotInput.getLines().size()).isEqualTo(size);
		return self();
	}
	
	public SELF line_$_is_equal_to(int lineNumber, String line) {
		Assertions.assertThat(martianRobotInput.getLines().get(lineNumber)).isEqualTo(line);
		return self();
	}
	
	public SELF output_is_null() {
		Assertions.assertThat(playMartinRobotOutput).isNull();
		return self();
	}

	public SELF output_has_$_results(int size) {
		Assertions.assertThat(playMartinRobotOutput.size()).isEqualTo(size);
		return self();
	}
	
	public SELF output_line_$_is_equal_to(int lineNumber, String line) {
		Assertions.assertThat(playMartinRobotOutput.get(lineNumber)).isEqualTo(line);
		return self();
	}

	public SELF no_exception_has_been_thrown() {
		Assertions.assertThat(functionalException).isNull();
		return self();
	}

	public SELF a_functional_exception_has_been_thrown() {
		Assertions.assertThat(functionalException).isNotNull();
		return self();
	}

	public SELF error_message(String errorMessage) {
		Assertions.assertThat(functionalException.getMessage()).isEqualTo(errorMessage);
		return self();
	}
}
