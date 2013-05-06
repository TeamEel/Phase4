package eel.seprphase4.Simulator;

import eel.seprphase4.GameOverException;
import eel.seprphase4.Utilities.Energy;
import eel.seprphase4.Utilities.Percentage;
import eel.seprphase4.Utilities.Pressure;
import eel.seprphase4.Utilities.Temperature;
import static eel.seprphase4.Utilities.Units.*;
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
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class FailureModel implements PlantController, PlantStatus {

    @JsonProperty
    PlantController controller;
    @JsonProperty
    PlantStatus status;
    private Random failChance = new Random();
    @JsonProperty
    private int numberOfTimesWaterLevelIsTooLow;
    private final int reactorOverheatThreshold = 50;
    private final Pressure condenserMaxPressure = new Pressure(30662500);
    // Software will fail 1 out of softwareFailureProbability times
    private final int softwareFailureProbability = 1000;
    private boolean randomFailures = true;
    private ProbabilitySource probability;
    private final int pumpCount = 2;
    private final int valveCount = 2;
    private final int maxControlRodPosition = 10;
    
    private FailureModel() {
    }

    public FailureModel(PlantController plantController, PlantStatus plantStatus, ProbabilitySource probability) {
        this.controller = plantController;
        this.status = plantStatus;
        this.probability = probability;

    }

    @Override
    public boolean condenserHasFailed() {
        return status.condenserHasFailed();
    }

    private boolean softwareFailure() {
        /*
         * A 1 in 8 chance of a software failure occuring
         */
        if(allowsRandomFailures())
        {
            if (probability.trueOnceIn(10)) {
                randomCommand();
                return true;
            }
        }
        return false;
    }
    
    private void randomCommand() {
        /*
         * Pick a random command and execute it.
         */
        try {
            switch (probability.choiceFromZeroTo(5)) {
                case 0:
                    controller.changePumpState(probability.choiceFromZeroTo(pumpCount),true);
                    break;
                case 1:
                    controller.changePumpState(probability.choiceFromZeroTo(pumpCount),false);
                    break;
                case 2:
                    controller.changeValveState(probability.choiceFromZeroTo(valveCount),true);
                    break;
                case 3:
                    controller.changePumpState(probability.choiceFromZeroTo(valveCount),false);
                    break;
                case 4:
                    controller.moveControlRods(percent(probability.choiceFromZeroTo(maxControlRodPosition*10)));
                    break;
                case 5: 
                    //do nothing
                    break;
            }
        } catch (Exception e) {

        }
    }

    
    /**
     * Step the PhysicalModel and determine any failures.
     *
     * Also implements reactor safety rules.
     *
     */
    @Deprecated
    public void step() throws GameOverException {


        controller.step(1);

        if (randomFailures) {
            failStateCheck();
        }

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

       
    }

    @Override
    public void quenchReactor() {
        controller.quenchReactor();
    }

    @Override
    public void repairPump(int pumpNumber) throws KeyNotFoundException, CannotRepairException {
        controller.repairPump(pumpNumber);
    }

    @Override
    public void repairCondenser() {
        controller.repairCondenser();
    }

    @Override
    public void repairTurbine() {
        controller.repairTurbine();
    }
    
    @Override
    public Percentage controlRodPosition() {
        return status.controlRodPosition();
    }

    @Override
    public Pressure reactorPressure() {

        return status.reactorPressure();
    }

    @Override
    public Temperature reactorTemperature() {
        return status.reactorTemperature();
    }

    @Override
    public Percentage reactorWaterLevel() {
        
        return status.reactorWaterLevel();
    }

    @Override
    public Energy energyGenerated() {
        return status.energyGenerated();
    }



    @Override
    public Temperature condenserTemperature() {
        
        return status.condenserTemperature();
    }

    @Override
    public Pressure condenserPressure() {
        
        return status.condenserPressure();
    }

    @Override
    public Percentage condenserWaterLevel() {
        
        return status.condenserWaterLevel();
    }

    @Override
    public Percentage reactorMinimumWaterLevel() {
        return status.reactorMinimumWaterLevel();
    }

    @Override
    public boolean quencherUsed() {
        return status.quencherUsed();
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
        randomCommand();
    }

    @Override
    public void step(int i) throws GameOverException {
        controller.step(i);

        if (randomFailures) {
            failStateCheck();
        }

        checkReactorWaterLevel();
        checkCondenserPressure();
        checkTurbineFailure();
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
    public void failTurbine() {
        controller.failTurbine();
    }

    @Override
    public void failPump(int pump) {
        controller.failPump(pump);
    }

    @Override
    public void changePumpState(int pumpNumber, boolean isPumping) {
        if(!softwareFailure()) {
            controller.changePumpState(pumpNumber, isPumping);
        }
    }

    @Override
    public void moveControlRods(Percentage extracted) {
        if(!softwareFailure()) {
            controller.moveControlRods(extracted);
        }
    }

    @Override
    public void changeValveState(int valveNumber, boolean isOpen) {
        if(!softwareFailure()) {
            controller.changeValveState(valveNumber, isOpen);
        }
    }

   
    @Override
    public boolean allowsRandomFailures() {
        return this.randomFailures;
    }

    @Override
    public void allowRandomFailures(boolean yes) {
        this.randomFailures = yes;
    }

    @Override
    public boolean pumpStatus(int pumpNumber) {
        return status.pumpStatus(pumpNumber);
    }

    @Override
    public boolean pumpFailed(int pumpNumber) {
        return status.pumpFailed(pumpNumber);
    }

    @Override
    public boolean valveState(int valveNumber) {
        return status.valveState(valveNumber);
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

  
}