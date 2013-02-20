package cichlid.seprphase3.GUIInterface;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.ShortLookupTable;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImageUtils {
    
    public static BufferedImage loadImage(String filePath) {
        try {
            filePath = "images/" + filePath;
            System.out.println(filePath);
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("Error loading image resources> " + filePath + "  :  " + e.getMessage());
        }

        return null;
    }
    
    public static BufferedImage loadImageByPath(String filePath) {
        try {
            System.out.println(filePath);
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
        Image scaledImage = unscaledImage.getScaledInstance( (int)(unscaledImage.getWidth() * percent), -1, Image.SCALE_DEFAULT);
        BufferedImage scaledBufferedImage = new BufferedImage(scaledImage.getWidth(null), scaledImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        scaledBufferedImage.getGraphics().drawImage(scaledImage, 0, 0, null);
        return scaledBufferedImage;
    }
    
    public static LookupOp createTintOp(short R1, short G1, short B1) {
        short[] alpha = new short[256];
        short[] red = new short[256];
        short[] green = new short[256];
        short[] blue = new short[256];

        for (short i = 0; i < 256; i++) {
            alpha[i] = i;
            red[i] = (short)((1 + i*R1)/2);
            green[i] = (short)((1 + i*G1)/2);
            blue[i] = (short)((1 + i*B1)/2);
        }

        short[][] data = new short[][] {
                red, green, blue, alpha
        };

        LookupTable lookupTable = new ShortLookupTable(0, data);
        return new LookupOp(lookupTable, null);
    }
}
