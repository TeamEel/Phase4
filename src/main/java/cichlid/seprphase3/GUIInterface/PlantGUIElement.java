package cichlid.seprphase3.GUIInterface;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class PlantGUIElement {
    
    // This rectangle will  be used to detect mouse collision with PlantGUIComponents.
    public Rectangle location;
    // This is the image that will be drawn to the screen while the PlantGUIComponent is static.
    public Animation working;
    public Animation starting;
    public Animation stopping;
    public Animation broken;
    public Animation meltdown;
    
    public PlantGUIElement(BufferedImage _image, int x, int y, float scaling) {
        image = scaleImage(_image, scaling);
        location = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }
    
    public PlantGUIElement(BufferedImage _image, int x, int y, float scaling, int offsetx, int offsety) {
        image = scaleImage(_image, scaling);
        location = new Rectangle(x+offsetx, y+offsety, image.getWidth(), image.getHeight());
    }
    
    /**
     * Scales the image by a specified amount.
     * @param unscaledImage     The image to scale
     * @param percent           The percentage by which to scale it (1.0 is 100%).
     * @return  A bufferedimage representing the scaled image.
     */
    private BufferedImage scaleImage(BufferedImage unscaledImage, float percent) {
        Image scaledImage = unscaledImage.getScaledInstance( (int)(unscaledImage.getWidth() * percent), -1, Image.SCALE_SMOOTH);
        BufferedImage scaledBufferedImage = new BufferedImage(scaledImage.getWidth(null), scaledImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        scaledBufferedImage.getGraphics().drawImage(scaledImage, 0, 0, null);
        return scaledBufferedImage;
    }
    
    public int x() {
        return location.x;
    }
    
    public int y() {
        return location.y;
    }
}
