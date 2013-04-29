package eel.seprphase4.drawing.builders;

import eel.seprphase4.drawing.animation.Animation;
import eel.seprphase4.drawing.animation.AnimationSet;
import java.io.IOException;

/**
 * Helper methods to create Expression Builders for AnimationSets
 * @author drm
 */
public class BuildAnimationSet {

    public static AnimationSetBuilder buildAnimationSet() {
        return new AnimationSetBuilder();
    }

    public static AnimationSet singleImage(String resourcePath) throws IOException {
        return new AnimationSetBuilder().animation(BuildAnimation.singleFrame(resourcePath)).done();
    }

    public static AnimationSet singleAnimation(Animation animation) {
        return new AnimationSetBuilder().animation(animation).done();
    }
}
