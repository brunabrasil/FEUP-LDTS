package tetris;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class GameOverTest {

    @Test
    void DrawGameOverTest() throws IOException, IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        TerminalScreen terminalScreen = new TerminalScreen(terminalFactory.createTerminal());
        TextGraphics board= Mockito.mock(TextGraphics.class);
        GameOver gameOver = new GameOver(terminalScreen);
        gameOver.setTextGraphics(board);
        gameOver.drawGameOver();
        Mockito.verify(board, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(board, Mockito.times(2)).setForegroundColor(TextColor.Factory.fromString("#FF8000"));
        Mockito.verify(board, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        Mockito.verify(board, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        Mockito.verify(board, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#008000"));
        Mockito.verify(board, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#00FFFF"));
        Mockito.verify(board, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#800080"));
        Mockito.verify(board, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#00FF00"));
    }
}

