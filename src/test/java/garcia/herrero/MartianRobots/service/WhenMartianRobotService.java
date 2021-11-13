package garcia.herrero.MartianRobots.service;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeScenario;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import garcia.herrero.MartianRobots.dto.MartianRobotInput;
import garcia.herrero.MartianRobots.error.FunctionalException;
import garcia.herrero.MartianRobots.validation.BoardSizeValidator;
import garcia.herrero.MartianRobots.validation.InputValidator;
import garcia.herrero.MartianRobots.validation.RobotInstructionsValidator;
import garcia.herrero.MartianRobots.validation.RobotPositionValidator;

public class WhenMartianRobotService<SELF extends WhenMartianRobotService<?>> extends Stage<SELF> {
	
	@ExpectedScenarioState
	MartianRobotInput martianRobotInput;
	
	@Spy
	private MoveRightInstruction moveRightInstruction;
	
	@Spy
	private MoveLeftInstruction moveLeftInstruction;
	
	@Spy
	private MovedForwardInstruction movedForwardInstruction;
	
	@Spy
	private BoardSizeValidator boardSizeValidator;

	@Spy
	private RobotPositionValidator robotPositionValidator;

	@Spy
	private RobotInstructionsValidator robotInstructionsValidator;
	
	@Spy
	@InjectMocks
	private InstructionFactory instructionFactory;

	@Spy
	@InjectMocks
	private InputValidator inputValidator;

	@ProvidedScenarioState
    @Spy
    @InjectMocks
    MartinRobotsService martinRobotsService;

    @ProvidedScenarioState
    FunctionalException functionalException;

    @ProvidedScenarioState
	List<String> playMartinRobotOutput;
    
    @BeforeScenario
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

    
	 public SELF an_martian_robot_service() {
		 return self();
	 }
	 
	 public SELF the_playMartinRobot_method_is_called() {
		 try{
			 playMartinRobotOutput = martinRobotsService.playMartinRobot(martianRobotInput);
		 }catch(FunctionalException functionalException){
			 this.functionalException = functionalException;
		 }
		 return self();
	 }
}
