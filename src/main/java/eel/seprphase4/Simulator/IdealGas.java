package eel.seprphase4.Simulator;

import eel.seprphase4.Utilities.Pressure;
import eel.seprphase4.Utilities.Mass;
import eel.seprphase4.Utilities.Temperature;
import eel.seprphase4.Utilities.Volume;
import static eel.seprphase4.Simulator.PhysicalConstants.*;
import static eel.seprphase4.Utilities.Units.*;

/**
 * Ideal Gas Equation
 *
 * Rearrangements of the Ideal Gas Equation to find any one variable given the others.
 *
 * @author David
 */
public class IdealGas {

    /**
     *
     * @param volume
     * @param mass
     * @param temperature
     *
     * @return
     */
    public static Pressure pressure(Volume volume, Mass mass, Temperature temperature) {
        return pascals((mass.inMolesOfWater() * gasConstant * temperature.inKelvin()) / volume.inCubicMetres());
    }

    /**
     *
     * @param volume
     * @param mass
     * @param pressure
     *
     * @return
     */
    public static Temperature temperature(Volume volume, Mass mass, Pressure pressure) {
        return kelvin((pressure.inPascals() * volume.inCubicMetres()) / (mass.inKilograms() * gasConstant));
    }

    /**
     *
     * @param pressure
     * @param mass
     * @param temperature
     *
     * @return
     */
    public static Volume volume(Pressure pressure, Mass mass, Temperature temperature) {
        return cubicMetres(mass.inMolesOfWater() * gasConstant * temperature.inKelvin());
    }

    /**
     *
     * @param pressure
     * @param volume
     * @param temperature
     *
     * @return
     */
    public static Mass mass(Pressure pressure, Volume volume, Temperature temperature) {
        return molesOfWater((pressure.inPascals() * volume.inCubicMetres()) / (gasConstant * temperature.inKelvin()));
    }
}
