package cichlid.seprphase3.GUIInterface;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class PlantGUIElement {
    
    // This rectangle will  be used to detect mouse collision with PlantGUIComponents.
    public Rectangle location;
    
    // This is the image that will be drawn to the screen while the PlantGUIComponent is static.
    public BufferedImage image;
    
    private PlantAnimationType currentAnimation = PlantAnimationType.ON;
    
    public PlantGUIElement() {
        
    }
    
    public PlantGUIElement(BufferedImage _image, int x, int y, float scaling, int offsetx, int offsety) {
        image = ImageUtils.scaleImage(_image, scaling);
        location = new Rectangle(x+offsetx, y+offsety, image.getWidth(), image.getHeight());
    }
    
    public int x() {
        return location.x;
    }
    
    public int y() {
        return location.y;
    }
    
    public void setY(int offset) {
        location.y += offset;
    }
    
    public BufferedImage getImage() {
        return image;
    }
}
