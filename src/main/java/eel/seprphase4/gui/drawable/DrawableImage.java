package eel.seprphase4.gui.drawable;

import eel.seprphase4.gui.HitBox;
import eel.seprphase4.gui.ResourceLoader;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author David
 */
public class DrawableImage implements Drawable {

    private Image image;

    public DrawableImage(String resource) {
        this.image = ResourceLoader.imageResource(resource);
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    @Override
    public void advance(int ms) {
        // do nothing
    }

    @Override
    public HitBox hitBox(int x, int y) {
        return HitBox.fromImage(image, x, y);
    }

    @Override
    public void reset() {
        // ignore
    }
}
