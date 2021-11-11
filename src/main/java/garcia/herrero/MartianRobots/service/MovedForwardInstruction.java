package garcia.herrero.MartianRobots.service;

import org.springframework.stereotype.Component;

import garcia.herrero.MartianRobots.model.Board;
import garcia.herrero.MartianRobots.model.Coordinate;
import garcia.herrero.MartianRobots.model.Orientation;
import garcia.herrero.MartianRobots.model.Robot;

@Component
public class MovedForwardInstruction implements IInstruction {
	
	@Override
	public void apply(Robot robot) {

		Coordinate currentCoordinate = robot.getCurrentPosition().getCoordinate();
		Coordinate nextCoordinate = null;
		if (Orientation.N.equals(robot.getCurrentPosition().getOrientation())) {
			nextCoordinate = Coordinate.of(currentCoordinate.getX(), currentCoordinate.getY() + 1);
		} else if (Orientation.E.equals(robot.getCurrentPosition().getOrientation())) {
			nextCoordinate = Coordinate.of(currentCoordinate.getX() + 1, currentCoordinate.getY());
		} else if (Orientation.S.equals(robot.getCurrentPosition().getOrientation())) {
			nextCoordinate = Coordinate.of(currentCoordinate.getX(), currentCoordinate.getY() - 1);
		} else if (Orientation.W.equals(robot.getCurrentPosition().getOrientation())) {
			nextCoordinate = Coordinate.of(currentCoordinate.getX() - 1, currentCoordinate.getY());
		}
		
		Board board = Board.getBoard();
		
		boolean isScentCoordinate = board.isScentCoordinate(currentCoordinate);
		boolean isCoordinateWithinBounds = board.isCoordinateWithinBounds(nextCoordinate);
		
		if(isCoordinateWithinBounds){
			robot.getCurrentPosition().setCoordinate(nextCoordinate);
			robot.getHistoricalCoordinates().add(currentCoordinate);		
		}else if(!isScentCoordinate){
			robot.setOnMars(false);
			board.getScentCoordinates().add(currentCoordinate);
		}
	}
}
