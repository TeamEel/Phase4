package cichlid.seprphase3.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import cichlid.seprphase3.Utilities.Density;

/**
 *
 * @author James
 */
public class HeatSink {

    @JsonProperty
    private Port outputPort;

    public HeatSink() {
        outputPort = new Port();
        outputPort.temperature = cichlid.seprphase3.Utilities.Units.kelvin(308.15);
        outputPort.density = Density.ofLiquidWater();
        outputPort.mass = cichlid.seprphase3.Utilities.Units.kilograms(10);
    }

    public Port outputPort() {
        return outputPort;
    }
}
