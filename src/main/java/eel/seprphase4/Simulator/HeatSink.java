package eel.seprphase4.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase4.Utilities.Density;

/**
 *
 * @author James
 */
public class HeatSink {

    @JsonProperty
    private Port outputPort;

    public HeatSink() {
        outputPort = new Port();
        outputPort.temperature = eel.seprphase4.Utilities.Units.kelvin(308.15);
        outputPort.density = Density.ofLiquidWater();
        outputPort.mass = eel.seprphase4.Utilities.Units.kilograms(10);
    }

    public Port outputPort() {
        return outputPort;
    }
}
