package eel.seprphase4.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase4.Utilities.Mass;
import eel.seprphase4.Utilities.Percentage;
import static eel.seprphase4.Utilities.Units.*;

/**
 *
 * @author Marius
 */
public class Pump extends FailableComponent {

    @JsonProperty
    private Port inputPort;
    @JsonProperty
    private Port outputPort;
    @JsonProperty
    private Mass capacity = kilograms(6);
    @JsonProperty
    private boolean on = true;

    private Pump() {
        inputPort = null;
        outputPort = null;
    }

    public Pump(Port input, Port output) {
        inputPort = input;
        outputPort = output;
    }

    public void step() {
        if (hasFailed()) {
            outputPort.mass = kilograms(0);
            return;
        }
        if (on) {
            if (inputPort.mass.inKilograms() > capacity.inKilograms()) {
                outputPort.mass = capacity;
                inputPort.mass = inputPort.mass.minus(capacity);
            } else {
                outputPort.mass = inputPort.mass;
                inputPort.mass = kilograms(0);
            }
            outputPort.temperature = inputPort.temperature;
            stepWear();
        } else {
            outputPort.mass = kilograms(0);
        }
    }

    @Override
    public Percentage calculateWearDelta() {
        return new Percentage(0.05);
    }

    public void setOnState(boolean isOn) {
        this.on = isOn;
    }

    public void setCapacity(Mass newCapacity) {
        capacity = newCapacity;
    }

    public boolean isOn() {
        return on;
    }

    public Port inputPort() {
        return inputPort;
    }

    public Port outputPort() {
        return outputPort;
    }
}
