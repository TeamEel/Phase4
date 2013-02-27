package cichlid.seprphase3.Persistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import cichlid.seprphase3.Simulator.FailureModel;
import cichlid.seprphase3.Simulator.PhysicalModel;
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

    public static SaveGame load(String filename) throws JsonParseException, IOException {
        cichlid.seprphase3.Persistence.Persistence p = new cichlid.seprphase3.Persistence.Persistence();
        return p.deserializeSaveGame(FileSystem.readString(filename));
    }

    public SaveGame(PhysicalModel physicalModel, FailureModel failureModel, String userName) {
        this.physicalModel = physicalModel;
        this.userName = userName;
    }

    public void save() throws JsonProcessingException, FileNotFoundException, IOException {
        cichlid.seprphase3.Persistence.Persistence p = new cichlid.seprphase3.Persistence.Persistence();
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
        return new FailureModel(physicalModel, physicalModel);
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
