package eel.seprphase4.drawing;

import eel.seprphase4.drawing.animation.AnimationSet;
import java.awt.Graphics;

/**
 *
 * @author drm
 */
public class Sprite implements MouseControllable {

    private AnimationSet animations;
    private Coordinate position;
    private MouseControllable mouseControl;
    
    public Sprite(AnimationSet animations) {
        this(animations, new Coordinate(0, 0));
    }
    
    public Sprite(AnimationSet animations, Coordinate position) {
        this.animations = animations;
        this.position = position;
    }

    /**
     * Select an animation from the set of animations available to the Sprite
     * 
     * Resets the animation back to the beginning of the sequence of frames.
     * Should therefore only be called when the animation should *change* in some way.
     * Calling this function multiple times with the same argument may cause the animation
     * to skip
     * 
     * @param state the 0-based index of the animation to select
     */
    public void selectAnimation(int state) {
        animations.select(state);
    }

    /**
     * Ensure that an animation is selected for the Sprite
     * 
     * If the animation is already selected, does *not* reset it.
     * Can therefore be called as many times as is convenient, without causing
     * the animation to skip
     * 
     * 
     * @param state the 0-based index of the animation to select
     */
    public void ensureAnimationSelected(int state) {
        animations.ensureSelected(state);
    }

    /**
     * Advance the animation by one frame
     */
    public void advance() {
        animations.advance();
    }

    public void moveTo(Coordinate c) {
        position = c;
    }

    public void moveBy(Coordinate delta) {
        position = position.plus(delta);
    }

    public void paint(Graphics g) {
        animations.paint(g, position);
    }

    public void setMouseListener(MouseControllable mouseControl) {
        this.mouseControl = mouseControl;
    }
      
    public boolean contains(Coordinate c) {
        return c.southEastOf(position) &&
               c.northWestOf(bottomRight());
    }

    @Override
    public void mouseEntered() {
        if (mouseControl == null) {
            return;
        }
        mouseControl.mouseEntered();
    }

    @Override
    public void mouseExited() {
        if (mouseControl == null) {
            return;
        }
        mouseControl.mouseExited();
    }

    @Override
    public void leftClicked() {
        if (mouseControl == null) {
            return;
        }
        mouseControl.leftClicked();
    }

    @Override
    public void rightClicked() {
        if (mouseControl == null) {
            return;
        }
        mouseControl.rightClicked();
    }
    
    private Coordinate bottomRight() {
        return position.plus(animations.bottomRight());
    }
    
}
