package eel.seprphase4.Persistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase4.Simulator.FailureModel;
import eel.seprphase4.Simulator.PhysicalModel;
import eel.seprphase4.Simulator.RandomProbabilitySource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

/**
 * Saveable/Loadable game state
 *
 * @author David
 */
public class SaveGame {

    @JsonProperty
    private PhysicalModel physicalModel;
    @JsonProperty
    private String userName;

    public SaveGame() {
    }

    public static SaveGame load(String path) throws JsonParseException, IOException {
        eel.seprphase4.Persistence.Persistence p = new eel.seprphase4.Persistence.Persistence();
        return p.deserializeSaveGame(Utils.readFile(path));
    }
    
    public SaveGame(PhysicalModel physicalModel, FailureModel failureModel, String userName) {
        this.physicalModel = physicalModel;
        this.userName = userName;
    }

    public void save() throws JsonProcessingException, FileNotFoundException, IOException {
        eel.seprphase4.Persistence.Persistence p = new eel.seprphase4.Persistence.Persistence();
        String data = p.serialize(this);
        FileSystem.createSavePath();
        FileSystem.writeString(fileName(), data);
    }

    public String getUserName() {
        return this.userName;
    }

    public PhysicalModel getPhysicalModel() {
        return this.physicalModel;
    }

    public FailureModel getFailureModel() {
        /*
         * TODO load multiplayer state from save
         */
        return new FailureModel(physicalModel, physicalModel, new RandomProbabilitySource());
    }

    /**
     * generateFileName generates a new unique file name using getTimeInMillis
     *
     * @return The newly generated random file name
     */
    private String fileName() {
        Calendar cal = Calendar.getInstance();
        return "sepr.teameel." + userName + "." + cal.getTimeInMillis() + ".nuke";
    }
}
