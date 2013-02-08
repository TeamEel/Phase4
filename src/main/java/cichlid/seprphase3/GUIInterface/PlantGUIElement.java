package cichlid.seprphase3.GUIInterface;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class PlantGUIElement {
    public Rectangle location;
    public BufferedImage image;
    
    public PlantGUIElement(BufferedImage _image, int x, int y, float scaling) {
        image = scaleImage(_image, scaling);
        location = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }
    
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
