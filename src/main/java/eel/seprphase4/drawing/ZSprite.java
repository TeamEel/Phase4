package eel.seprphase4.drawing;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Encapsulate a Sprite with an associated z-value
 * 
 * Provides the interface needed by SpriteSet and (indirectly) SpriteCanvas.
 * 
 * @author drm
 */
public class ZSprite implements Comparable<ZSprite>, MouseControllable {

    private Sprite sprite;
    private int z;

    public ZSprite(Sprite sprite, int z) {
        this.sprite = sprite;
        this.z = z;
    }

    public void advance() {
        sprite.advance();
    }

    public void paint(Graphics g) {
        sprite.paint(g);
    }

    @Override
    public int compareTo(ZSprite other) {
        return new Integer(z).compareTo(other.z);
    }

    public boolean contains(Point p) {
        return sprite.contains(new Coordinate(p));
    }

    @Override
    public void mouseEntered() {
        sprite.mouseEntered();
    }

    @Override
    public void mouseExited() {
        sprite.mouseExited();
    }

    @Override
    public void leftClicked() {
        sprite.leftClicked();
    }

    @Override
    public void rightClicked() {
        sprite.rightClicked();
    }
}
