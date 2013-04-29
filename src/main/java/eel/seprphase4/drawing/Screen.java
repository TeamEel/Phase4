/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.drawing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author drm511
 */
public class Screen {

    private Image background;
    public SpriteSet sprites;

    public Screen(Image background) {
        this.background = background;
        this.sprites = new SpriteSet();
    }
    
    public Dimension size() {
        return new Dimension(background.getWidth(null), background.getHeight(null));
    }

    public void drawBackground(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }
    
    public void addSprite(Sprite s, int z) {
        sprites.add(s, z);
    }
}
