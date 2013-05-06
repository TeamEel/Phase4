package eel.seprphase4.Simulator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase4.GameOverException;
import eel.seprphase4.Persistence.FileSystem;
import eel.seprphase4.Persistence.SaveGame;
import eel.seprphase4.Utilities.Energy;
import eel.seprphase4.Utilities.Percentage;
import eel.seprphase4.Utilities.Pressure;
import eel.seprphase4.Utilities.Temperature;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author David
 */
public class Simulator extends Observable implements PlantController, PlantStatus, GameManager {

    private PhysicalModel physicalModel;
    private FailureModel failureModel;
    private String userName;

    public Simulator(String userName) {
        physicalModel = new PhysicalModel();
        failureModel = new FailureModel(physicalModel, physicalModel, new RandomProbabilitySource());
        this.userName = userName;
        
        
    }

    @Override
    public boolean condenserHasFailed() {
        return failureModel.condenserHasFailed();
    }

    private void updateAndNotify() {
        setChanged();
        notifyObservers();
    }
 

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public void saveGame() throws JsonProcessingException {
        SaveGame saveGame = new SaveGame(physicalModel, failureModel, userName);
        try {
            saveGame.save();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    @Override
    public void loadGame(int gameNumber) {
        try {
            SaveGame saveGame = SaveGame.load(listGames()[gameNumber]);
            this.physicalModel = saveGame.getPhysicalModel();
            this.failureModel = saveGame.getFailureModel();
            this.userName = saveGame.getUserName();
        } catch (JsonParseException ex) {
        } catch (IOException ex) {
        }
    }

    @Override
    public String[] listGames() {
        return FileSystem.listSaveGames(userName);
    }


    public boolean step() {
        try {
            failureModel.step();
            return true;
        } catch (GameOverException e) {
            return false;
        }
    }

    public void failStateCheck() {
        failureModel.failStateCheck();
    }

    @Override
    public void moveControlRods(Percentage extracted) {
        failureModel.moveControlRods(extracted);
        updateAndNotify();
    }

    @Override
    public void changeValveState(int valveNumber, boolean isOpen) {
        failureModel.changeValveState(valveNumber, isOpen);
        updateAndNotify();
    }

    @Override
    public void changePumpState(int pumpNumber, boolean isPumping) {
        failureModel.changePumpState(pumpNumber, isPumping);
        updateAndNotify();
    }

    @Override
    public void quenchReactor() {
        failureModel.quenchReactor();
        updateAndNotify();
    }

    @Override
    public void repairPump(int pumpNumber) throws KeyNotFoundException, CannotRepairException {
        failureModel.repairPump(pumpNumber);
        updateAndNotify();
    }

    @Override
    public void repairCondenser() {
        failureModel.repairCondenser();
        updateAndNotify();
    }

    @Override
    public void repairTurbine() {
        failureModel.repairTurbine();
        updateAndNotify();
    }


    @Override
    public Percentage controlRodPosition() {
        return failureModel.controlRodPosition();
    }

    @Override
    public Pressure reactorPressure() {
        return failureModel.reactorPressure();
    }

    @Override
    public Temperature reactorTemperature() {
        return failureModel.reactorTemperature();
    }

    @Override
    public Percentage reactorWaterLevel() {
        return failureModel.reactorWaterLevel();
    }

    @Override
    public Energy energyGenerated() {
        return failureModel.energyGenerated();
    }


    @Override
    public Temperature condenserTemperature() {
        return failureModel.condenserTemperature();
    }

    @Override
    public Pressure condenserPressure() {
        return failureModel.condenserPressure();
    }

    @Override
    public Percentage condenserWaterLevel() {
        return failureModel.condenserWaterLevel();
    }

    @Override
    public Percentage reactorMinimumWaterLevel() {
        return failureModel.reactorMinimumWaterLevel();
    }

    @Override
    public boolean quencherUsed() {
        return failureModel.quencherUsed();
    }

    @Override
    public void step(int steps) throws GameOverException {
        failureModel.step(steps);
        updateAndNotify();
    }

    @Override
    public boolean turbineHasFailed() {
        return failureModel.turbineHasFailed();
    }

    @Override
    public ArrayList<FailableComponent> components() {
        throw new UnsupportedOperationException("Not supported yet");
    }


    @Override
    public void failCondenser() {
        failureModel.failCondenser();
    }

    @Override
    public void failReactor() {
        failureModel.failReactor();
    }

    @Override
    public void failSoftware() {
        failureModel.failSoftware();
    }

    @Override
    public void allowRandomFailures(boolean yes) {
        failureModel.allowRandomFailures(yes);
        updateAndNotify();
    }

    @Override
    public boolean allowsRandomFailures() {
        return failureModel.allowsRandomFailures();
    }

    @Override
    public void failTurbine() {
        failureModel.failTurbine();
    }

    @Override
    public void failPump(int pump) {
        failureModel.failPump(pump);
    }
@Override
    public boolean pumpStatus(int pumpNumber) {
        return failureModel.pumpStatus(pumpNumber);
    }

    @Override
    public boolean pumpFailed(int pumpNumber) {
        return failureModel.pumpFailed(pumpNumber);
    }

    @Override
    public boolean valveState(int valveNumber) {
        return failureModel.valveState(valveNumber);
    }

}
