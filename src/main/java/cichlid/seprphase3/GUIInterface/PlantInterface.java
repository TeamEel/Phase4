package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.GameOverException;
import cichlid.seprphase3.Simulator.GameManager;
import cichlid.seprphase3.Simulator.PlantController;
import cichlid.seprphase3.Simulator.PlantStatus;
import cichlid.seprphase3.Simulator.SoftwareFailure;
import cichlid.seprphase3.Utilities.Pressure;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.JPanel;

public class PlantInterface extends JPanel {

    private PlantController plantController;
    private PlantStatus plantStatus;
    private GameManager gameManager;
    
//    private Rectangle reactorLocation = new Rectangle(50, 70);
//    private Rectangle condenserLocation = new Rectangle();
//    private Rectangle pump1Location = new Rectangle();
//    private Rectangle pump2Location = new Rectangle();
//    private Rectangle valve1Location = new Rectangle();
//    private Rectangle valve2Location = new Rectangle();
//    private Rectangle coolingPipeLocation = new Rectangle();
//    private Rectangle reactorToCondenserLocation = new Rectangle();
//    private Rectangle condenserToReactorLocation = new Rectangle();
//    private Rectangle fuelRodsLocation = new Rectangle();
//    private Rectangle controlRodsLocation = new Rectangle();
//    private Rectangle turbineLocation = new Rectangle();
//    private Rectangle turbineHousingLocation = new Rectangle();

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

    private final float WATER_LEVEL_PIX = 400;

    private final float SCALE_AMOUNT = 0.6f;

    AffineTransformOp rotateValve90Deg;

    public PlantInterface(PlantController plantController, PlantStatus plantStatus, GameManager gameManager) {
        this.plantController = plantController;
        this.plantStatus = plantStatus;
        this.gameManager = gameManager;
        
        loadImages();
        
        rotateValve90Deg = new AffineTransformOp(AffineTransform.getRotateInstance(Math.PI/2, valveImage.getWidth()/2, (valveImage.getHeight()/3)*2), AffineTransformOp.TYPE_BILINEAR);
    }
    
    private void loadImages() {
        BufferedImage unscaledReactorImage = loadImage("images/reactor.png");
        reactorImage = scaleImage(unscaledReactorImage, SCALE_AMOUNT);
        
        BufferedImage unscaledCondenserImage = loadImage("images/reactor.png");
        condenserImage = scaleImage(unscaledCondenserImage, SCALE_AMOUNT);
        
        BufferedImage unscaledPumpImage = loadImage("images/pump.png");
        pumpImage = scaleImage(unscaledPumpImage, SCALE_AMOUNT);
        BufferedImage unscaledValveImage = loadImage("images/valve.png");
        valveImage = scaleImage(unscaledValveImage, SCALE_AMOUNT);
        
        BufferedImage unscaledCoolingPipeImage = loadImage("images/pipe1.png");
        coolingPipeImage = scaleImage(unscaledCoolingPipeImage, SCALE_AMOUNT);
        BufferedImage unscaledReactorToCondenserPipeImage = loadImage("images/pipe2.png");
        reactorToCondenserPipeImage = scaleImage(unscaledReactorToCondenserPipeImage, SCALE_AMOUNT);
        BufferedImage unscaledCondenserToReactorPipeImage = loadImage("images/pipe3.png");
        condenserToReactorPipeImage = scaleImage(unscaledCondenserToReactorPipeImage, SCALE_AMOUNT);
        
        BufferedImage unscaledFuelRodsImage = loadImage("images/fuel_rods.png");
        fuelRodsImage = scaleImage(unscaledFuelRodsImage, SCALE_AMOUNT);
        BufferedImage unscaledControlRodsImage = loadImage("images/control_rods.png");
        controlRodsImage = scaleImage(unscaledControlRodsImage, SCALE_AMOUNT);
        
        BufferedImage unscaledTurbineImage = loadImage("images/turbine.png");
        turbineImage = scaleImage(unscaledTurbineImage, SCALE_AMOUNT);
        BufferedImage unscaledTurbineHousingImage = loadImage("images/turbineC2.png");
        turbineHousingImage = scaleImage(unscaledTurbineHousingImage, SCALE_AMOUNT);
    }
    
    private BufferedImage scaleImage(BufferedImage unscaledImage, float percent) {
        Image scaledImage = unscaledImage.getScaledInstance( (int)(unscaledImage.getWidth() * percent), -1, Image.SCALE_SMOOTH);
        BufferedImage scaledBufferedImage = new BufferedImage(scaledImage.getWidth(null), scaledImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        scaledBufferedImage.getGraphics().drawImage(scaledImage, 0, 0, null);
        return scaledBufferedImage;
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

        g2d.drawImage(condenserToReactorPipeImage, 250, 584, null);

        g.drawImage(controlRodsImage, 150, -150, null);

        g.drawImage(fuelRodsImage, 150, 350, null);

        g2d.setColor(new Color(0, 0, 255, 100));

        //g2d.fillRect(90, 175, 288, (int)(WATER_LEVEL_PIX * plantStatus.reactorWaterLevel().ratio()));
    }
}
