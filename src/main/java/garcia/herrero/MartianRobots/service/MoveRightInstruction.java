package garcia.herrero.MartianRobots.service;

import org.springframework.stereotype.Component;

import garcia.herrero.MartianRobots.model.Orientation;

@Component
public class MoveRightInstruction extends MoveInDirectionInstruction {

	@Override
	protected Orientation calculateNextOrientation(Orientation currentOrientation) {
		return currentOrientation.getRigth();
	}
}
