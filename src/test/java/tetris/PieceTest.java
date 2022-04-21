package tetris;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PieceTest {

    Coordinate[][] relativeTilePositions= new Coordinate[10][10];
    Coordinate[][] rotationOffsets= new Coordinate[10][10];
    Piece piece = new Piece(1, relativeTilePositions,rotationOffsets);
    @Test
    void GetIndexTest() {
        Assertions.assertEquals(1, piece.getIndex());
    }
    @Test
    void equalsCoordinateTest(){
        Assertions.assertNotNull(equals(relativeTilePositions));
    }
}
