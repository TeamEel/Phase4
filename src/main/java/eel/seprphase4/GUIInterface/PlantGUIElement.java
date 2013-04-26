package eel.seprphase4.GUIInterface;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import javax.swing.SwingUtilities;

/**
 * PlantGUIElement represents anything which can be drawn to a part of the Plant's GUI. It holds an image and a
 * location.
 */
public class PlantGUIElement {

    // This rectangle will  be used to detect mouse collision with PlantGUIComponents.
    protected Rectangle location;
    // This is the image that will be drawn to the screen while the PlantGUIComponent is static.
    // Protected means it is accessible to AnimatedPlantGUIElement, since it inherits from PlantGUIElement.
    protected BufferedImage image;

    /**
     * This constructor must be called by AnimatedPlantGUIElement.
     */
    public PlantGUIElement() {
    }

    /**
     * Create a PlantGUIElement from several parameters.
     *
     * @param _image  The image to use for displaying the Element.
     * @param x       The X position.
     * @param y       The Y position.
     * @param scaling The scaling to apply.
     */
    public PlantGUIElement(BufferedImage _image, int x, int y, float scaling) {
        // Scale the image first.
        image = ImageUtils.scaleImage(_image, scaling);

        // Automatically compute the rectangle on the screen that this image occuipes.
        location = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public int x() {
        return location.x;
    }

    public int y() {
        return location.y;
    }

    public void setX(int pos) {
        location.x = pos;
    }

    public void setY(int pos) {
        location.y = pos;
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean clicked(MouseEvent e) {
        return location.contains(e.getPoint());
    }

    public void handleClick(MouseEvent e) {
        if (clicked(e)) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                handleLeftClick();
            } else if (SwingUtilities.isRightMouseButton(e)) {
                handleRightClick();
            }
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(getImage(), x(), y(), null);
    }

    public void draw(Graphics2D g, boolean failed) {
        if (!failed) {
            g.drawImage(getImage(), x(), y(), null);
        } else {
            // If the element has failed, apply a tint which reddens the image completely.
            BufferedImageOp tintFilter = ImageUtils.createTintOp((short)2, (short).5, (short).5);
            BufferedImage tintedImage = tintFilter.filter(getImage(), null);
            g.drawImage(tintedImage, x(), y(), null);
        }
    }

    protected void handleLeftClick() {
        // do nothing
        // todo: make abstract?
    }

    protected void handleRightClick() {
        // do nothing
        // todo: make abstract?
    }
}
