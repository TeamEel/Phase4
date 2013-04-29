package eel.seprphase4.Simulator;

import eel.seprphase4.Utilities.Energy;
import eel.seprphase4.Utilities.Percentage;
import eel.seprphase4.Utilities.Pressure;
import eel.seprphase4.Utilities.Temperature;
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

    public boolean getTurbineToCondenser();

    public Temperature condenserTemperature();

    public Pressure condenserPressure();

    public Percentage condenserWaterLevel();

    public Percentage reactorMinimumWaterLevel();

    public boolean quencherUsed();

    public String[] listFailedComponents();

    public SoftwareFailure getSoftwareFailure();

    public boolean turbineHasFailed();

    public ArrayList<FailableComponent> components();

    public HashMap<String, FailableComponent> componentList();

    public HashMap<String, Connection> connectionList();

    public boolean allowsRandomFailures();
}
