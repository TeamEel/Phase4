package cichlid.seprphase3.Simulator;

import cichlid.seprphase3.Utilities.Pressure;
import cichlid.seprphase3.Utilities.Density;
import cichlid.seprphase3.Utilities.Mass;
import cichlid.seprphase3.Utilities.Temperature;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import static cichlid.seprphase3.Utilities.Units.*;

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
