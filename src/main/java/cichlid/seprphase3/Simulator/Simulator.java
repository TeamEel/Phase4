package cichlid.seprphase3.Simulator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import cichlid.seprphase3.GameOverException;
import cichlid.seprphase3.Persistence.FileSystem;
import cichlid.seprphase3.Persistence.SaveGame;
import cichlid.seprphase3.Utilities.Energy;
import cichlid.seprphase3.Utilities.Percentage;
import cichlid.seprphase3.Utilities.Pressure;
import cichlid.seprphase3.Utilities.Temperature;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author David
 */
public class Simulator implements PlantController, PlantStatus, GameManager {

    private PhysicalModel physicalModel;
    private FailureModel failureModel;
    private String userName;

    public Simulator() {
        physicalModel = new PhysicalModel();
        failureModel = new FailureModel(physicalModel, physicalModel);
        userName = "";
    }

    @Override
    public void setUsername(String userName) {
        this.userName = userName;
    }
    
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

    @Override
    public String[] listFailedComponents() {
        return failureModel.listFailedComponents();
    }
    
    @Override
    public SoftwareFailure getSoftwareFailure() {
        return failureModel.getSoftwareFailure();
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
    public Boolean moveControlRods(Percentage extracted) {
        return failureModel.moveControlRods(extracted);
    }

    @Override
    public Boolean changeValveState(int valveNumber, boolean isOpen) throws KeyNotFoundException {
        return failureModel.changeValveState(valveNumber, isOpen);
    }

    @Override
    public Boolean changePumpState(int pumpNumber, boolean isPumping) throws CannotControlException, KeyNotFoundException {
        return failureModel.changePumpState(pumpNumber, isPumping);
    }

    @Override
    public void repairPump(int pumpNumber) throws KeyNotFoundException, CannotRepairException {
        failureModel.repairPump(pumpNumber);
    }

    @Override
    public void repairCondenser() throws CannotRepairException {
        failureModel.repairCondenser();
    }

    @Override
    public void repairTurbine() throws CannotRepairException {
        failureModel.repairTurbine();
    }
    
    @Override
    public void repairSoftware() {
        failureModel.repairSoftware();
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
    public void setReactorToTurbine(boolean open) {
        failureModel.setReactorToTurbine(open);
    }

    @Override
    public boolean getReactorToTurbine() {
        return failureModel.getReactorToTurbine();
    }
    
    @Override
    public boolean getTurbineToCondenser() {
        return failureModel.getTurbineToCondenser();
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
    public void failCondenser() {
        throw new UnsupportedOperationException("Not meaningful at this level.");
    }

    @Override
    public void failReactor() {
        throw new UnsupportedOperationException("Not meaningful at this level.");
    }
    
    @Override
    public void failSoftware() {
        throw new UnsupportedOperationException("Not meaningful at this level.");
    }

    @Override
    public void step(int steps) throws GameOverException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean turbineHasFailed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<FailableComponent> components() {
        throw new UnsupportedOperationException("Not supported yet");
    }
    
    @Override
    public HashMap<String, FailableComponent> componentList() {
        return failureModel.componentList();
    }
    
    @Override
    public HashMap<String, Connection> connectionList() {
        return failureModel.connectionList();
    }
}
