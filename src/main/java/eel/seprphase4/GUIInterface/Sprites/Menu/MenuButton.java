/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.GUIInterface.Sprites.Menu;

import eel.seprphase4.GUIInterface.Sprites.SimpleSprite;
import eel.seprphase4.drawing.Coordinate;
import eel.seprphase4.drawing.MouseControllable;
import eel.seprphase4.drawing.Screen;
import eel.seprphase4.drawing.Sprite;
import eel.seprphase4.drawing.SpriteCanvas;
import static eel.seprphase4.drawing.builders.BuildAnimation.singleFrame;
import static eel.seprphase4.drawing.builders.BuildAnimationSet.buildAnimationSet;
import static eel.seprphase4.drawing.builders.BuildAnimation.range;
import java.io.IOException;

/**
 *
 * @author James
 */
public class MenuButton implements SimpleSprite, MouseControllable {

    private Sprite sprite;

    public MenuButton(String name) throws IOException {
        sprite = new Sprite(buildAnimationSet()
                .animation(singleFrame("/menu/button_" + name + ".png"))
                .animation(singleFrame("/menu/button_" + name + "_sel.png"))
                .done());
        sprite.setMouseListener(this);
    }

    @Override
    public void addToScreen(Screen screen) {
        screen.addSprite(sprite, 1);
    }

    @Override
    public void moveTo(int x, int y) {
        sprite.moveTo(new Coordinate(x, y));
    }

    @Override
    public void mouseEntered() {
        sprite.selectAnimation(1);
    }

    @Override
    public void mouseExited() {
        sprite.selectAnimation(0);
    }

    @Override
    public void leftClicked() {
        System.out.println("button left clicked");
    }

    @Override
    public void rightClicked() {
        System.out.println("button right clicked");
    }
}
