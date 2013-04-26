/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.GUIInterface.Sprites;

import eel.seprphase4.drawing.SpriteCanvas;

/**
 *
 * @author James
 */
public interface SimpleSprite {

    public void addToCanvas(SpriteCanvas canvas);

    public void moveTo(int x, int y);
}
