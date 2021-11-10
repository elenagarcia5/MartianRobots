package garcia.herrero.MartianRobots.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Robot {

    private Position currentPosition;
    private List<Coordinate> historicalCoordinates = new ArrayList<>();
    private boolean isOnMars = true;

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public List<Coordinate> getHistoricalCoordinates() {
        return historicalCoordinates;
    }

    public void setHistoricalCoordinates(List<Coordinate> historicalCoordinates) {
        this.historicalCoordinates = historicalCoordinates;
    }

    public boolean isOnMars() {
        return isOnMars;
    }

    public void setOnMars(boolean isOnMars) {
        this.isOnMars = isOnMars;
    }

    public void createPositionFromCommandLine(String commandLineInfo) {
        String[] positionInfo = commandLineInfo.split(" ");
        this.currentPosition = new Position();
        this.currentPosition
                .setCoordinate(Coordinate.of(Integer.valueOf(positionInfo[0]), Integer.valueOf(positionInfo[1])));
        this.currentPosition.setOrientation(Orientation.valueOf(positionInfo[2]));
    }

    public void printPosition(){
        System.out.println(StringUtils.join(Arrays.asList(currentPosition.getCoordinate().getX(),currentPosition.getCoordinate().getY(),
                        currentPosition.getOrientation().name(), isOnMars ? "" : "LOST"), " "));
    }

}
