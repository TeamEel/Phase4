package cichlid.seprphase3.Simulator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Enum to represent a software failure in the plant.
 */
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum SoftwareFailure {
    pumpStateChange, valveStateChange, rodStateChange,
    condenserPressureRead, condenserTemperatureRead, condenserWaterRead,
    reactorPressureRead, reactorTemperatureRead, reactorWaterRead, controlRodRead,
    None;
//    pumpStateChange("pumpstate"),
//    valveStateChange("valvestate"),
//    rodStateChange("rodstate"),
//    condenserPressureRead("condenserpressure"),
//    condenserTemperatureRead("condensertemperature"),
//    condenserWaterRead("condenserwater"),
//    reactorPressureRead("reactorpressure"),
//    reactorTemperatureRead("reactortemperature"),
//    reactorWaterRead("reactorwater"),
//    None("none");

//    private final String value;
//
//    private SoftwareFailure(String value) {
//        this.value = value;
//    }

    // Used to cache the array so it is not created every tick!
    private static final List<SoftwareFailure> failureList = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int size = failureList.size();
    private static final Random random = new Random();

    public static SoftwareFailure pickRandom() {
        return failureList.get(random.nextInt(size));
    }
}
