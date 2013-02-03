package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.Simulator.GameManager;
import cichlid.seprphase3.Simulator.PlantController;
import cichlid.seprphase3.Simulator.PlantStatus;
import cichlid.seprphase3.Simulator.SoftwareFailure;
import cichlid.seprphase3.Utilities.Pressure;

public class GUIInterface {

    private PlantController plantController;
    private PlantStatus plantStatus;
    private GameManager gameManager;

    public GUIInterface(PlantController plantController, PlantStatus plantStatus, GameManager gameManager) {
        this.plantController = plantController;
        this.plantStatus = plantStatus;
        this.gameManager = gameManager;
    }
}
