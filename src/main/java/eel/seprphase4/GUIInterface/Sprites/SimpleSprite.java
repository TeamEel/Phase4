/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.GUIInterface.Sprites;

import eel.seprphase4.drawing.Screen;

/**
 *
 * @author drm511
 */
public interface SimpleSprite {

    public void addToScreen(Screen screen);

    public void moveTo(int x, int y);
}
