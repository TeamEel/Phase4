package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.Simulator.GameManager;
import cichlid.seprphase3.Simulator.PlantController;
import cichlid.seprphase3.Simulator.PlantStatus;
import cichlid.seprphase3.Simulator.SoftwareFailure;
import cichlid.seprphase3.Utilities.Pressure;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.JPanel;

public class PlantInterface extends JPanel {

    private PlantController plantController;
    private PlantStatus plantStatus;
    private GameManager gameManager;
    
    // Interface images
    private BufferedImage reactorImage;
    private BufferedImage condenserImage;
    private BufferedImage pumpImage;
    private BufferedImage valveImage;
    private BufferedImage coolingPipeImage;
    private BufferedImage reactorToCondenserPipeImage;
    private BufferedImage condenserToReactorPipeImage;
    private BufferedImage fuelRodsImage;
    private BufferedImage controlRodsImage;
    private BufferedImage turbineImage;
    private BufferedImage turbineHousingImage;
    
    AffineTransformOp rotateValve90Deg;

    public PlantInterface(PlantController plantController, PlantStatus plantStatus, GameManager gameManager) {
        this.plantController = plantController;
        this.plantStatus = plantStatus;
        this.gameManager = gameManager;
        
        reactorImage = loadImage("images/reactor.png");
        condenserImage = loadImage("images/reactor.png");
        
        pumpImage = loadImage("images/pump.png");
        valveImage = loadImage("images/valve.png");
        
        coolingPipeImage = loadImage("images/pipe1.png");
        reactorToCondenserPipeImage = loadImage("images/pipe2.png");
        condenserToReactorPipeImage = loadImage("images/pipe3.png");
        
        fuelRodsImage = loadImage("images/fuel_rods.png");
        controlRodsImage = loadImage("images/control_rods.png");
        
        turbineImage = loadImage("images/turbine.png");
        turbineHousingImage = loadImage("images/turbineC2.png");
        
        rotateValve90Deg = new AffineTransformOp(AffineTransform.getRotateInstance(Math.PI/2, valveImage.getWidth()/2, (valveImage.getHeight()/3)*2), AffineTransformOp.TYPE_BILINEAR);
    }
    
    private BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("Error loading image resources: " + e.getMessage());
        }
        
        return null;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.drawImage(reactorImage, 50, 70, null);
        
        g2d.drawImage(condenserImage, 1100, 400, null);
        
        g2d.drawImage(valveImage, 550, 13, null);
        
        g2d.drawImage(reactorToCondenserPipeImage, 270, 65, null);
        
        g2d.drawImage(turbineHousingImage, 1150, 135, null);
        
        g2d.drawImage(turbineHousingImage, 1350, 130, null);
        
        g2d.drawImage(turbineImage, 1195, 150, this);
        
        g2d.drawImage(rotateValve90Deg.filter(valveImage, null), 1291, 373, null);

        g2d.drawImage(coolingPipeImage, 1250, 600, null);
        
        g2d.drawImage(pumpImage, 1500, 760, null);

        g.drawImage(condenserToReactorPipeImage, 200, 300, null);
//        
//        g.drawImage(fuelRodsImage, 100, 100, null);
//        g.drawImage(controlRodsImage, 100, 100, null);
//        
//        g.drawImage(turbineImage, 100, 100, null);

    }
}
