package tetris;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeTest {
    Time time = new Time();

    @Test
    void GetTimeTest() {
        Assertions.assertNotNull( time.getTime());
    }
}
