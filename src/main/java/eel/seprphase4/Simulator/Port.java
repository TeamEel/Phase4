package eel.seprphase4.Simulator;

import eel.seprphase4.Utilities.Pressure;
import eel.seprphase4.Utilities.Density;
import eel.seprphase4.Utilities.Mass;
import eel.seprphase4.Utilities.Temperature;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import static eel.seprphase4.Utilities.Units.*;

/**
 *
 * @author David
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Port {

    /**
     *
     */
    public Density density = Density.ofLiquidWater();
    /**
     *
     */
    public Pressure pressure = pascals(101325);
    /**
     *
     */
    public Temperature temperature = kelvin(300);
    /**
     *
     */
    public Mass mass = kilograms(0);
    /*
     * 
     */
    public Mass flow = kilograms(0);
}
