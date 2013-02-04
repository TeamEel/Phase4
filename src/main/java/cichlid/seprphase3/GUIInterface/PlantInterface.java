package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.Simulator.GameManager;
import cichlid.seprphase3.Simulator.PlantController;
import cichlid.seprphase3.Simulator.PlantStatus;
import cichlid.seprphase3.Simulator.SoftwareFailure;
import cichlid.seprphase3.Utilities.Pressure;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class PlantInterface extends JPanel {

    private PlantController plantController;
    private PlantStatus plantStatus;
    private GameManager gameManager;

    public PlantInterface(PlantController plantController, PlantStatus plantStatus, GameManager gameManager) {
        this.plantController = plantController;
        this.plantStatus = plantStatus;
        this.gameManager = gameManager;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Insert Power Plant Here", 20, 20);
    }
}
