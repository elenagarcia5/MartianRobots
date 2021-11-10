package garcia.herrero.MartianRobots.service;

import garcia.herrero.MartianRobots.model.Orientation;
import garcia.herrero.MartianRobots.model.Robot;

public abstract class MoveInDirectionInstruction implements Instruction {

	@Override
	public void apply(Robot robot) {
		Orientation currentOrientation = robot.getCurrentPosition().getOrientation();
		robot.getCurrentPosition().setOrientation(calculateNextOrientation(currentOrientation));
		
	}
	
	protected abstract Orientation calculateNextOrientation(Orientation currentOrientation);
}
