package tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PieceFactory {

    private static final Coordinate[][] pieceIRotationOffsets = {
            {
                    Coordinate.ZERO,
                    new Coordinate(-1, 0),
                    new Coordinate(-1, 1),
                    new Coordinate(0, 1)},
            {
                    new Coordinate(-1, 0),
                    Coordinate.ZERO,
                    new Coordinate(1, 1),
                    new Coordinate(0, 1)},
            {
                    new Coordinate(2, 0),
                    Coordinate.ZERO,
                    new Coordinate(-2, 1),
                    new Coordinate(0, 1)},
            {
                    new Coordinate(-1, 0),
                    new Coordinate(0, 1),
                    new Coordinate(1, 0),
                    new Coordinate(0, -1)},
            {
                    new Coordinate(2, 0),
                    new Coordinate(0, -2),
                    new Coordinate(-2, 0),
                    new Coordinate(0, 2)}
    };

    private static final Coordinate[][] pieceORotationOffsets = {
            {
                    Coordinate.ZERO,
                    Coordinate.DOWN,
                    new Coordinate(-1, -1),
                    Coordinate.LEFT}
    };

    private static final Coordinate[][] piecesJLSTZRotationOffsets = {
            {
                    Coordinate.ZERO,
                    Coordinate.ZERO,
                    Coordinate.ZERO,
                    Coordinate.ZERO},
            {
                    Coordinate.ZERO,
                    new Coordinate(1, 0),
                    Coordinate.ZERO,
                    new Coordinate(-1, 0)},
            {
                    Coordinate.ZERO,
                    new Coordinate(1, -1),
                    Coordinate.ZERO,
                    new Coordinate(-1, -1)},
            {
                    Coordinate.ZERO,
                    new Coordinate(0, 2),
                    Coordinate.ZERO,
                    new Coordinate(0, 2)},
            {
                    Coordinate.ZERO,
                    new Coordinate(1, 2),
                    Coordinate.ZERO,
                    new Coordinate(-1, 2)}
    };

    private static final Coordinate[][] customRotationOffsets = {
            {
                    Coordinate.ZERO,
                    Coordinate.ZERO,
                    Coordinate.ZERO,
                    Coordinate.ZERO}
    };

    private static final ArrayList<Coordinate[][]> rotationOffsetMapping = new ArrayList<>(Arrays.asList(
            pieceIRotationOffsets,
            pieceORotationOffsets,
            piecesJLSTZRotationOffsets,
            piecesJLSTZRotationOffsets,
            piecesJLSTZRotationOffsets,
            piecesJLSTZRotationOffsets,
            piecesJLSTZRotationOffsets
    ));

    private final static Coordinate[] pieceIRelativesFacingNorth = {
            new Coordinate(-1, 0),
            new Coordinate(0, 0),
            new Coordinate(1, 0),
            new Coordinate(2, 0)
    };

    private final static Coordinate[] pieceORelativesFacingNorth = {
            new Coordinate(0, 0),
            new Coordinate(1, 0),
            new Coordinate(0, 1),
            new Coordinate(1, 1)
    };

    private final static Coordinate[] pieceTRelativesFacingNorth = {
            new Coordinate(-1, 0),
            new Coordinate(0, 0),
            new Coordinate(1, 0),
            new Coordinate(2, 0)
    };

    private final static Coordinate[] pieceSRelativesFacingNorth = {
            new Coordinate(-1, 0),
            new Coordinate(0, 0),
            new Coordinate(1, 0),
            new Coordinate(2, 0)
    };

    private final static Coordinate[] pieceZRelativesFacingNorth = {
            new Coordinate(-1, 0),
            new Coordinate(0, 0),
            new Coordinate(1, 0),
            new Coordinate(2, 0)
    };

    private final static Coordinate[] pieceJRelativesFacingNorth = {
            new Coordinate(-1, 0),
            new Coordinate(0, 0),
            new Coordinate(1, 0),
            new Coordinate(2, 0)
    };

    private final static Coordinate[] pieceLRelativesFacingNorth = {
            new Coordinate(-1, 0),
            new Coordinate(0, 0),
            new Coordinate(1, 0),
            new Coordinate(2, 0)
    };


    private static final ArrayList<Coordinate[][]> relativeTilePosMapping = new ArrayList<>();

    static {
        Coordinate[][] piecesNorthFacing = {
                { new Coordinate(0, 0), new Coordinate(-1, 0), new Coordinate(1, 0), new Coordinate(2, 0) },
                { new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(1, 0), new Coordinate(1, 1) },
                { new Coordinate(0, 0), new Coordinate(-1, 0), new Coordinate(1, 0), new Coordinate(0, 1) },
                { new Coordinate(0, 0), new Coordinate(-1, 0), new Coordinate(0, 1), new Coordinate(1, 1) },
                { new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(0, 1), new Coordinate(-1, 1) },
                { new Coordinate(0, 0), new Coordinate(-1, 0), new Coordinate(1, 0), new Coordinate(-1, 1) },
                { new Coordinate(0, 0), new Coordinate(-1, 0), new Coordinate(1, 0), new Coordinate(1, 1) }
        };
        for (int k = 0; k < 7; k++) {
            Coordinate[][] temp = new Coordinate[4][4];
            Coordinate[] tilesNorthCurrentPiece = piecesNorthFacing[k];
            for (int i = 0; i < 4; i++) {
                Coordinate currTile = tilesNorthCurrentPiece[i];
                temp[0][i] = currTile;
                temp[1][i] = currTile.rotateClockwise();
                temp[2][i] = currTile.negative();
                temp[3][i] = currTile.rotateCounterClockwise();
            }
            relativeTilePosMapping.add(temp);
        }
    }

    public static List<Piece> getShuffledBatch() {
        List<Piece> freshBatch = new ArrayList<>();
        for (int index = 0; index < 7; index++) {
            freshBatch.add(getInstance(index));
        }
        Collections.shuffle(freshBatch);
        return freshBatch;
    }
    
    public static Piece getInstance(int index) {
        return new Piece(index, relativeTilePosMapping.get(index), rotationOffsetMapping.get(index));
    }

    public static Piece from(Piece piece) {
        return getInstance(piece.getIndex());
    }

    public static int getPoolSize() {
        return relativeTilePosMapping.size();
    }

}
