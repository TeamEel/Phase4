/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.GUIInterface.Sprites.Menu;

import eel.seprphase4.GUIInterface.Sprites.SimpleSprite;
import eel.seprphase4.drawing.Coordinate;
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
public class MenuBackgroundImage implements SimpleSprite {
    private Sprite sprite;


    public MenuBackgroundImage() throws IOException {
        sprite = new Sprite(buildAnimationSet()
                                .animation(singleFrame("/menu/background.png"))
                                .done());
    }

    @Override
    public void addToScreen(Screen screen) {
        screen.addSprite(sprite, 0);
    }

    @Override
    public void moveTo(int x, int y) {
        sprite.moveTo(new Coordinate(x, y));
    }

}
