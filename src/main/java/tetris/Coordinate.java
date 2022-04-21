package tetris;

public final class Coordinate {
    public static final Coordinate ZERO = new Coordinate(0, 0);
    public static final Coordinate UP = new Coordinate(0, 1);
    public static final Coordinate DOWN = new Coordinate(0, -1);
    public static final Coordinate LEFT = new Coordinate(-1, 0);
    public static final Coordinate RIGHT = new Coordinate(1, 0);

    private final int column;
    private final int row;

    public Coordinate(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }
    public int getRow() {
        return row;
    }

    public boolean equals(Coordinate position) {
        return column == position.column && row == position.row;
    }

    public Coordinate plus(Coordinate position) {
        return new Coordinate(column + position.column, row + position.row);
    }

    public Coordinate rotateClockwise() {
        return new Coordinate(-row, column);
    }

    public Coordinate rotateCounterClockwise() {
        return new Coordinate(row, -column);
    }

    public Coordinate negative() {
        return new Coordinate(-column, -row);
    }
}
