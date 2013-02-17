package cichlid.seprphase3.GUIInterface;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class AnimatedPlantGUIElement extends PlantGUIElement {
    public Animation working;
    public Animation starting;
    public Animation stopping;
    public Animation broken;
    public Animation meltdown;
    
    PlantAnimationType currentAnimation = PlantAnimationType.WORKING;
    
    public AnimatedPlantGUIElement(String workingPath, String startingPath, String stoppingPath, String brokenPath, String meltdownPath, int x, int y, float scaling, int offsetx, int offsety) {
        super(new BufferedImage(0, 0, 0), x, y, scaling, offsetx, offsety);
        working = new Animation(workingPath, scaling);
        starting = new Animation(startingPath, scaling);
        stopping = new Animation(stoppingPath, scaling);
        broken = new Animation(brokenPath, scaling);
        meltdown = new Animation(meltdownPath, scaling);
        image = working.staticImage();
        location = new Rectangle(x+offsetx, y+offsety, image.getWidth(), image.getHeight());
    }
    
    public void setAnimation(PlantAnimationType aniType) {
        currentAnimation = aniType;
    }
    
    public BufferedImage stepImage() {
        switch(currentAnimation) {
            case WORKING:
                return working.stepImage();
            case STARTING:
                return starting.stepImage();
            case STOPPING:
                return stopping.stepImage();
            case BROKEN:
                return broken.stepImage();
            case MELTDOWN:
                return meltdown.stepImage();
        }

        return image;
    }
}
