package garcia.herrero.MartianRobots.service;

import garcia.herrero.MartianRobots.model.Robot;

public interface IInstruction {

	public void apply(Robot robot);
}
