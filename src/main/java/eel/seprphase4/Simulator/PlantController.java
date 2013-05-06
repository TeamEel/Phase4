package eel.seprphase4.Simulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase4.GameOverException;
import eel.seprphase4.Utilities.Percentage;
import java.io.IOException;

/**
 *
 * @author David
 */
public interface PlantController {

    /**
     *
     * @param extracted
     */
    public void moveControlRods(Percentage extracted);

    public void changeValveState(int valveNumber, boolean isOpen);

    public void changePumpState(int pumpNumber, boolean isPumping);

    public void quenchReactor();

    public void repairPump(int pumpNumber) throws KeyNotFoundException, CannotRepairException;

    public void repairCondenser();

    public void repairTurbine();

    public void failCondenser();

    public void failReactor();

    public void failSoftware();

    public void failTurbine();

    public void failPump(int pump);

    public void step(int steps) throws GameOverException;

    public void allowRandomFailures(boolean yes);
    
  
}
