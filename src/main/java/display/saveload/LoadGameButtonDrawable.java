
package display.saveload;

import display.HitBox;
import display.drawable.Drawable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author David
 */
public abstract class LoadGameButtonDrawable implements Drawable {

    private static final int width = 499;
    private static final int height = 50;
    private final Font font;
    private final String username;
    private final String timestamp;

    public LoadGameButtonDrawable(String username, String timestamp) {
        this.username = username;
        this.timestamp = timestamp;
        this.font = new Font("Arial", Font.BOLD, 16);
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();
        g.setColor(color());
        g.setFont(font);
        for (int i = 0; i < 3; i++) {
            g.drawRect(x + xOffset() + i, y + yOffset() + i,
                       width - 2*i, height - 2*i);
        }
        g.drawString(username, x + xOffset() + 6, y + yOffset() + 20);
        g.drawString(timestamp, x + xOffset() + 6 + 300, y + yOffset() + 40);
        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    @Override
    public void advance(int ms) {
        // ignore
    }

    @Override
    public void reset() {
        // ignore
    }

    @Override
    public HitBox hitBox(int x, int y) {
        return new HitBox(new Point(x, y), new Point(x + width, y + height));
    }

    protected abstract Color color();

    protected abstract int xOffset();

    protected abstract int yOffset();
}
