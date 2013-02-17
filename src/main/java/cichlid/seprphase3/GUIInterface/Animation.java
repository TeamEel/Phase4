package cichlid.seprphase3.GUIInterface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Animation {
    private BufferedImage[] images;
    private int currentFrame;
    
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(final File dir, final String name) {
            String extension = ".png";
            if(name.endsWith(extension)) {
                return true;
            } else {
                return false;
            }
        }
    };
    
    public Animation(String filePath, float scaling) {
        File imgDir = new File("images/" + filePath);
        
        try {
            images = new BufferedImage[imgDir.listFiles().length];
            int i = 0;
            for(File f : imgDir.listFiles(IMAGE_FILTER)) {
                images[i] = ImageUtils.loadImage(f.getCanonicalPath());
                images[i] = ImageUtils.scaleImage(images[i], scaling);
            }
        } catch (IOException e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
    }
    
    public BufferedImage stepImage() {
        currentFrame++;
        return images[currentFrame];
    }
    
    public BufferedImage staticImage() {
        return images[0];
    }
}
