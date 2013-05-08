package eel.seprphase4.Utilities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import static eel.seprphase4.Utilities.Units.*;

/**
 * Encapsulate a quantity representing a physical Mass
 *
 * @author David
 */
@JsonTypeName(value = "Mass")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
public class Mass {

    @JsonProperty
    private final double kilograms;

    /**
     * Gets the mass from moles of water
     * @param moles
     *
     * @return Mass from moles 
     */
    public static Mass fromMolesOfWater(double moles) {
        return new Mass(moles * 18 / 1000);
    }

    /**
     *
     */
    public Mass() {
        kilograms = 0;
    }

    /**
     * Creates a new mass object from numeric weight
     * @param kilograms
     */
    public Mass(double kilograms) {
        this.kilograms = kilograms;
    }

    /**
     * Gets the numeric value of the mass
     * @return kilograms
     */
    public double inKilograms() {
        return kilograms;
    }

    /**
     * 
     * @return mass in number of moles
     */
    public double inMolesOfWater() {
        return kilograms * 1000 / 18;
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public Mass plus(Mass other) {
        return new Mass(kilograms + other.kilograms);
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public Mass minus(Mass other) {
        return new Mass(kilograms - other.kilograms);
    }

    /**
     *
     * @param volume
     *
     * @return
     */
    public Density densityAt(Volume volume) {
        return new Density(kilograms / volume.inCubicMetres());
    }

    /**
     *
     * @param density
     *
     * @return
     */
    public Volume volumeAt(Density density) {
        if (density.equals(kilogramsPerCubicMetre(0))) {
            throw new IllegalArgumentException("Attempted to find the volume of a mass with density 0");
        }
        return new Volume(kilograms / density.inKilogramsPerCubicMetre());
    }

    @Override
    public String toString() {
        return Format.toThreeDecimalPlaces(kilograms) + " kg";
    }

    @Override
    public int hashCode() {
        return (int)kilograms;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mass other = (Mass)obj;
        if (Double.doubleToLongBits(this.kilograms) != Double.doubleToLongBits(other.kilograms)) {
            return false;
        }
        return true;
    }
}
