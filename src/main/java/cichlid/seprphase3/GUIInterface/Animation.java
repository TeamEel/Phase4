package cichlid.seprphase3.GUIInterface;

import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * This class allows us to create an animation. It holds a number of frames, the current frame it is on as well as if
 * the animation should loop.
 *
 * @author jonny
 */
public class Animation {

    // The array of frames.
    private BufferedImage[] images;
    private File[] imageFiles;
    // The current frame which the animation is on.
    private int currentFrame;
    // Whether the animation should loop or not.
    private boolean loop;
    // This is used to make .listfiles() only return a list of .png's.
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(final File dir, final String name) {
            // 'accept only files that end with .png'
            String extension = ".png";
            if (name.endsWith(extension)) {
                return true;
            } else {
                return false;
            }
        }
    };

    private void getFileList(String filePath) {
        // Set all given paths to be relative to images/
        File imgDir = new File("images/" + filePath);

        if (imgDir.exists()) {

            // Instantiate the array to be the number of images in the directory.
            images = new BufferedImage[imgDir.listFiles(IMAGE_FILTER).length];

            // Get a list of the files in the directory.
            imageFiles = imgDir.listFiles(IMAGE_FILTER);
            // Sort it by name so the images are loaded in the correct order.
            Arrays.sort(imageFiles);
        }
    }

    /**
     * Create an animation based on a file path.
     *
     * @param filePath   The file path of the images.
     * @param shouldLoop Whether it should loop or not.
     */
    public Animation(String filePath, boolean shouldLoop) {

        // Set if the animation should loop.
        loop = shouldLoop;

        // Get the list of files in the provided path.
        getFileList(filePath);

        try {
            // Load each image in the list of files.
            for (int i = 0; i < imageFiles.length; i++) {
                images[i] = ImageUtils.loadImageByPath(imageFiles[i].getPath());
            }
        } catch (Exception e) {
            System.out.println("Failed to open file:" + filePath);
        }
    }

    /**
     * Create an animation with a scaling.
     *
     * @param scaling The scaling to apply to each of the images.
     */
    public Animation(String filePath, float scaling, boolean shouldLoop) {

        // Initialise based on the most basic constructor (load the images).
        this(filePath, shouldLoop);

        // Apply the scale to each of the loaded images.
        for (int i = 0; i < imageFiles.length; i++) {
            images[i] = ImageUtils.scaleImage(images[i], scaling);
        }
    }

    /**
     * Create an animation with scaling while applying a transform to all of the images.
     *
     * @param scaling   The scaling to apply.
     * @param transform The transform to apply.
     */
    public Animation(String filePath, float scaling, AffineTransformOp transform, boolean shouldLoop) {

        // Initialise based on the most basic constructor (load the images).
        this(filePath, shouldLoop);

        // Apply the scale and transformation to each of the loaded images.
        for (int i = 0; i < imageFiles.length; i++) {
            images[i] = ImageUtils.scaleImage(images[i], scaling);
            images[i] = transform.filter(images[i], null);
        }
    }

    /**
     * This gets the next frame that the animation should display.
     *
     * @return The image.
     */
    public BufferedImage stepImage() {

        // Increment the current frame.to the next frame.
        currentFrame++;

        // If the current frame is at the end of the animation, set the current frame to 0.
        if (isAtEnd()) {
            if (loop) {
                reset();
            } else {
                currentFrame = images.length - 1;
            }
        }

        // Return the 'current' frame. Must use -- since the currentFrame was previously
        // incremented but we still want the last frame.
        return images[currentFrame];
    }

    /**
     * This returns the first frame of the animation for use as a static image.
     *
     * @return The first frame of the animation.
     */
    public BufferedImage staticImage() {
        return images[0];
    }

    /**
     * This checks if the animation is at the end of its frames.
     *
     * @return
     */
    public Boolean isAtEnd() {
        return currentFrame >= images.length - 1;
    }

    /**
     * Resets the animation; sets the currentFrame back to 0.
     */
    public void reset() {
        currentFrame = 0;
    }
}
