package cichlid.seprphase3.GUIInterface;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImageUtils {
    
    public static BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("Error loading image resources> " + filePath + "  :  " + e.getMessage());
        }

        return null;
    }
    
    /**
     * Scales the image by a specified amount.
     * @param unscaledImage     The image to scale
     * @param percent           The percentage by which to scale it (1.0 is 100%).
     * @return  A bufferedimage representing the scaled image.
     */
    public static BufferedImage scaleImage(BufferedImage unscaledImage, float percent) {
        Image scaledImage = unscaledImage.getScaledInstance( (int)(unscaledImage.getWidth() * percent), -1, Image.SCALE_SMOOTH);
        BufferedImage scaledBufferedImage = new BufferedImage(scaledImage.getWidth(null), scaledImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        scaledBufferedImage.getGraphics().drawImage(scaledImage, 0, 0, null);
        return scaledBufferedImage;
    }
}
