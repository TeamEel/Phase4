package cichlid.seprphase3.Simulator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Enum to represent a software failure in the plant.
 */
public enum SoftwareFailure {

    pumpStateChange, valveStateChange, rodStateChange,
    condenserPressureRead, condenserTemperatureRead, condenserWaterRead,
    reactorPressureRead, reactorTemperatureRead, reactorWaterRead, controlRodRead,
    None;
    // Used to cache the array so it is not created every tick!
    private static final List<SoftwareFailure> failureList = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int size = failureList.size();
    private static final Random random = new Random();

    public static SoftwareFailure pickRandom() {
        return failureList.get(random.nextInt(size));
    }
}
