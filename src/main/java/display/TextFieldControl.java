package display;

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

    private DrawableText text;
    private int x, y;
    private boolean hasFocus;

    public TextFieldControl(String text, Font font, Color color, int x, int y) {
        this.text = new DrawableText(text, font, color);
        this.x = x;
        this.y = y;
        this.hasFocus = true;
    }

    @Override
    public void paint(Graphics g) {
        text.draw(g, x, y);
    }

    @Override
    public void advance(int ms) {
        text.advance(ms);
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
        // TODO: take focus if contains point, otherwise remove
        return false;
    }

    @Override
    public boolean onMouseReleased(Point point) {
        return false;
    }

    @Override
    public boolean onKeyPressed(KeyEvent e) {
        if (hasFocus) {
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
            if (!e.isActionKey()) {
                if (e.getKeyChar() == '\b') {
                    String s = text.getText();
                    if (s.length() == 0) {
                        return true;
                    }
                    s = s.substring(0, s.length() - 1);
                    text.setText(s);
                } else {
                    String s = text.getText();
                    s = s + e.getKeyChar();
                    text.setText(s);
                }
            }
            return true;
        }
        return false;
    }
}
