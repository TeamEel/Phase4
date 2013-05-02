package display.drawable;

import display.*;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author David
 */
public class Animation implements Drawable {

    private final Image[] frames;
    private final boolean loop;
    private final int msPerFrame;
    private int spareMs;
    private int currentFrame;

    public Animation(String[] images, boolean loop, int msPerFrame) {
        if (images.length < 1) {
            throw new IllegalArgumentException("Animation must consist of at least one frame");
        }
        this.frames = ResourceLoader.imageResourceArray(images);
        this.loop = loop;
        this.msPerFrame = msPerFrame;
        this.currentFrame = 0;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(frames[currentFrame], x, y, null);
    }

    @Override
    public void advance(int ms) {
        currentFrame += (ms + spareMs) / msPerFrame;
        spareMs = (ms + spareMs) % msPerFrame;
        if (currentFrame >= frames.length && !loop) {
            currentFrame = frames.length;
            return;
        }
        currentFrame %= frames.length;
    }

    @Override
    public HitBox hitBox(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
