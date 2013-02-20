package cichlid.seprphase3.Simulator;

import cichlid.seprphase3.GameOverException;
import cichlid.seprphase3.Utilities.Energy;
import cichlid.seprphase3.Utilities.Percentage;
import cichlid.seprphase3.Utilities.Pressure;
import cichlid.seprphase3.Utilities.Temperature;
import static cichlid.seprphase3.Utilities.Units.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Manages software and hardware failures.
 *
 * The FailureModel is responsible for inducing software and hardware failures in the plant.
 *
 * Software failures are currently unimplemented; however, as all calls to the PhysicalModel are delegated through the
 * FailureModel, it is easy to alter this delegation in arbitrary ways to simulate control and/or reporting failures.
 *
 * Hardware failures are determined by inspecting the PhysicalModel to determine the status, wear, and operating
 * conditions of the various components, and then instructing the PhysicalModel to mark certain components as having
 * failed.
 *
 * Failures are constrained to one per timestep, which is enabled by the delegation of the step() method through the
 * FailureModel.
 *
 * @author Marius Dumitrescu
 */
@JsonAutoDetect(getterVisibility= JsonAutoDetect.Visibility.NONE, setterVisibility= JsonAutoDetect.Visibility.NONE)
public class FailureModel implements PlantController, PlantStatus {

    @JsonProperty
    PlantController controller;
    @JsonProperty
    PlantStatus status;
    private Random failChance = new Random();
    @JsonProperty
    private int numberOfTimesWaterLevelIsTooLow;
    private final int reactorOverheatThreshold = 8;
    private final Pressure condenserMaxPressure = new Pressure(30662500);

    // Software will fail 1 out of softwareFailureProbability times
    private final int softwareFailureProbability = 1000;

    private FailureModel() {
    }

    public FailureModel(PlantController plantController, PlantStatus plantStatus) {
        this.controller = plantController;
        this.status = plantStatus;
    }

    /**
     * Step the PhysicalModel and determine any failures.
     *
     * Also implements reactor safety rules.
     *
     */
    public void step() throws GameOverException {
        controller.step(1);
        failStateCheck();
        checkReactorWaterLevel();
        checkCondenserPressure();
        checkTurbineFailure();
    }

    /**
     * Determine failures
     *
     */
    public void failStateCheck() {
        ArrayList<FailableComponent> components = status.components();
        int failValue = failChance.nextInt(20000);  //A component that is 100% wear will have a 1 in 50 chance of failing
        int componentsFailChance = 0;
        for (int i = 0; i < components.size(); i++) {
            componentsFailChance += components.get(i).wear().points() / components.size();
            if (componentsFailChance > failValue) {
                components.get(i).fail();
                break; //We only want to induce one hardware failure! Break here.
            }

        }

        // There is also a 1 in 1000 chance that a software failure will occur at any step.
        // But only if there is not already a failure!
        if(status.getSoftwareFailure() == SoftwareFailure.None) {
            failValue = failChance.nextInt(softwareFailureProbability);
            if (failValue == 0) {
                failSoftware();
            }
        }
    }

    @Override
    public String[] listFailedComponents() {
        return status.listFailedComponents();
    }
    
    @Override
    public SoftwareFailure getSoftwareFailure() {
        return status.getSoftwareFailure();
    }

    @Override
    public Boolean moveControlRods(Percentage extracted) {
        if (status.getSoftwareFailure() == SoftwareFailure.rodStateChange) {
            //randomSoftwareFailure();
            return false;
        }
        controller.moveControlRods(extracted);
        return true;
    }

    @Override
    public Boolean changeValveState(int valveNumber, boolean isOpen) throws KeyNotFoundException {
        if (status.getSoftwareFailure() == SoftwareFailure.valveStateChange) {
            //randomSoftwareFailure();
            return false;
        }
        controller.changeValveState(valveNumber, isOpen);
        return true;
    }

    @Override
    public Boolean changePumpState(int pumpNumber, boolean isPumping) throws CannotControlException, KeyNotFoundException {
        if (status.getSoftwareFailure() == SoftwareFailure.pumpStateChange) {
            randomSoftwareFailure();
            return false;
        }
        controller.changePumpState(pumpNumber, isPumping);
        return true;
    }

    @Override
    public void repairPump(int pumpNumber) throws KeyNotFoundException, CannotRepairException {
        controller.repairPump(pumpNumber);
    }

    @Override
    public void repairCondenser() throws CannotRepairException {
        controller.repairCondenser();
    }

    @Override
    public void repairTurbine() throws CannotRepairException {
        controller.repairTurbine();
    }
    
    public void repairSoftware() {
        controller.repairSoftware();
    }

    @Override
    public Percentage controlRodPosition() {
        if (status.getSoftwareFailure() == SoftwareFailure.controlRodRead) {
            return null;
        }
        return status.controlRodPosition();
    }

    @Override
    public Pressure reactorPressure() {
        if (status.getSoftwareFailure() == SoftwareFailure.reactorPressureRead) {
            return null;
        }
        return status.reactorPressure();
    }

    @Override
    public Temperature reactorTemperature() {
        if (status.getSoftwareFailure() == SoftwareFailure.reactorTemperatureRead) {
            return null;
        }
        return status.reactorTemperature();
    }

    @Override
    public Percentage reactorWaterLevel() {
        if (status.getSoftwareFailure() == SoftwareFailure.reactorWaterRead) {
            return null;
        }
        return status.reactorWaterLevel();
    }

    @Override
    public Energy energyGenerated() {
        return status.energyGenerated();
    }

    @Override
    public void setReactorToTurbine(boolean open) {
        controller.setReactorToTurbine(open);
    }

    @Override
    public boolean getReactorToTurbine() {
        return status.getReactorToTurbine();
    }
    
    @Override
    public boolean getTurbineToCondenser() {
        return status.getTurbineToCondenser();
    }

    @Override
    public Temperature condenserTemperature() {
        if (status.getSoftwareFailure() == SoftwareFailure.condenserTemperatureRead) {
            return null;
        }
        return status.condenserTemperature();
    }

    @Override
    public Pressure condenserPressure() {
        if (status.getSoftwareFailure() == SoftwareFailure.condenserPressureRead) {
            return null;
        }
        return status.condenserPressure();
    }

    @Override
    public Percentage condenserWaterLevel() {
        if (status.getSoftwareFailure() == SoftwareFailure.condenserWaterRead) {
            return null;
        }
        return status.condenserWaterLevel();
    }

    @Override
    public Percentage reactorMinimumWaterLevel() {
        return status.reactorMinimumWaterLevel();
    }

    @Override
    public void failCondenser() {
        controller.failCondenser();
    }

    @Override
    public void failReactor() {
        controller.failReactor();
    }

    @Override
    public void failSoftware() {
        controller.failSoftware();
    }

    @Override
    public void step(int i) throws GameOverException {
        controller.step(i);
    }

    @Override
    public boolean turbineHasFailed() {
        return status.turbineHasFailed();
    }

    @Override
    public ArrayList<FailableComponent> components() {
        return status.components();
    }
    
    @Override
    public HashMap<String, FailableComponent> componentList() {
        return status.componentList();
    }
    
    @Override
    public HashMap<String, Connection> connectionList() {
        return status.connectionList();
    }

    private void checkReactorWaterLevel() {
        if (status.reactorWaterLevel().points() < status.reactorMinimumWaterLevel().points()) {
            numberOfTimesWaterLevelIsTooLow += 1;
            if (numberOfTimesWaterLevelIsTooLow > reactorOverheatThreshold) {
                controller.failReactor();
            }
        } else {
            numberOfTimesWaterLevelIsTooLow = 0;
        }
    }

    private void checkCondenserPressure() {
        if (status.condenserPressure().greaterThan(condenserMaxPressure)) {
            controller.failCondenser();
        }
    }

    private void checkTurbineFailure() {
        if (status.turbineHasFailed()) {
            controller.moveControlRods(new Percentage(0.0));
        }
    }

    private void randomSoftwareFailure() {
        RandomAction actionToFailWith = RandomAction.pickRandom();

        try {
            switch(actionToFailWith) {
                case pumpOff:
                    controller.changePumpState(0, false);
                case coolantPumpOff:
                    controller.changePumpState(1, false);
                case reactorControlRodsLower:
                    controller.moveControlRods(new Percentage(100.0));
                case reactorControlRodsRaise:
                    controller.moveControlRods(new Percentage(0.0));
                case valve1Close:
                    controller.changeValveState(1, false);
                default:
                    break;
            }
        } catch (CannotControlException e) {
            throw new RuntimeException("This should never happen as values are known.");
        } catch (KeyNotFoundException e) {
            throw new RuntimeException("This should never happen as values are known.");
        }
    }
}