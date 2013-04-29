/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.drawing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

/**
 *
 * @author drm511
 */
public class Screen {

    private Image background;
    private SpriteSet sprites;

    public Screen(Image background) {
        this.background = background;
        this.sprites = new SpriteSet();
    }

    public Dimension size() {
        return new Dimension(background.getWidth(null), background.getHeight(null));
    }

    public void mouseReleased(MouseEvent e) {
        sprites.mouseReleased(e);
    }

    public void mouseExited(MouseEvent e) {
        sprites.mouseExited(e);
    }

    public void mouseMoved(MouseEvent e) {
        sprites.mouseMoved(e);
    }

    public void addSprite(Sprite s, int z) {
        sprites.add(s, z);
    }

    public void paint(Graphics g) {
        drawBackground(g);
        sprites.paint(g);
    }

    public void advance() {
        sprites.advance();
    }

    private void drawBackground(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }
}
