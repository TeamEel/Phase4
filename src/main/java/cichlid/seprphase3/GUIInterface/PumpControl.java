package cichlid.seprphase3.GUIInterface;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author david
 */
public class PumpControl extends PlantGUIElement {

    PlantGUIElement body;
    AnimatedPlantGUIElement rotors;
    boolean on;

    public PumpControl(int x, int y, float scale) {
        body = new PlantGUIElement(ImageUtils.loadImage("pump.png"), x, y, scale);
        rotors = new AnimatedPlantGUIElement(true,
                                             "animations/workingpump",
                                             "animations/startpump",
                                             "animations/stoppump",
                                             x + 26, y + 32, scale);
        rotors.setAnimation(PlantAnimationType.ON);
    }

    @Override
    public void draw(Graphics2D g) {
        updateAnimations();
        body.draw(g);
        rotors.draw(g);
    }

    @Override
    public void draw(Graphics2D g, boolean failed) {
        updateAnimations();
        body.draw(g, failed);
        rotors.draw(g, failed);
    }

    @Override
    public boolean clicked(MouseEvent click) {
        return body.clicked(click) ||
               rotors.clicked(click);
    }

    public void turnOn() {
        rotors.setAnimation(PlantAnimationType.ON);
    }

    public void turnOff() {
        rotors.setAnimation(PlantAnimationType.TURNINGOFF);
    }

    private void updateAnimations() {
        if (on && rotors.animationFinished()) {
            rotors.setAnimation(PlantAnimationType.ON);
        }
    }
}
