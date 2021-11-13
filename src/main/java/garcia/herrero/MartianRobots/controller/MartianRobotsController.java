package garcia.herrero.MartianRobots.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import garcia.herrero.MartianRobots.dto.MartianRobotInput;
import garcia.herrero.MartianRobots.error.FunctionalException;
import garcia.herrero.MartianRobots.model.Board;
import garcia.herrero.MartianRobots.model.Instruction;
import garcia.herrero.MartianRobots.model.Robot;
import garcia.herrero.MartianRobots.service.InstructionFactory;
import garcia.herrero.MartianRobots.validation.InputValidator;

@RestController
public class MartianRobotsController {

	@Autowired
	private InstructionFactory instructionFactory;

	@Autowired
	private InputValidator inputValidator;

	@PostMapping("/play")
	public ResponseEntity<List<String>> play(@Valid MartianRobotInput martianRobotINPUT) throws FunctionalException {

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

		return ResponseEntity.status(OK).body(output);
	}

	@ExceptionHandler(FunctionalException.class)
	public ResponseEntity<String> handleError(HttpServletRequest req, Exception ex) {
		return ResponseEntity.status(BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericError(HttpServletRequest req, Exception ex) {
		return ResponseEntity.status(BAD_REQUEST).body("Unexpected error ");
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public Map<String, String> handleValidationExceptions(BindException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
