package display;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author David
 */
public class ResourceLoader {

    public static Image imageResource(String resourcePath) {
        try {
            URL resourceURL = ResourceLoader.class.getResource(resourcePath);
            if (resourceURL == null) {
                throw new FileNotFoundException(resourcePath);
            }
            return ImageIO.read(resourceURL);
        } catch (IOException ex) {
            throw new Error(ex);
        }
    }

    public static Image[] imageResourceArray(String[] resourcePaths) {
        Image[] images = new Image[resourcePaths.length];
        for (int i = 0; i < images.length; i++) {
            images[i] = imageResource(resourcePaths[i]);
        }
        return images;
    }
//    public static Animation animationResource(String resourcePath) {
//        try {
//            URL resourceURL = ResourceLoader.class.getResource(resourcePath);
//            if (resourceURL == null) {
//                throw new FileNotFoundException(resourcePath);
//            }
//            ObjectMapper objectMapper = new ObjectMapper();
//            AnimationSpec spec = objectMapper.readValue(resourceURL, AnimationSpec.class);
//                                                                               
//            return Animation.fromSpec(spec);
//        } catch (IOException ex) {
//            throw new Error(ex);
//        }
//    }
}
