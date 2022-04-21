package tetris;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GridTest {
    Grid grid = new Grid(20,20,30);

    @Test
    void getWidthTest(){
        Assertions.assertEquals(20, grid.getWidth());
    }
    @Test
    void getHeightTest(){
        Assertions.assertEquals(20, grid.getHeight());
    }
    @Test
    void getVisibleHeightTest(){
        Assertions.assertEquals(30, grid.getVisibleHeight());
    }
}
