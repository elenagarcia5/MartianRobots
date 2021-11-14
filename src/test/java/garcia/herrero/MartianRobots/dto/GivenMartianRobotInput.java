package garcia.herrero.MartianRobots.dto;

import java.util.ArrayList;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

public class GivenMartianRobotInput<SELF extends GivenMartianRobotInput<?>> extends Stage<SELF> {

	@ProvidedScenarioState
	MartianRobotInputDto martianRobotInput;

	public SELF a_martian_robot_input() {
		this.martianRobotInput = new MartianRobotInputDto();
		return self();
	}
	
	public SELF no_lines() {
		this.martianRobotInput.setLines(null);
		return self();
	}

	public SELF line(String line) {
		if (this.martianRobotInput.getLines() == null) {
			this.martianRobotInput.setLines(new ArrayList<String>());
		}
		this.martianRobotInput.getLines().add(line);
		return self();
	}
}
