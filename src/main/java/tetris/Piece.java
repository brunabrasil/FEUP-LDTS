package tetris;

public class Piece {
    private final Coordinate[][] relativeTilePositions;
    private final Coordinate[][] rotationOffsets;
    private final int index;
    private int rotationState;
    private Coordinate currentPosition;

    public Piece(int index, Coordinate[][] relativeTilePositions, Coordinate[][] rotationOffsets) {
        this.index = index;
        this.rotationState = 0;
        this.relativeTilePositions = relativeTilePositions;
        this.rotationOffsets = rotationOffsets;
        reset();
    }

    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    public Coordinate[] getAbsoluteTilePositions() {
        Coordinate[] absoluteTilePositions = new Coordinate[relativeTilePositions.length];
        for (int i = 0; i < absoluteTilePositions.length; i++) {
            absoluteTilePositions[i] = currentPosition.plus(relativeTilePositions[rotationState][i]);
        }
        return absoluteTilePositions;
    }

    public Coordinate[] getRelativeTilePositions() {
        return relativeTilePositions[rotationState];
    }

    public void rotate(boolean clockwise) {
        rotationState += relativeTilePositions.length + (clockwise ? 1 : -1);
        rotationState %= relativeTilePositions.length;
    }

    public void move(Coordinate movement) {
        currentPosition = currentPosition.plus(movement);
    }

    public void reset() {
        rotationState = 0;
        currentPosition = Coordinate.ZERO;
    }

    public int getIndex() {
        return index;
    }
}
