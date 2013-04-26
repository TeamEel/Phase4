/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.GUIInterface.Sprites.Menu;

import eel.seprphase4.GUIInterface.Sprites.SimpleSprite;
import eel.seprphase4.drawing.Coordinate;
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
public class MenuButton implements SimpleSprite {

    private Sprite sprite;


    public MenuButton(String name) throws IOException {
        sprite = new Sprite(buildAnimationSet()
                                .animation(singleFrame("/menu/button_"+name+".png"))
                                .done());
    }

    @Override
    public void addToCanvas(SpriteCanvas canvas) {
        canvas.add(sprite, 3);
    }

    @Override
    public void moveTo(int x, int y) {
        sprite.moveTo(new Coordinate(x, y));
    }

   

 

}
