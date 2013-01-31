package cichlid.seprphase3.Simulator;

/**
 * Enum to represent a software failure in the plant.
 */
public enum SoftwareFailure {
    pumpStateChange, valveStateChange, rodStateChange,
    condenserPressureRead, condenserTemperatureRead, condenserWaterRead,
    reactorPressureRead, reactorTemperatureRead, reactorWaterRead,
    None
}
