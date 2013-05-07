package display.drawable;

import display.HitBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

/**
 *
 * @author David
 */
public class DrawableText implements Drawable {

    private String text;
    private Font font;
    private Color color;

    public DrawableText(String text, Font font, Color color) {
        this.text = text;
        this.font = font;
        this.color = color;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        Font oldFont = g.getFont();
        Color oldColor = g.getColor();
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    @Override
    public void advance(int ms) {
        // ignore
    }

    @Override
    public HitBox hitBox(int x, int y) {
         
        FontRenderContext frc = new FontRenderContext(new AffineTransform(),true,true);

        return HitBox.fromDimensions(x,y-(int) font.getStringBounds(text,frc ).getHeight(),
                (int) font.getStringBounds(text,frc ).getWidth(),
                (int) font.getStringBounds(text,frc ).getHeight());
    }

    @Override
    public void reset() {
        // ignore
    }
}
