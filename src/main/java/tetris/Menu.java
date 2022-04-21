package tetris;
import tetris.Tetris;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Menu {
    TextGraphics textGraphics;
    TerminalScreen terminalScreen;
    public Menu(TerminalScreen terminalScreen) {
        textGraphics = terminalScreen.newTextGraphics();
        this.terminalScreen = terminalScreen;
    }
    public void drawMenu() {
        textGraphics.setBackgroundColor(Color.BLACK);
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(30, 60), ' ');

        textGraphics.setForegroundColor(Color.RED);
        textGraphics.putString(8, 6, "T");
        textGraphics.setForegroundColor(Color.ORANGE);
        textGraphics.putString(9, 6, "E");
        textGraphics.setForegroundColor(Color.YELLOW);
        textGraphics.putString(10, 6, "T");
        textGraphics.setForegroundColor(Color.GREEN);
        textGraphics.putString(11, 6, "R");
        textGraphics.setForegroundColor(Color.CYAN);
        textGraphics.putString(12, 6, "I");
        textGraphics.setForegroundColor(Color.PURPLE);
        textGraphics.putString(13, 6, "S");

        textGraphics.setForegroundColor(Color.WHITE);
        textGraphics.putString(2, 12, "Press ENTER to play");
        textGraphics.setForegroundColor(Color.WHITE);
        textGraphics.putString(2, 14, "Press 'q' to exit");
        textGraphics.setForegroundColor(Color.WHITE);
        textGraphics.putString(2, 15, "any moment");

    }
    public void run() {
        drawMenu();

    }
    public void setTextGraphics(TextGraphics textGraphics){
        this.textGraphics = textGraphics;
    }
}
