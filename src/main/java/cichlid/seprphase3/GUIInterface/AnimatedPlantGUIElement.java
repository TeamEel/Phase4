package cichlid.seprphase3.GUIInterface;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

/**
 * This class represents something which can be displayed on the GUI which can be animated. It holds three animations;
 * On, TurningOn and TurningOff.
 *
 * @author jonny
 */
public class AnimatedPlantGUIElement extends PlantGUIElement {

    // These are the three animations which the GUI element can display.
    private Animation on;
    private Animation turningon;
    private Animation turningoff;
    // The animation that is currently displaying.
    private PlantAnimationType currentAnimation = PlantAnimationType.OFF;

    /**
     * Creates an animatedGUIElement without a transformation.
     *
     * @param _loop          Whether the element should loop or not.
     * @param onPath         The path of the on animation.
     * @param turningOnPath  The path of the turningOn animation.
     * @param turningOffPath The path of the turningOff animation.
     * @param x              The X location of the element on the screen.
     * @param y              The Y location of the element on the screen.
     * @param scaling        The scaling of the element.
     */
    public AnimatedPlantGUIElement(Boolean _loop, String onPath, String turningOnPath, String turningOffPath, int x,
                                   int y, float scaling) {
        // Create the three animations.
        on = new Animation(onPath, scaling, _loop);
        turningon = new Animation(turningOnPath, scaling, false);
        turningoff = new Animation(turningOffPath, scaling, false);

        // Create a static image just in case it is needed. Take this from the first frame of the ON animation.
        image = on.staticImage();

        // Automatically calculate the rectangle on the screen that the image will occupy.
        location = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    /**
     * Creates a new AnimatedPlantGUIElement while applying a transform to all of the frames.
     *
     * @param transform The transform to apply.
     */
    public AnimatedPlantGUIElement(Boolean _loop, String onPath, String turningOnPath, String turningOffPath, int x,
                                   int y, float scaling, AffineTransformOp transform) {
        // Create the three animations.
        on = new Animation(onPath, scaling, transform, _loop);
        turningon = new Animation(turningOnPath, scaling, transform, false);
        turningoff = new Animation(turningOffPath, scaling, transform, false);

        // Create a static image just in case it is needed. Take this from the first frame of the ON animation.
        this.image = on.staticImage();

        // Automatically calculate the rectangle on the screen that the image will occupy.
        location = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    /**
     * Get the current animation that is playing.
     *
     * @return
     */
    public PlantAnimationType getCurrentAnimation() {
        return currentAnimation;
    }

    /**
     * Set the animation to play.
     *
     * @param aniType The animation type to play.
     */
    public void setAnimation(PlantAnimationType aniType) {
        // Reset the current animation to frame 0 so it is ready to be replayed.
        switch (currentAnimation) {
            case ON:
                on.reset();
                break;
            case TURNINGON:
                turningon.reset();
                break;
            case TURNINGOFF:
                turningoff.reset();
                break;
        }

        // Change the animation type to the new animation type.
        currentAnimation = aniType;
    }

    /**
     * Get the next image in the current animation.
     *
     * @return The next image in the current animation.
     */
    public BufferedImage stepImage() {

        switch (currentAnimation) {
            case ON:
                return on.stepImage();
            case OFF:
                return on.staticImage();
            case TURNINGON:
                return turningon.stepImage();
            case TURNINGOFF:
                return turningoff.stepImage();
        }

        // Edge case if no animation is set, return the static image.
        return image;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(stepImage(), x(), y(), null);
    }
    
    @Override
    public void draw(Graphics2D g, boolean failed) {
        if (!failed) {
            // If it has not failed, show the next frame of the animation.
            g.drawImage(stepImage(), x(), y(), null);
        } else {
            // If it has failed, stop the animation and apply the red tint.
            setAnimation(PlantAnimationType.OFF);
            BufferedImageOp tintFilter = ImageUtils.createTintOp((short)1.5, (short).5, (short).5);
            BufferedImage tintedImage = tintFilter.filter(getImage(), null);
            g.drawImage(tintedImage, x(), y(), null);
        }
    }
}
