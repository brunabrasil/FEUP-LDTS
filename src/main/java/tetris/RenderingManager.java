package tetris;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.util.Queue;

public class RenderingManager {
    private TerminalScreen terminalScreen;
    private int verticalScaling = 1;
    private int horizontalScaling = verticalScaling;
    private boolean isNewFrameAvailable = false;
    private float secondsSinceLastFrame = 0.0f;
    private float secondsPerFrame = 1.0f / 30;

    public RenderingManager(TerminalScreen terminalScreen) {
        this.terminalScreen = terminalScreen;
    }

    public void draw(GameState gameState) {
        isNewFrameAvailable = false;

        TextImage textImage = getGameStateImage(gameState);
        TextGraphics textGraphics = terminalScreen.newTextGraphics();
        int columns = textImage.getSize().getColumns();
        int rows = textImage.getSize().getRows();
        int lastRow = rows - 1;
        TerminalSize terminalSize = new TerminalSize(horizontalScaling, verticalScaling);
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < columns; i++) {
                textGraphics.fillRectangle(
                        new TerminalPosition(i * horizontalScaling, j * verticalScaling),
                        terminalSize,
                        textImage.getCharacterAt(i, lastRow - j));
            }
        }
    }

    private TextImage getGameStateImage(GameState gameState) {
        TextImage boardImage = getBoardImage(gameState);
        TerminalSize boardSize = boardImage.getSize();

        int leftSpace = 6;
        int rightSpace = 6;
        TextImage gameStateImage = new BasicTextImage(boardSize.withRelativeColumns(leftSpace + rightSpace));
        boardImage.copyTo(gameStateImage, 0, boardSize.getRows(), 0, boardSize.getColumns(), 0, leftSpace);

        TextGraphics textGraphics = gameStateImage.newTextGraphics();

        // Hold Piece
        Piece holdPiece = gameState.getHoldPiece();
        if (holdPiece != null) {
            TextCharacter textCharacter = Sprite.pieceSprites[holdPiece.getIndex()].getTextCharacter();
            for (Coordinate tile : holdPiece.getRelativeTilePositions()) {
                textGraphics.setCharacter(
                        tile.getColumn() + 2,
                        tile.getRow() + 18,
                        textCharacter);
            }
        }

        // Queue
        int row = 18;
        int column = 20;
        int verticalDistanceBetweenQueuePieceCenters = 4;
        Queue<Piece> queue = gameState.getQueue();
        int max = 5;
        int curr = 0;
        for (Piece piece : queue) {
            if (curr == max) break;
            curr++;
            Coordinate[] tilePositions = piece.getRelativeTilePositions();
            TextCharacter textCharacter = Sprite.pieceSprites[piece.getIndex()].getTextCharacter();
            for (Coordinate tile : tilePositions) {
                textGraphics.setCharacter(
                        tile.getColumn() + column,
                        tile.getRow() + row,
                        textCharacter);
            }
            row -= verticalDistanceBetweenQueuePieceCenters;

        }
        // Score
        textGraphics.putString(0, 3, "score:");
        textGraphics.putString(2, 1, "" + 10 * gameState.getScore());
        textGraphics.putString(0, 7, "level:");
        textGraphics.putString(2, 5, "" + gameState.getLevel());

        return gameStateImage;
    }

    private TextImage getBoardImage(GameState gameState) {
        TerminalSize innerSize = new TerminalSize(
                gameState.getWidth(),
                gameState.getHeight());
        TerminalSize outerSize = innerSize.withRelative(2, 2);
        TextImage textImage = new BasicTextImage(outerSize);
        TextGraphics textGraphics = textImage.newTextGraphics();

        // Border
        textGraphics.drawRectangle(
                new TerminalPosition(0, 0),
                outerSize,
                Sprite.BORDER.getTextCharacter()
        );
        // Grid
        textGraphics.fillRectangle(
                new TerminalPosition(1, 1),
                innerSize,
                Sprite.BACKGROUND.getTextCharacter()
        );
        // Locked Pieces
        Grid grid = gameState.getGrid();
        int row = 0;
        int width = grid.getWidth();
        while (!grid.isRowEmpty(row)) {
            for (int i = 0; i < width; i++) {
                Integer index = grid.get(i, row);
                if (index != null && row < grid.getVisibleHeight()) {
                    textGraphics.setCharacter(i + 1, row + 1, Sprite.pieceSprites[index].getTextCharacter());
                }
            }
            row++;
        }
        // Ghost Piece
        Piece currentPiece = gameState.getCurrentPiece();
        int pieceDropDistance = gameState.pieceDropDistance();
        currentPiece.move(new Coordinate(0, -pieceDropDistance));
        Coordinate[] absoluteTilePositions = currentPiece.getAbsoluteTilePositions();
        TextCharacter textCharacter = Sprite.GHOST.getTextCharacter();
        for (Coordinate tile : absoluteTilePositions) {
            textGraphics.setCharacter(
                    tile.getColumn() + 1,
                    tile.getRow() + 1,
                    textCharacter);
        }
        currentPiece.move(new Coordinate(0, pieceDropDistance));
        // Current Piece
        absoluteTilePositions = currentPiece.getAbsoluteTilePositions();
        textCharacter = Sprite.pieceSprites[currentPiece.getIndex()].getTextCharacter();
        for (Coordinate tile : absoluteTilePositions) {
            if (tile.getRow() < grid.getVisibleHeight()) {
                textGraphics.setCharacter(
                        tile.getColumn() + 1,
                        tile.getRow() + 1,
                        textCharacter);
            }
        }

        return textImage;
    }

    public void update(float dt) {
        secondsSinceLastFrame += dt;
        if (secondsSinceLastFrame > 0) {
            secondsSinceLastFrame -= secondsPerFrame;
            isNewFrameAvailable = true;
        }
    }

    public boolean isNewFrameAvailable() {
        return isNewFrameAvailable;
    }

}
