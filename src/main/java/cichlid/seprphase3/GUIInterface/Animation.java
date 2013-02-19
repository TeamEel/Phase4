package cichlid.seprphase3.GUIInterface;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class Animation {
    
    private BufferedImage[] images;
    private int currentFrame;
    private AnimatedPlantGUIElement elementParent;
    
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
        
        if(imgDir.exists()) {
        
            try {
                images = new BufferedImage[imgDir.listFiles().length];

                File[] imageFiles = imgDir.listFiles(IMAGE_FILTER);
                Arrays.sort(imageFiles);
                
                for(int i = 0; i < imageFiles.length; i++) {
                    images[i] = ImageUtils.loadImage(imageFiles[i].getAbsolutePath());
                    images[i] = ImageUtils.scaleImage(images[i], scaling);
                }
            } catch (Exception e) {
                System.out.println("Failed to load image: " + e.getMessage());
            }
        
        }
    }
    
    public Animation(String filePath, float scaling, AnimatedPlantGUIElement parent) {
        this(filePath, scaling);
        
        elementParent = parent;
    }
    
    public Animation(String filePath, float scaling, AffineTransformOp transform, AnimatedPlantGUIElement parent) {
        File imgDir = new File("images/" + filePath);
        
        elementParent = parent;
        
        if(imgDir.exists()) {
        
            try {
                images = new BufferedImage[imgDir.listFiles().length];

                File[] imageFiles = imgDir.listFiles(IMAGE_FILTER);
                Arrays.sort(imageFiles);
                
                for(int i = 0; i < imageFiles.length; i++) {
                    images[i] = ImageUtils.loadImage(imageFiles[i].getAbsolutePath());
                    images[i] = ImageUtils.scaleImage(images[i], scaling);
                    images[i] = transform.filter(images[i], null);
                }
            } catch (Exception e) {
                System.out.println("Failed to load image: " + e.getMessage());
            }
        
        }
    }
    
    public BufferedImage stepImage() {
        
        currentFrame++;
        
        if (currentFrame >= images.length) {
            switch(elementParent.getCurrentAnimation()) {
                case TURNINGON:
                    elementParent.setAnimation(PlantAnimationType.ON);
                    break;
                case TURNINGOFF:
                    elementParent.setAnimation(PlantAnimationType.OFF);
                    break;
                case ON:
                    reset(); break;
                case OFF:
                    reset(); break;
                case MELTDOWN:
                    break;
            }
        }
        
        return images[currentFrame];
    }
    
    public BufferedImage staticImage() {
        return images[0];
    }
    
    public void reset() {
        currentFrame = 0;
    }
}
