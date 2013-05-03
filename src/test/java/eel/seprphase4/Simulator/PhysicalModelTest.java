package eel.seprphase4.Simulator;

import eel.seprphase4.Simulator.KeyNotFoundException;
import eel.seprphase4.Simulator.PhysicalModel;
import eel.seprphase4.Simulator.CannotControlException;
import eel.seprphase4.Simulator.CannotRepairException;
import eel.seprphase4.GameOverException;
import org.junit.Test;
import static org.junit.Assert.*;
import static eel.seprphase4.Utilities.Units.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author Marius
 */
public class PhysicalModelTest {

    @Test
    public void runningStepIncreasesReactorTemperature() throws GameOverException {
        PhysicalModel model = new PhysicalModel();
        model.moveControlRods(percent(100));
        model.step(10);
        assertThat(model.reactorTemperature().inKelvin(), greaterThan(350.0));
    }

    @Test
    public void reactorMovesTurbine() throws GameOverException {
        PhysicalModel model = new PhysicalModel();
        model.moveControlRods(percent(100));
        model.step(100);
        assertThat(model.energyGenerated().inJoules(), greaterThan(0.0));
    }

    @Test
    public void shouldSetControlRodPosition() throws GameOverException {
        PhysicalModel model = new PhysicalModel();
        model.moveControlRods(percent(100));
        assertTrue(model.controlRodPosition().equals(percent(100)));
    }



    @Test
    public void shouldSetCondenserBackToNormalFailureState() {
        PhysicalModel model = new PhysicalModel();
        model.failCondenser();
        try {
            model.repairCondenser();
        } catch (CannotRepairException e) {
            fail(e.getMessage());
        }
        assertFalse(model.components().get(2).hasFailed());
    }

    @Test
    public void shouldSetTurbineBackToNormalFailureState() {
        PhysicalModel model = new PhysicalModel();
        model.components().get(0).fail();
        try {
            model.repairTurbine();
        } catch (CannotRepairException e) {
            fail(e.getMessage());
        }
        assertFalse(model.components().get(0).hasFailed());
    }

    @Test(expected = CannotRepairException.class)
    public void shouldNotSetCondenserBackToNormalFailureState() throws CannotRepairException {
        PhysicalModel model = new PhysicalModel();


        model.repairCondenser();

    }

    @Test(expected = CannotRepairException.class)
    public void shouldNotSetTurbineBackToNormalFailureState() throws CannotRepairException {
        PhysicalModel model = new PhysicalModel();


        model.repairCondenser();

    }


    @Test(expected = CannotRepairException.class)
    public void shouldNotSetPumpBackToNormalFailureState() throws CannotRepairException, KeyNotFoundException {
        PhysicalModel model = new PhysicalModel();
        model.repairPump(1);
    }

    @Test
    public void shouldInitializePump2ToPumping() {
        PhysicalModel model = new PhysicalModel();
        assertTrue(model.pumpStatus(2));
    }

    @Test
    public void shouldInitializePump1ToPumping() {
        PhysicalModel model = new PhysicalModel();
        assertTrue(model.pumpStatus(1));

    }

    @Test
    public void shouldSetPumpStateToOff() throws CannotControlException, KeyNotFoundException {
        PhysicalModel model = new PhysicalModel();
        assertTrue(model.pumpStatus(1));
        model.changePumpState(1, false);
        assertFalse(model.pumpStatus(1));
    }

    @Test
    public void shouldSetPumpStateToOn() throws CannotControlException, KeyNotFoundException {
        PhysicalModel model = new PhysicalModel();
        assertTrue(model.pumpStatus(1));
        model.changePumpState(1, false);
        assertFalse(model.pumpStatus(1));
        model.changePumpState(1, true);
        assertTrue(model.pumpStatus(1));
    }

    @Test(expected = KeyNotFoundException.class)
    public void shouldRefuseToRepairInvalidPump() throws KeyNotFoundException, CannotRepairException {
        PhysicalModel model = new PhysicalModel();
        model.repairPump(100);
    }

    @Test(expected = KeyNotFoundException.class)
    public void shouldRefuseToControlInvalidPump() throws CannotControlException, KeyNotFoundException {
        PhysicalModel model = new PhysicalModel();
        model.changePumpState(100, true);
    }

    @Test(expected = KeyNotFoundException.class)
    public void shouldRefuseToControlInvalidValve() throws KeyNotFoundException {
        PhysicalModel model = new PhysicalModel();
        model.changeValveState(100, true);
    }
}
