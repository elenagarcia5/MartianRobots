package garcia.herrero.MartianRobots.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int rows;
    private final int columns;
    private List<Coordinate> scentCoordinates = new ArrayList<>();

    private static Board board;

    public static void createBoardFromInput(String commandLineInfo) {
    	String[] columnsAndRows = commandLineInfo.split(" ");
    	board = new Board(Integer.valueOf(columnsAndRows[0]), Integer.valueOf(columnsAndRows[1]));
    }

    public static Board getBoard() {
        return board;
    }

    private Board(int columns, int rows) {
        super();
        this.columns = columns;
        this.rows = rows;
    }

    public boolean isCoordinateWithinBounds(Coordinate coordinate) {
        return coordinate.getX() <= columns && coordinate.getY() <= rows;
    }

    public boolean isScentCoordinate(Coordinate coordinate) {
        return scentCoordinates.contains(coordinate);
    }

    public List<Coordinate> getScentCoordinates() {
        return scentCoordinates;
    }
}
