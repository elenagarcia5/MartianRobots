package garcia.herrero.MartianRobots.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

public class Robot {

	private Position currentPosition;
	private String instructions;
	private boolean isOnMars = true;

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	public boolean isOnMars() {
		return isOnMars;
	}

	public void setOnMars(boolean isOnMars) {
		this.isOnMars = isOnMars;
	}

	public List<Instruction> getInstructions() {
		return instructions == null ? new ArrayList<>()
				: Arrays.asList(instructions.split(StringUtils.EMPTY)).stream().map(ins -> Instruction.valueOf(ins))
						.collect(Collectors.toList());
	}

	public static Robot createRobotFromInput(Pair<String, String> commandLineInfo) {
		Robot robot = new Robot();
		robot.setCurrentPosition(Position.createPositionFromInput(commandLineInfo.getLeft()));
		robot.setInstructions(commandLineInfo.getRight());
		return robot;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getPositionAsString() {
		List<String> toPrint = Arrays
				.asList(currentPosition.getCoordinate().getX(), currentPosition.getCoordinate().getY(),
						currentPosition.getOrientation().name())
				.stream().map(pos -> String.valueOf(pos)).collect(Collectors.toList());

		if (!isOnMars) {
			toPrint.add("LOST");
		}
		return StringUtils.join(toPrint, StringUtils.SPACE);
	}

}
