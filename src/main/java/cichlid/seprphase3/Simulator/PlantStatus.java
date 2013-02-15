package cichlid.seprphase3.Simulator;

import cichlid.seprphase3.Utilities.Energy;
import cichlid.seprphase3.Utilities.Percentage;
import cichlid.seprphase3.Utilities.Pressure;
import cichlid.seprphase3.Utilities.Temperature;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author David
 */
public interface PlantStatus {

    /**
     *
     * @return
     */
    public Percentage controlRodPosition();

    /**
     *
     * @return
     */
    public Pressure reactorPressure();

    /**
     *
     * @return
     */
    public Temperature reactorTemperature();

    /**
     *
     * @return
     */
    public Percentage reactorWaterLevel();

    /**
     *
     * @return
     */
    public Energy energyGenerated();

    /**
     *
     * @return
     */
    public boolean getReactorToTurbine();

    public Temperature condenserTemperature();

    public Pressure condenserPressure();

    public Percentage condenserWaterLevel();

    public Percentage reactorMinimumWaterLevel();

    public String[] listFailedComponents();
    
    public SoftwareFailure getSoftwareFailure();

    public boolean turbineHasFailed();

    public ArrayList<FailableComponent> components();
    
    public HashMap<String, FailableComponent> componentList();
    public HashMap<String, Connection> connectionList();
}
