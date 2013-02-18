package cichlid.seprphase3.GUIInterface;

import java.awt.Rectangle;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class AnimatedPlantGUIElement extends PlantGUIElement {
    
    private Animation on;
    private Animation turningon;
    private Animation turningoff;
    
    private Boolean staticWhileActivated;
    
    private PlantAnimationType currentAnimation = PlantAnimationType.OFF;
    
    public AnimatedPlantGUIElement(Boolean _staticWhileActivated, String onPath, String turningOnPath, String turningOffPath, int x, int y, float scaling, int offsetx, int offsety) {
        on = new Animation(onPath, scaling, this);
        turningon = new Animation(turningOnPath, scaling, this);
        turningoff = new Animation(turningOffPath, scaling, this);
        image = on.staticImage();
        location = new Rectangle(x+offsetx, y+offsety, image.getWidth(), image.getHeight());
        staticWhileActivated = _staticWhileActivated;
    }
    
    public AnimatedPlantGUIElement(Boolean _staticWhileActivated, String onPath, String turningOnPath, String turningOffPath, int x, int y, float scaling, int offsetx, int offsety, AffineTransformOp transform) {
        on = new Animation(onPath, scaling, transform, this);
        turningon = new Animation(turningOnPath, scaling, transform, this);
        turningoff = new Animation(turningOffPath, scaling, transform, this);
        image = on.staticImage();
        location = new Rectangle(x+offsetx, y+offsety, image.getWidth(), image.getHeight());
        staticWhileActivated = _staticWhileActivated;
    }
    
    public PlantAnimationType getCurrentAnimation() {
        return currentAnimation;
    }
    
    public void setAnimation(PlantAnimationType aniType) {
        switch(currentAnimation) {
            case ON:
                on.reset(); break;
            case TURNINGON:
                turningon.reset(); break;
            case TURNINGOFF:
                turningoff.reset(); break;
            case BROKEN:
                break;
        }
        
        currentAnimation = aniType;
    }
    
    public BufferedImage stepImage() {
        switch(currentAnimation) {
            case ON:
                if (staticWhileActivated) {
                    return turningoff.staticImage();
                }
                return on.stepImage();
            case OFF:
                if (staticWhileActivated) {
                    return turningon.staticImage();
                }
                return on.staticImage();
            case TURNINGON:
                return turningon.stepImage();
            case TURNINGOFF:
                return turningoff.stepImage();
            case BROKEN:
                // TODO: sine filter for red image.
                break;
        }

        return image;
    }
}
