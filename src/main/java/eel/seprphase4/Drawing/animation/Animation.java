package eel.seprphase4.drawing.animation;

import eel.seprphase4.drawing.Coordinate;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Encapsulate an animation (a sequence of image frames).
 * 
 * @author drm
 */
public abstract class Animation {

    private Image[] frames;
    protected int currentFrame;

    public Animation(Image[] images) {
        // TODO: validate that there is at least one frame
        // TODO: validate that all frames are the same size
        frames = images;
        currentFrame = 0;
    }

    public void paint(Graphics g, Coordinate c) {
        g.drawImage(frames[currentFrame], c.x, c.y, null);
    }

    /**
     * Advance the animation by one frame.
     * 
     * Different concrete subclasses override this method to provide
     * different animation types, either one-shot or looping.
     */
    public abstract void advance();

    /**
     * Reset the animation back to the first frame
     */
    public void reset() {
        currentFrame = 0;
    }

    public Coordinate bottomRight() {
        return new Coordinate(frames[0].getWidth(null),
                              frames[0].getHeight(null));
    }
    
    /**
     * Check whether the animation is on its last frame
     * 
     * @return whether or not the current frame is the last of this animation
     */
    protected boolean lastFrame() {
        return currentFrame == frames.length - 1;
    }
}
