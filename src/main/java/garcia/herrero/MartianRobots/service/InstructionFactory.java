package garcia.herrero.MartianRobots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstructionFactory {

	@Autowired
	private MoveRightInstruction moveRightInstruction;
	
	@Autowired
	private MoveLeftInstruction moveLeftInstruction;
	
	@Autowired
	private MovedForwardInstruction movedForwardInstruction;
	
	public Instruction getInstructionFromCommand(String command) {
		
		switch (command) {
		case "R":
			return moveRightInstruction;
		case "L":
			return moveLeftInstruction;
		case "F":
			return movedForwardInstruction;
		default:
			return null;
		}
	}
}
