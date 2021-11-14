package garcia.herrero.MartianRobots.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import garcia.herrero.MartianRobots.dto.MartianRobotInputDto;
import garcia.herrero.MartianRobots.error.FunctionalException;
import garcia.herrero.MartianRobots.service.MartinRobotsService;

@RestController
public class MartianRobotsController {

	@Autowired
	private MartinRobotsService martinRobotsService;


	@PostMapping("/play")
	public ResponseEntity<List<String>> play(@Valid MartianRobotInputDto martianRobotINPUT) throws FunctionalException {
		
		List<String> output = martinRobotsService.playMartinRobot(martianRobotINPUT);

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
