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

    // Interface images
    private PlantGUIElement reactor;
    private PlantGUIElement condenser;
    private PlantGUIElement pump1;
    private PlantGUIElement pump2;
    private PlantGUIElement valve1;
    private PlantGUIElement valve2;
    private PlantGUIElement coolingPipe;
    private PlantGUIElement reactorToCondenserPipe;
    private PlantGUIElement condenserToReactorPipe;
    private PlantGUIElement fuelRods;
    private PlantGUIElement controlRods;
    private PlantGUIElement turbine;
    private PlantGUIElement turbineHousing;

    private final float WATER_LEVEL_PIX = 400;

    private final float SCALE_AMOUNT = 0.6f;

    AffineTransformOp rotateValve90Deg;

    public PlantInterface(PlantController plantController, PlantStatus plantStatus, GameManager gameManager) {
        this.plantController = plantController;
        this.plantStatus = plantStatus;
        this.gameManager = gameManager;
        
        setupComponents();
    }
    
    private void setupComponents() {
        BufferedImage reactorImage = loadImage("images/reactor.png");
        reactor = new PlantGUIElement(reactorImage, 50, 70, SCALE_AMOUNT);
        
        BufferedImage condenserImage = loadImage("images/reactor.png");
        condenser = new PlantGUIElement(condenserImage, 0, 0, SCALE_AMOUNT);
        
        BufferedImage pumpImage = loadImage("images/pump.png");
        pump1 = new PlantGUIElement(pumpImage, 0, 0, SCALE_AMOUNT);
        pump2 = new PlantGUIElement(pumpImage, 0, 0, SCALE_AMOUNT);
        
        BufferedImage valveImage = loadImage("images/valve.png");
        valve1 = new PlantGUIElement(valveImage, 0, 0, SCALE_AMOUNT);
        valve2 = new PlantGUIElement(valveImage, 0, 0, SCALE_AMOUNT);
        rotateValve90Deg = new AffineTransformOp(AffineTransform.getRotateInstance(Math.PI/2, valve1.image.getWidth()/2, (valve1.image.getHeight()/3)*2), AffineTransformOp.TYPE_BILINEAR);
        
        BufferedImage coolingPipeImage = loadImage("images/pipe1.png");
        coolingPipe = new PlantGUIElement(coolingPipeImage, 0, 0, SCALE_AMOUNT);
        
        BufferedImage reactorToCondenserPipeImage = loadImage("images/pipe2.png");
        reactorToCondenserPipe = new PlantGUIElement(reactorToCondenserPipeImage, 0, 0, SCALE_AMOUNT);
        
        BufferedImage condenserToReactorPipeImage = loadImage("images/pipe3.png");
        condenserToReactorPipe = new PlantGUIElement(condenserToReactorPipeImage, 0, 0, SCALE_AMOUNT);
        
        BufferedImage fuelRodsImage = loadImage("images/fuel_rods.png");
        fuelRods = new PlantGUIElement(fuelRodsImage, 0, 0, SCALE_AMOUNT);
        
        BufferedImage controlRodsImage = loadImage("images/control_rods.png");
        controlRods = new PlantGUIElement(controlRodsImage, 0, 0, SCALE_AMOUNT);
        
        BufferedImage turbineImage = loadImage("images/turbine.png");
        turbine = new PlantGUIElement(turbineImage, 0, 0, SCALE_AMOUNT);
        
        BufferedImage turbineHousingImage = loadImage("images/turbineC2.png");
        turbineHousing = new PlantGUIElement(turbineHousingImage, 0, 0, SCALE_AMOUNT);
    }
    
    
    
    private BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("Error loading image resources: " + e.getMessage());
        }
        
        return null;
    }
    
    public void drawPlantGUIElement(Graphics2D g, PlantGUIElement guiElement) {
        g.drawImage(guiElement.image, guiElement.x(), guiElement.y(), null);
    }
    
    public void drawTransformedGUIElement(Graphics2D g, PlantGUIElement guiElement, AffineTransformOp transform) {
        g.drawImage(transform.filter(guiElement.image, null), guiElement.x(), guiElement.y(), null);
    }

    @Override
    public void paintComponent(Graphics _g) {
        super.paintComponent(_g);
        
        Graphics2D g = (Graphics2D)_g;

        drawPlantGUIElement(g, reactor);
        
        drawPlantGUIElement(g, condenser);

        drawPlantGUIElement(g, valve1);
        
        drawPlantGUIElement(g, valve2);
        
        drawPlantGUIElement(g, reactorToCondenserPipe);
        
        drawPlantGUIElement(g, condenserToReactorPipe);
        
        drawPlantGUIElement(g, turbineHousing);
        
        drawPlantGUIElement(g, turbine);
        
        drawTransformedGUIElement(g, valve2, rotateValve90Deg);
        
        drawPlantGUIElement(g, coolingPipe);
        
        drawPlantGUIElement(g, pump1);
        
        drawPlantGUIElement(g, pump2);

        drawPlantGUIElement(g, controlRods);
        
        drawPlantGUIElement(g, fuelRods);

        //g2d.setColor(new Color(0, 0, 255, 100));

        //g2d.fillRect(90, 175, 288, (int)(WATER_LEVEL_PIX * plantStatus.reactorWaterLevel().ratio()));
    }
}
