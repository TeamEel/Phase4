package eel.seprphase4.GUIInterface;

import eel.seprphase4.Simulator.Connection;
import eel.seprphase4.Simulator.PlantStatus;
import eel.seprphase4.Simulator.SoftwareFailure;

/**
 *
 * @author drm511
 */
public class ValveControl extends AnimatedPlantGUIElement {

    private PlantStatus plantStatus;
    private String id;

    public ValveControl(PlantStatus plantStatus,
                        String id,
                        int x, int y, float scaling) {
        super(false,
              "animations/closevalve",
              "animations/closevalve",
              "animations/openvalve",
              x, y, scaling);
        this.plantStatus = plantStatus;
        this.id = id;
    }

    @Override
    public void handleLeftClick() {
        // If a valve was clicked, change its status and animate it changing.
        if (!(plantStatus.getSoftwareFailure() == SoftwareFailure.valveStateChange)) {
            boolean state = component().getOpen();

            if (state) {
                component().setOpen(false);
                setAnimation(PlantAnimationType.TURNINGON);
            } else {
                component().setOpen(true);
                setAnimation(PlantAnimationType.TURNINGOFF);
            }
        }
    }

    private Connection component() {
        return plantStatus.connectionList().get(id);
    }
}
