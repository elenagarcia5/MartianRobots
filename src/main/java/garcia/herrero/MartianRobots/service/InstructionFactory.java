package garcia.herrero.MartianRobots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import garcia.herrero.MartianRobots.error.FunctionalException;
import garcia.herrero.MartianRobots.model.Instruction;

@Component
public class InstructionFactory {

	@Autowired
	private MoveRightInstruction moveRightInstruction;
	
	@Autowired
	private MoveLeftInstruction moveLeftInstruction;
	
	@Autowired
	private MovedForwardInstruction movedForwardInstruction;
	
	public IInstruction getInstructionFromCommand(String command) throws FunctionalException {

		if (Instruction.R.name().equals(command)){
			return moveRightInstruction;
		}
		if (Instruction.L.name().equals(command)){
			return moveLeftInstruction;
		}
		if (Instruction.F.name().equals(command)){
			return movedForwardInstruction;
		}

		throw new FunctionalException("No instruction service was found for command");
	}
}
