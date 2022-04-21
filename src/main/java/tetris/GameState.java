package tetris;

import java.util.Queue;

public class GameState {
    private final Grid grid;
    private final PieceQueue pieceQueue;
    private int score = 0;
    private Piece currentPiece = null;
    private Piece heldPiece = null;
    private boolean isUnableToHold = false;
    private boolean isGameOver = false;
    private final Coordinate spawnPosition;
    private int resetCount = 0;
    private float fallAccumulator = -1.0f;
    private float levelAccumulator = -1.0f;
    private int currentLevel = 1;
    private int tilesPerSecond = 1;

    public GameState() {
        int width = 10;
        int visibleHeight = 23;
        int height = 2 * visibleHeight;
        int spawnColumn = (width - 1) / 2;
        int spawnRow = visibleHeight - 1;

        this.grid = new Grid(width, height, visibleHeight);
        this.spawnPosition = new Coordinate(spawnColumn, spawnRow);
        this.pieceQueue = new PieceQueue();
        spawnNextPiece();
    }

    private boolean isInCollision() {
        for (Coordinate tilePos : currentPiece.getAbsoluteTilePositions()) {
            if (!grid.isEmpty(tilePos)) return true;
        }
        return false;
    }

    public void holdPiece() {
        if (isUnableToHold) return;
        if (heldPiece == null) {
            heldPiece = currentPiece;
            currentPiece = pieceQueue.pop();
        } else {
            Piece tempPiece = currentPiece;
            tempPiece.reset();
            currentPiece = heldPiece;
            heldPiece = tempPiece;
        }
        spawnPiece();
        isUnableToHold = true;
    }
    
    public void rotate(boolean clockwise) {
        currentPiece.rotate(clockwise);
        if (isInCollision()) currentPiece.rotate(!clockwise);
    }

    public void move(Coordinate movement) {
        currentPiece.move(movement);
        if (isInCollision()) currentPiece.move(movement.negative());
    }

    public void moveDown() {
        move(Coordinate.DOWN);
    }

    public void moveLeft() {
        move(Coordinate.LEFT);
    }

    public void moveRight() {
        move(Coordinate.RIGHT);
    }

    public void lockingSoftDrop() {
        Coordinate initialPosition = currentPiece.getCurrentPosition();
        moveDown();
        if (currentPiece.getCurrentPosition().equals(initialPosition)) {
            lockPiece();
        }
    }

    public int pieceDropDistance() {
        int drop = grid.getHeight();
        for (Coordinate position : currentPiece.getAbsoluteTilePositions()) {
            drop = Math.min(drop, calculateTileDropDistance(position));
        }
        return drop;
    }

    private int calculateTileDropDistance(Coordinate position) {
        int drop = 0;
        while (grid.isEmpty(position.getColumn(), position.getRow() - drop - 1)) {
            drop++;
        }
        return drop;
    }

    public void firmDrop() {
        currentPiece.move(new Coordinate(0, -pieceDropDistance()));
    }

    public void hardDrop() {
        firmDrop();
        lockPiece();
    }

    private void lockPiece() {
        for (Coordinate tilePos : currentPiece.getAbsoluteTilePositions()) {
            grid.set(tilePos.getColumn(), tilePos.getRow(), currentPiece.getIndex());
        }
        score += currentLevel * grid.clearFullRows();
        spawnNextPiece();
    }

    public int getLevel() {
        return currentLevel;
    }

    private void spawnNextPiece() {
        currentPiece = pieceQueue.pop();
        spawnPiece();
        isUnableToHold = false;
    }

    private void spawnPiece() {
        currentPiece.move(spawnPosition);
        if (isInCollision()) currentPiece.move(Coordinate.UP);
        if (isInCollision()) isGameOver = true;

    }


    public int getWidth() {
        return grid.getWidth();
    }

    public int getHeight() {
        return grid.getVisibleHeight();
    }

    public Grid getGrid() {
        return grid;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void update(float dt) {
        fallAccumulator += dt * tilesPerSecond * currentLevel;
        levelAccumulator += dt * 0.1;
        while (fallAccumulator > 0) {
            fallAccumulator -= 1.0f;
            lockingSoftDrop();
        }
        while (levelAccumulator > 0) {
            levelAccumulator -= 1.0f;
            currentLevel += 1;
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver() {
        isGameOver = true;
    }

    public Piece getHoldPiece() {
        return heldPiece;
    }

    public Queue<Piece> getQueue() {
        return pieceQueue.getQueue();
    }

    public int getScore() {
        return score;
    }
}
