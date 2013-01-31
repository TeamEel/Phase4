package cichlid.seprphase3.Simulator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import cichlid.seprphase3.Utilities.Mass;
import cichlid.seprphase3.Utilities.Percentage;
import static cichlid.seprphase3.Utilities.Units.*;

/**
 *
 * @author Marius
 */
public class Turbine extends FailableComponent {

    @JsonProperty
    private double outputPower;
    @JsonProperty
    private Port inputPort = new Port();
    @JsonProperty
    private Port outputPort = new Port();
    @JsonIgnore
    private static PlantController controller;
    @JsonProperty
    private Mass buildUp = kilograms(0);

    /**
     *
     */
    public Turbine() {
        super();
    }

    /**
     *
     */
    public void step() {
        //System.out.println("Turbine Input Water Mass " + inputPort.mass);
        //System.out.println("Turbine Output Water Mass 1 " + outputPort.mass);

        if (hasFailed()) {
            outputPower = 0;
            stepWear();
            buildUp = buildUp.plus(inputPort.mass);
            return;
        }

        outputPower = inputPort.mass.inKilograms() * 1000; //Requires conversion to grams
        outputPort.mass = inputPort.mass.plus(buildUp);
        outputPort.pressure = inputPort.pressure;
        outputPort.temperature = inputPort.temperature;
        outputPort.flow = inputPort.flow;
        buildUp = kilograms(0);
        stepWear();

        //System.out.println("Turbine Output Water Mass 2 " + outputPort.mass);
    }

    /**
     *
     * @return
     */
    public double outputPower() {
        return outputPower;
    }

    /**
     *
     * @return
     */
    public Port inputPort() {
        return inputPort;
    }

    /**
     *
     * @return
     */
    public Port outputPort() {
        return outputPort;
    }

    /**
     *
     * @return
     */
    @Override
    public Percentage calculateWearDelta() {
        return new Percentage(1);
    }
}
