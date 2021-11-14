package garcia.herrero.MartianRobots.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import garcia.herrero.MartianRobots.dto.MartianRobotInputDto;
import garcia.herrero.MartianRobots.error.FunctionalException;
import garcia.herrero.MartianRobots.model.Board;
import garcia.herrero.MartianRobots.model.Instruction;
import garcia.herrero.MartianRobots.model.Robot;
import garcia.herrero.MartianRobots.validation.InputValidator;

@Service
public class MartinRobotsService {

	@Autowired
	private InstructionFactory instructionFactory;

	@Autowired
	private InputValidator inputValidator;

	
	public List<String> playMartinRobot(MartianRobotInputDto martianRobotINPUT) throws FunctionalException{

		inputValidator.validate(martianRobotINPUT.getLines());
		
		Board.createBoardFromInput(martianRobotINPUT.getLines().get(0));
		
		List<String> output = new ArrayList<>();
		List<List<String>> eachRobotInformation = Lists.partition( martianRobotINPUT.getLines().subList(1,  martianRobotINPUT.getLines().size()), 2);
		
		for (List<String> robotInfo : eachRobotInformation) {
			
			Robot robot = Robot.createRobotFromInput(Pair.of(robotInfo.get(0), robotInfo.get(1)));
			for (Instruction instruction : robot.getInstructions()) {
				if (!robot.isOnMars()) {
					break;
				}

				instructionFactory.getInstructionService(instruction).apply(robot);
			}
			
			output.add(robot.getPositionAsString());
		}
		
		return output;

	}
}
