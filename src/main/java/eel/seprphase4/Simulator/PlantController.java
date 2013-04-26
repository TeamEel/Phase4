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
    public Boolean moveControlRods(Percentage extracted);

    /**
     *
     */
    public Boolean changeValveState(int valveNumber, boolean isOpen) throws KeyNotFoundException;

    public Boolean changePumpState(int pumpNumber, boolean isPumping) throws CannotControlException,
                                                                             KeyNotFoundException;
    
    public void quenchReactor();

    public void repairPump(int pumpNumber) throws KeyNotFoundException, CannotRepairException;

    public void repairCondenser() throws CannotRepairException;

    public void repairTurbine() throws CannotRepairException;

    public void repairSoftware();

    public void failCondenser();

    public void failReactor();

    public void failSoftware();

    public void failTurbine();
    
    public void failPump(int pump);
    
    public void setReactorToTurbine(boolean open);

    public void step(int steps) throws GameOverException;
    
    public void allowRandomFailures(boolean yes);
}
