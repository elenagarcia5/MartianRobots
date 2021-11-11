package garcia.herrero.MartianRobots;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

import garcia.herrero.MartianRobots.dto.MartianRobotINPUT;
import garcia.herrero.MartianRobots.error.FunctionalException;
import garcia.herrero.MartianRobots.model.Board;
import garcia.herrero.MartianRobots.model.Robot;
import garcia.herrero.MartianRobots.service.Instruction;
import garcia.herrero.MartianRobots.service.InstructionFactory;

@SpringBootApplication
@RestController
public class MartianRobotsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MartianRobotsApplication.class, args);
	}

	@Autowired
	private InstructionFactory instructionFactory;

	@PostMapping("/play")
	public String play(MartianRobotINPUT martianRobotINPUT) throws FunctionalException {

		StringBuilder stringBuilder = new StringBuilder();

		if (martianRobotINPUT.getLines() == null) {
			throw new FunctionalException("No input has been provided");
		}

		String[] columnsAndRows = martianRobotINPUT.getLines().get(0).split(" ");
		Board.createBoard(Integer.valueOf(columnsAndRows[0]), Integer.valueOf(columnsAndRows[1]));

		List<String> lines = martianRobotINPUT.getLines();
		List<List<String>> eachRobotInformation = Lists.partition(lines.subList(1, lines.size()), 2);
		for (List<String> robotInfo : eachRobotInformation) {
			Robot robot = new Robot();
			robot.createPositionFromCommandLine(robotInfo.get(0));
			for (String command : robotInfo.get(1).split("")) {
				if (!robot.isOnMars()) {
					break;
				}
				Instruction instruction = instructionFactory.getInstructionFromCommand(command);
				instruction.apply(robot);
			}
			stringBuilder.append("\n");
			stringBuilder.append(robot.getPositionAsString());
		}

		return stringBuilder.toString();
	}

	@ExceptionHandler(FunctionalException.class)
	public String handleError(HttpServletRequest req, Exception ex) {
		return ex.getMessage();
	}
}
