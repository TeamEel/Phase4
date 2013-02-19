package cichlid.seprphase3.GUIInterface;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class PlantGUIElement {
    
    // This rectangle will  be used to detect mouse collision with PlantGUIComponents.
    public Rectangle location;
    
    // This animation will be used when the plant is melting down.
    private Animation meltdown;
    private int meltdownFrame = 0;
    
    // This is the image that will be drawn to the screen while the PlantGUIComponent is static.
    public BufferedImage image;
    
    private PlantAnimationType currentAnimation = PlantAnimationType.ON;
    
    public PlantGUIElement() {
        
    }
    
    public PlantGUIElement(BufferedImage _image, String meldownPath, int x, int y, float scaling, int offsetx, int offsety) {
        image = ImageUtils.scaleImage(_image, scaling);
        meltdown = new Animation(meldownPath, scaling);
        location = new Rectangle(x+offsetx, y+offsety, image.getWidth(), image.getHeight());
    }
    
    public int x() {
        return location.x;
    }
    
    public int y() {
        return location.y;
    }
    
    public BufferedImage getImage() {
        if(currentAnimation != PlantAnimationType.MELTDOWN) {
            return image;
        } else {
            return meltdown.stepImage();
        }
    }
    
    public void meltdown() {
        currentAnimation = PlantAnimationType.MELTDOWN;
    }
}
