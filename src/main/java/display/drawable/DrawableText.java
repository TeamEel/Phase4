package display.drawable;

import display.HitBox;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class DrawableText implements Drawable {

    private String text;
    private Font font;

    public DrawableText(String text, Font font) {
        this.text = text;
        this.font = font;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        //Font oldFont = g.getFont();
        //g.setFont(font);
        g.drawString(text, x, y);
        //g.setFont(oldFont);
    }

    @Override
    public void advance(int ms) {
        // ignore
    }

    @Override
    public HitBox hitBox(int x, int y) {
        return HitBox.nullHitBox();
    }
}
