package garcia.herrero.MartianRobots.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

import garcia.herrero.MartianRobots.dto.MartianRobotInput;
import garcia.herrero.MartianRobots.error.FunctionalException;

public class ThenMartianRobotService<SELF extends ThenMartianRobotService<?>> extends Stage<SELF> {

	@ExpectedScenarioState
	MartinRobotsService martinRobotsService;

	@ExpectedScenarioState
	List<String> playMartinRobotOutput;

	@ExpectedScenarioState
	FunctionalException functionalException;

	ArgumentCaptor<MartianRobotInput> captorMartianRobotInput = ArgumentCaptor.forClass(MartianRobotInput.class);;

	public SELF the_playMartinRobot_method_has_been_called() throws FunctionalException {
		Mockito.verify(martinRobotsService, Mockito.times(1)).playMartinRobot(captorMartianRobotInput.capture());
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

	public SELF no_exception_has_been_thrown() {
		Assertions.assertThat(functionalException).isNull();
		return self();
	}

	public SELF an_functional_exception_has_been_thrown() {
		Assertions.assertThat(functionalException).isNotNull();
		return self();
	}

	public SELF error_message(String errorMessage) {
		Assertions.assertThat(functionalException.getMessage()).isEqualTo(errorMessage);
		return self();
	}
}
