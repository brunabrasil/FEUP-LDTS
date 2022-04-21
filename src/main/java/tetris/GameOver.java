package tetris;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GameOver {
    TextGraphics textGraphics;
    public GameOver(TerminalScreen terminalScreen) {
        textGraphics = terminalScreen.newTextGraphics();

    }
    public void drawGameOver() {
        textGraphics.setBackgroundColor(Color.BLACK);
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(30, 60), ' ');

        textGraphics.setForegroundColor(Color.RED);
        textGraphics.putString(7, 6, "G");
        textGraphics.setForegroundColor(Color.ORANGE);
        textGraphics.putString(8, 6, "A");
        textGraphics.setForegroundColor(Color.YELLOW);
        textGraphics.putString(9, 6, "M");
        textGraphics.setForegroundColor(Color.GREEN);
        textGraphics.putString(10, 6, "E");
        textGraphics.setForegroundColor(Color.CYAN);
        textGraphics.putString(12, 6, "O");
        textGraphics.setForegroundColor(Color.PURPLE);
        textGraphics.putString(13, 6, "V");
        textGraphics.setForegroundColor(Color.ORANGE);
        textGraphics.putString(14, 6, "E");
        textGraphics.setForegroundColor(Color.LIME);
        textGraphics.putString(15, 6, "R");

    }
    public void setTextGraphics(TextGraphics textGraphics){
        this.textGraphics = textGraphics;
    }

}


