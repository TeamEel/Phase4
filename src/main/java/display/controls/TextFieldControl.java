package display.controls;

import display.Asset;
import display.Control;
import display.DrawableFactory;
import display.HitBox;
import display.drawable.Drawable;
import display.drawable.DrawableText;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author David
 */
public class TextFieldControl implements Control {

    private static final int cursorWidth = 3;
    private static final int cursorHeight = 70;
    private static final int textXOffset = 10;
    private static final int textYOffset = 60;
    private static final int maxChars = 16;
    private static final Drawable background = DrawableFactory.create(Asset.TextFieldBackground);
    private static final Drawable selectedBackground = DrawableFactory.create(Asset.TextFieldSelectedBackground);
    private final HitBox hitBox;
    private final int x, y;
    private DrawableText text;
    private boolean hasFocus;
    private int msElapsed;
    private int cursor;

    public TextFieldControl(String text, int x, int y) {
        this.hitBox = background.hitBox(x, y);
        this.x = x;
        this.y = y;
        this.text = new DrawableText(text, new Font("Courier New", Font.BOLD, 50), Color.WHITE);
        this.hasFocus = false;
        this.msElapsed = 0;
        this.cursor = text.length();
    }

    public void setText(String s) {
        if (s.length() > maxChars) {
            s = s.substring(0, maxChars);
        }
        text.setText(s);
    }

    public String getText() {
        return text.getText();
    }

    @Override
    public void paint(Graphics g) {
        if (hasFocus) {
            selectedBackground.draw(g, x, y);
            if (msElapsed < 500) {
                drawCursor(g);
            }
        } else {
            background.draw(g, x, y);
        }
        text.draw(g, x + textXOffset, y + textYOffset);
    }

    @Override
    public void advance(int ms) {
        text.advance(ms);
        msElapsed += ms;
        msElapsed %= 1000;
    }

    @Override
    public void onMouseExited() {
        // do nothing
    }

    @Override
    public void onMouseMoved(Point point) {
        // do nothing
    }

    @Override
    public boolean onMousePressed(Point point) {
        if (hasFocus && !hitBox.contains(point)) {
            hasFocus = false;
            return false;
        } else if (!hasFocus && hitBox.contains(point)) {
            hasFocus = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean onMouseReleased(Point point) {
        return false;
    }

    @Override
    public boolean onKeyPressed(KeyEvent e) {
        if (hasFocus) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    moveRight();
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    deleteBackward();
                    break;
                case KeyEvent.VK_DELETE:                    
                    deleteForward();
                    break;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyReleased(KeyEvent e) {
        if (hasFocus) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyTyped(KeyEvent e) {
        if (hasFocus) {
            char c = e.getKeyChar();
            if (Character.isLetterOrDigit(c) || Character.isSpaceChar(c)) {
                insert(e.getKeyChar());
            }
            return true;
        }
        return false;
    }

    private void drawCursor(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(x + textXOffset + cursorX(), y + 5,
                   cursorWidth, cursorHeight);
        g.setColor(oldColor);
    }

    private int cursorX() {
        return cursor * 30;
    }

    private void deleteBackward() {
        String s = text.getText();
        if (s.length() == 0) {
            return;
        }
        s = s.substring(0, cursor - 1) + s.substring(cursor, s.length());
        cursor--;
        setText(s);
    }
    
    private void deleteForward() {
        String s = text.getText();
        if (s.length() == 0 || cursor == s.length()) {
            return;
        }
        s = s.substring(0, cursor) + s.substring(cursor + 1, s.length());
        setText(s);
    }

    private void insert(char c) {
        String s = text.getText();
        s = s.substring(0, cursor) + c + s.substring(cursor, s.length());
        cursor++;
        setText(s);
    }

    private void moveLeft() {
        cursor--;
        if (cursor < 0) {
            cursor = 0;
        }
    }

    private void moveRight() {
        int max = text.getText().length();
        cursor++;
        if (cursor > max) {
            cursor = max;
        }
    }
}
