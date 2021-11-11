package garcia.herrero.MartianRobots.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int rows;
    private final int columns;
    private List<Coordinate> scentCoordinates = new ArrayList<>();

    private static Board board;

    public static void createBoard(int columns, int rows) {
    	if(board != null){
    		board = new Board(columns, rows);
    	}
    }

    public static Board getBoard() {
        if(  board == null){
            board = new Board(0, 0);
        }
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
