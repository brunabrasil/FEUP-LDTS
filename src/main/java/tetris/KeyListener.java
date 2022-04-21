package tetris;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.HashSet;
import java.util.Set;

public class KeyListener { // Singleton
    private static KeyListener instance;
    private final Set<KeyStroke> keyPressed = new HashSet<>();

    private KeyListener() {
    }

    public static KeyListener get() {
        if (KeyListener.instance == null) {
            KeyListener.instance = new KeyListener();
        }
        return KeyListener.instance;
    }

    public static boolean isKeyPressed(KeyStroke key) {
        return get().keyPressed.contains(key);
    }

    public static boolean shouldClose() {
        return isKeyPressed(new KeyStroke(KeyType.EOF));
    }
}
