package cichlid.seprphase3.Simulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import cichlid.seprphase3.GameOverException;
import cichlid.seprphase3.Utilities.Percentage;
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
    public Boolean moveControlRods(Percentage extracted) throws CannotControlException, KeyNotFoundException;

    /**
     *
     */
    public Boolean changeValveState(int valveNumber, boolean isOpen) throws CannotControlException, KeyNotFoundException;

    public Boolean changePumpState(int pumpNumber, boolean isPumping) throws CannotControlException, KeyNotFoundException;

    public void repairPump(int pumpNumber) throws KeyNotFoundException, CannotRepairException;

    public void repairCondenser() throws CannotRepairException;

    public void repairTurbine() throws CannotRepairException;

    public void failCondenser();

    public void failReactor();

    public void failSoftware();

    public void setReactorToTurbine(boolean open);

    public void turbineFailurePrecautions();

    public void step(int steps) throws GameOverException;
}
