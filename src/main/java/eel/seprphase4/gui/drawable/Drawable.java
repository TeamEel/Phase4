package eel.seprphase4.gui.drawable;

import eel.seprphase4.gui.HitBox;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public interface Drawable {

    public void draw(Graphics g, int x, int y);

    public void advance(int ms);

    public void reset();
    
    public HitBox hitBox(int x, int y);
}
