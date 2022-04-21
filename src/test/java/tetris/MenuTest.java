package tetris;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class MenuTest {
    @Test
    void drawMenuTest() throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        TerminalScreen terminalScreen = new TerminalScreen(terminalFactory.createTerminal());
        TextGraphics board = Mockito.mock(TextGraphics.class);
        Menu menu = new Menu(terminalScreen);
        menu.setTextGraphics(board);
        menu.drawMenu();
        Mockito.verify(board, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(board, Mockito.times(3)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(board, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        Mockito.verify(board, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FF8000"));
        Mockito.verify(board, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        Mockito.verify(board, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#008000"));
        Mockito.verify(board, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#00FFFF"));
        Mockito.verify(board, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#800080"));
    }

}
