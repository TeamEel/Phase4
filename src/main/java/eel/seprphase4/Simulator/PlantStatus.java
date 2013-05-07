package eel.seprphase4.Simulator;

import eel.seprphase4.Utilities.Energy;
import eel.seprphase4.Utilities.Percentage;
import eel.seprphase4.Utilities.Pressure;
import eel.seprphase4.Utilities.Temperature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

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
    public Temperature condenserTemperature();

    public Pressure condenserPressure();

    public Percentage condenserWaterLevel();

    public Percentage reactorMinimumWaterLevel();

    public boolean quencherUsed();

    public boolean turbineHasFailed();
    
    public boolean pumpIsOn(int pumpNumber);
    
    public boolean pumpHasFailed(int pumpNumber);

    public boolean valveIsOn(int valveNumber);
    
    public ArrayList<FailableComponent> components();

    public boolean allowsRandomFailures();
    
    public boolean condenserHasFailed();
}
