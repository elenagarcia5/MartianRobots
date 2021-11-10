package garcia.herrero.MartianRobots.service;

import garcia.herrero.MartianRobots.model.Robot;

public interface Instruction {

	public void apply(Robot robot);
}
