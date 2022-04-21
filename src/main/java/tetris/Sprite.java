package tetris;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.HashMap;

public class Sprite {
    public final static Sprite IPIECE = new Sprite(' ', Color.CYAN, Color.BLACK);
    public final static Sprite OPIECE = new Sprite(' ', Color.YELLOW, Color.BLACK);
    public final static Sprite TPIECE = new Sprite(' ', Color.PURPLE, Color.BLACK);
    public final static Sprite SPIECE = new Sprite(' ', Color.GREEN, Color.BLACK);
    public final static Sprite ZPIECE = new Sprite(' ', Color.RED, Color.BLACK);
    public final static Sprite JPIECE = new Sprite(' ', Color.BLUE, Color.BLACK);
    public final static Sprite LPIECE = new Sprite(' ', Color.ORANGE, Color.BLACK);
    public final static Sprite BORDER = new Sprite(' ', Color.BABYBLUE, Color.BLACK);
    public final static Sprite BACKGROUND = new Sprite(' ', Color.WHITE, Color.BLACK);

    public final static Sprite[] pieceSprites = { IPIECE, OPIECE, TPIECE, SPIECE, ZPIECE, JPIECE, LPIECE };
    public final static Sprite GHOST = new Sprite(' ', Color.GRAY, Color.BLACK);;

    private static ArrayList<TextCharacter> textCharacterArrayList;
    private static HashMap<TextCharacter, Integer> textCharacterIntMapSet;
    private final int index;

    public Sprite(TextCharacter textCharacter) {
        if (textCharacterArrayList == null) textCharacterArrayList = new ArrayList<>();
        if (textCharacterIntMapSet == null) textCharacterIntMapSet = new HashMap<>();

        int size = textCharacterArrayList.size();
        Integer newIndex = textCharacterIntMapSet.putIfAbsent(textCharacter, size);
        if (newIndex == null) {
            textCharacterArrayList.add(textCharacter);
            newIndex = size;
        }
        this.index = newIndex;
    }

    public Sprite(char ascii, TextColor backgroundColor, TextColor foregroundColor, SGR... modifiers) {
        this(new TextCharacter(
                ascii,
                foregroundColor,
                backgroundColor,
                modifiers));
    }

    public TextCharacter getTextCharacter() {
        return textCharacterArrayList.get(index);
    }
}
