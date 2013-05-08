package eel.seprphase4.gui.drawable;

import eel.seprphase4.gui.HitBox;
import eel.seprphase4.gui.ResourceLoader;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author David
 */
public class RangeDrawable implements Drawable {

    private final Image[] frames;
    private final double min, max;
    private int currentFrame;
    
    public RangeDrawable(String[] frameResources, double min, double max) {
        this(ResourceLoader.imageResourceArray(frameResources), min, max);
    }
    
    public RangeDrawable(Image[] frames, double min, double max) {
        if (frames.length <= 0) {
            throw new IllegalArgumentException("<frames> must contain at least one frame");
        }
        if (max - min == 0) {
            throw new IllegalArgumentException("<min> and <max> must be distinguishable");
        }
        this.frames = frames;
        this.min = min;
        this.max = max;
        this.currentFrame = 0;
    }
    
    public void setLevel(double value) {
        double choice = ((value-min) * frames.length) / (max - min);
        if (choice > frames.length - 1) {
            choice = frames.length - 1;
        }
        if (choice < 0) {
            choice = 0;
        }
        currentFrame = (int)Math.round(choice);
    }
    
    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(frames[currentFrame], x, y, null);
    }

    @Override
    public void advance(int ms) {
        // ignore
    }

    @Override
    public void reset() {
        // ignore
    }
    
    @Override
    public HitBox hitBox(int x, int y) {
        return HitBox.fromImage(frames[0], x, y);
    }

}
