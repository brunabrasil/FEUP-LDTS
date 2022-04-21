package tetris;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


public class Tetris { // Singleton
    private int terminalWidth, terminalHeight;
    private String title;
    private TerminalScreen terminalScreen = null;

    private static Tetris tetris = null;
    private RenderingManager renderingManager;
    private GameState gameState;
    private boolean isGameOverScreen = false;
    public Clip clip = null;


    private Tetris(){
        this.terminalWidth = 24; //24
        this.terminalHeight = 25; // ou 70 35
        this.title = "Tetris";
    }

    public static Tetris get()  {
        if (Tetris.tetris == null) {
            Tetris.tetris = new Tetris();
        }
        return Tetris.tetris;
    }

    public void run() {

        try {
            init();
            startMenu();
            loop();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException, URISyntaxException, FontFormatException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {

        URL resource = getClass().getClassLoader().getResource("square.ttf");
        File fontFile = new File(resource.toURI());
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setTerminalEmulatorTitle(title)
                .setInitialTerminalSize(new TerminalSize(terminalWidth, terminalHeight));
        terminalFactory.setForceAWTOverSwing(true);
        Font loadedFont = font.deriveFont(Font.BOLD, 15);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);

        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);

        terminalScreen = new TerminalScreen(terminalFactory.createTerminal());

        terminalScreen.setCursorPosition(null);
        terminalScreen.startScreen();
        terminalScreen.refresh();
        gameState = new GameState();

        renderingManager = new RenderingManager(terminalScreen);

        URL resourceMusic = getClass().getClassLoader().getResource("tetris.wav");
        File wavFile = new File(resourceMusic.toURI());
        clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(wavFile));
    }


    public void loop() throws IOException, InterruptedException {
        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;

        while (!KeyListener.shouldClose()) {
            KeyStroke key = pollInput();
            processKey(key);

            if (dt >= 0) gameState.update(dt);

            if (gameState.isGameOver()) {
                gameOver();
                break;

            } else {
                render(dt);
            }

            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
        terminalScreen.close();
    }

    private void processKey(KeyStroke key) throws IOException, InterruptedException {
        if (key == null) return;
        if (key.getKeyType() == KeyType.EOF) gameState.setGameOver();
        else if (key.getKeyType() == KeyType.ArrowUp) gameState.rotate(true);
        else if (key.getKeyType() == KeyType.ArrowDown) gameState.moveDown();
        else if (key.getKeyType() == KeyType.ArrowLeft) gameState.moveLeft();
        else if (key.getKeyType() == KeyType.ArrowRight) gameState.moveRight();
        if (key.getKeyType() == KeyType.Enter) {
            terminalScreen.clear();
            //loop();
        }
        else if (key.getKeyType() == KeyType.Character) {
            if (key.getCharacter() == 'c' || key.getCharacter() == 'C') gameState.holdPiece();
            else if (key.getCharacter() == ' ')
                gameState.hardDrop();
            else if (key.getCharacter() == 'x' || key.getCharacter() == 'X') gameState.rotate(false);

            else if (key.getCharacter() == 'Q' || key.getCharacter() == 'q'){
                terminalScreen.stopScreen();
                terminalScreen.close();
            }
        }

    }

    private void gameOver() throws IOException, InterruptedException {
        clip.close();
        terminalScreen.clear();
        isGameOverScreen = true;

        GameOver gameOver = new GameOver(terminalScreen);
        gameOver.drawGameOver();
        terminalScreen.refresh();
        while (true){
            KeyStroke key = pollInput();
            if (key == null) continue;
            else if (key.getCharacter() == 'Q' || key.getCharacter() == 'q') {
                terminalScreen.stopScreen();
                terminalScreen.close();
                break;
            }
        }

    }
    private void startMenu() throws IOException, InterruptedException {
        terminalScreen.clear();
        Menu menu = new Menu(terminalScreen);
        menu.run();
        terminalScreen.refresh();
        while (true){
            KeyStroke key = pollInput();
            if (key == null) continue;
            if (key.getKeyType() == KeyType.Enter) {
                terminalScreen.clear();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                break;
            }
        }
    }

    private KeyStroke pollInput() throws IOException {

        KeyStroke key = terminalScreen.pollInput();
        return key;
    }

    private void render(float dt) throws IOException {
        renderingManager.update(dt);
        if (renderingManager.isNewFrameAvailable()) {
            terminalScreen.doResizeIfNecessary();
            terminalScreen.clear();
            renderingManager.draw(gameState);
            terminalScreen.refresh();
        }
    }

}
