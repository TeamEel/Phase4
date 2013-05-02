package display.drawable;

import display.*;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public interface Drawable {

    public void draw(Graphics g, int x, int y);

    public void advance(int ms);

    public HitBox hitBox(int x, int y);
}
