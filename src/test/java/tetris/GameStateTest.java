package tetris;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameStateTest {
    Coordinate c1= new Coordinate(0,0);
    GameState gameState = new GameState();

    @Test
    void moveDownTest() {
        gameState.moveDown();
        Assertions.assertEquals(0, c1.getRow());
    }

    @Test
    void moveLeftTest() {
        gameState.moveLeft();
        Assertions.assertEquals(0, c1.getColumn());
    }

    @Test
    void moveRightTest() {
        gameState.moveRight();
        Assertions.assertEquals(0, c1.getColumn());
    }
}
