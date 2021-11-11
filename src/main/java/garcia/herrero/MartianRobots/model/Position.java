package garcia.herrero.MartianRobots.model;

import org.apache.commons.lang3.StringUtils;

public class Position {

    private Coordinate coordinate;
    private Orientation orientation;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
    
    public static Position createPositionFromInput(String commandLineInfo) {
        String[] positionInfo = commandLineInfo.split(StringUtils.SPACE);
        Position currentPosition = new Position();
        currentPosition
                .setCoordinate(Coordinate.of(Integer.valueOf(positionInfo[0]), Integer.valueOf(positionInfo[1])));
        currentPosition.setOrientation(Orientation.valueOf(positionInfo[2]));
        return currentPosition;
    }

}
