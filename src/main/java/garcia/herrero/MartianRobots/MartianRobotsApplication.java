package garcia.herrero.MartianRobots;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import garcia.herrero.MartianRobots.dto.MartianRobotInput;
import garcia.herrero.MartianRobots.error.FunctionalException;
import garcia.herrero.MartianRobots.model.Board;
import garcia.herrero.MartianRobots.model.Position;
import garcia.herrero.MartianRobots.model.Robot;
import garcia.herrero.MartianRobots.service.IInstruction;
import garcia.herrero.MartianRobots.service.InstructionFactory;
import garcia.herrero.MartianRobots.validation.InputValidator;

@SpringBootApplication
@RestController
public class MartianRobotsApplication {

	@Autowired
	private InstructionFactory instructionFactory;

	@Autowired
	private InputValidator inputValidator;

	@PostMapping("/play")
	public ResponseEntity<String> play(MartianRobotInput martianRobotINPUT) throws FunctionalException {

		StringBuilder stringBuilder = new StringBuilder();

		inputValidator.validate(martianRobotINPUT.getLines());
		
		Board.createBoardFromInput(martianRobotINPUT.getLines().get(0));
		
		List<String> lines = martianRobotINPUT.getLines();
		List<List<String>> eachRobotInformation = Lists.partition(lines.subList(1, lines.size()), 2);
		for (List<String> robotInfo : eachRobotInformation) {
			Robot robot = new Robot();
			robot.setCurrentPosition(Position.createPositionFromInput(robotInfo.get(0)));
			for (String command : robotInfo.get(1).split(StringUtils.EMPTY)) {
				if (!robot.isOnMars()) {
					break;
				}
				IInstruction instruction = instructionFactory.getInstructionFromCommand(command);
				instruction.apply(robot);
			}
			stringBuilder.append(System.lineSeparator());
			stringBuilder.append(robot.getPositionAsString());
		}

		return ResponseEntity.status(OK).body(stringBuilder.toString());
	}

	@ExceptionHandler(FunctionalException.class)
	public ResponseEntity<String> handleError(HttpServletRequest req, Exception ex) {
		return ResponseEntity.status(BAD_REQUEST).body(ex.getMessage());
	}

	public static void main(String[] args) {
		SpringApplication.run(MartianRobotsApplication.class, args);
	}
}
