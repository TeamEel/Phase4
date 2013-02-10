package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.GameOverException;
import cichlid.seprphase3.Simulator.GameManager;
import cichlid.seprphase3.Simulator.PlantController;
import cichlid.seprphase3.Simulator.PlantStatus;
import cichlid.seprphase3.Simulator.SoftwareFailure;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    private PlantGUIElement coolingPump;
    private PlantGUIElement valve1;
    private PlantGUIElement valve2;
    private PlantGUIElement coolingPipe;
    private PlantGUIElement reactorToCondenserPipe;
    private PlantGUIElement condenserToReactorPipe;
    private PlantGUIElement fuelRods;
    private PlantGUIElement controlRods;
    private PlantGUIElement turbine;
    private PlantGUIElement turbineHousing;
    private PlantGUIElement turbineHousing2;

    private final int WATER_LEVEL_PIX = 240;

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
        condenser = new PlantGUIElement(condenserImage, 715, 345, SCALE_AMOUNT);
        
        BufferedImage pumpImage = loadImage("images/pump.png");
        pump1 = new PlantGUIElement(pumpImage, 622, 563, SCALE_AMOUNT);
        coolingPump = new PlantGUIElement(pumpImage, 965, 585, SCALE_AMOUNT);
        
        BufferedImage valveImage = loadImage("images/valve.png");
        valve1 = new PlantGUIElement(valveImage, 345, 35, SCALE_AMOUNT);
        valve2 = new PlantGUIElement(valveImage, 790, 250, SCALE_AMOUNT);
        rotateValve90Deg = new AffineTransformOp(AffineTransform.getRotateInstance(Math.PI/2, valve1.image.getWidth()/2, (valve1.image.getHeight()/3)*2), AffineTransformOp.TYPE_BILINEAR);
        
        BufferedImage coolingPipeImage = loadImage("images/pipe1.png");
        coolingPipe = new PlantGUIElement(coolingPipeImage, 800, 490, SCALE_AMOUNT);
        
        BufferedImage reactorToCondenserPipeImage = loadImage("images/pipe2.png");
        reactorToCondenserPipe = new PlantGUIElement(reactorToCondenserPipeImage, 178, 65, SCALE_AMOUNT);
        
        BufferedImage condenserToReactorPipeImage = loadImage("images/pipe3.png");
        condenserToReactorPipe = new PlantGUIElement(condenserToReactorPipeImage, 173, 376, SCALE_AMOUNT);
        
        BufferedImage fuelRodsImage = loadImage("images/fuel_rods.png");
        fuelRods = new PlantGUIElement(fuelRodsImage, 108, 260, SCALE_AMOUNT);
        
        BufferedImage controlRodsImage = loadImage("images/control_rods.png");
        controlRods = new PlantGUIElement(controlRodsImage, 108, 50, SCALE_AMOUNT);
        
        BufferedImage turbineImage = loadImage("images/turbine.png");
        turbine = new PlantGUIElement(turbineImage, 725, 115, SCALE_AMOUNT);
        
        BufferedImage turbineHousingImage = loadImage("images/turbineC2.png");
        turbineHousing = new PlantGUIElement(turbineHousingImage, 700, 106, SCALE_AMOUNT);
        turbineHousing2 = new PlantGUIElement(turbineHousingImage, 820, 100, SCALE_AMOUNT+0.1f);
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

        drawPlant(g);
        
        drawWater(g);
        
        drawEffects(g);
        
        drawText(g);
        
        //g2d.setColor(new Color(0, 0, 255, 100));

        //g2d.fillRect(90, 175, 288, (int)(WATER_LEVEL_PIX * plantStatus.reactorWaterLevel().ratio()));
    }
    
    private void drawPlant(Graphics2D g) {
        drawPlantGUIElement(g, reactor);
        drawPlantGUIElement(g, condenser);
        
        drawPlantGUIElement(g, pump1);
        drawPlantGUIElement(g, coolingPump);
        
        drawPlantGUIElement(g, reactorToCondenserPipe);
        drawPlantGUIElement(g, condenserToReactorPipe);
        drawPlantGUIElement(g, coolingPipe);
        
        drawPlantGUIElement(g, turbineHousing);
        drawPlantGUIElement(g, turbineHousing2);
        drawPlantGUIElement(g, turbine);
        
        drawPlantGUIElement(g, valve1);
        drawTransformedGUIElement(g, valve2, rotateValve90Deg);

        drawPlantGUIElement(g, controlRods);
        drawPlantGUIElement(g, fuelRods);
    }
    
    private void drawWater(Graphics2D g) {
        g.setColor(new Color(0.0f, 0.0f, 0.5f, 0.5f));
        
        if(plantStatus.reactorWaterLevel() != null) {
            g.fillRect(55,
                        129 + (int)(WATER_LEVEL_PIX * (1.0 - plantStatus.reactorWaterLevel().ratio())),
                        173,
                        (int)(WATER_LEVEL_PIX * plantStatus.reactorWaterLevel().ratio()));
        }
        
        if(plantStatus.condenserWaterLevel() != null) {
            g.fillRect(720,
                        405 + (int)(WATER_LEVEL_PIX * (1.0 - plantStatus.condenserWaterLevel().ratio())),
                        173,
                        (int)(WATER_LEVEL_PIX * plantStatus.condenserWaterLevel().ratio()));
        }
    }
    
    private void drawEffects(Graphics2D g) {
        
    }
    
    private void drawText(Graphics2D g) {
        int textX = 400;
        int textY = 200;
        
        g.drawString("> " + plantStatus.reactorWaterLevel().ratio(), textX, textY - 50);
        
        g.drawString("Reactor Pressure: " + plantStatus.reactorPressure(), textX, textY);
        g.drawString("Reactor Temp: " + plantStatus.reactorTemperature(), textX, textY + 20);
        g.drawString("Reactor Water: " + plantStatus.reactorWaterLevel(), textX, textY + 40);
        
        g.drawString("Condenser Pressure: " + plantStatus.condenserPressure(), textX, textY + 60);
        g.drawString("Condenser Temp: " + plantStatus.condenserTemperature(), textX, textY + 80);
        g.drawString("Condenser Water: " + plantStatus.condenserWaterLevel(), textX, textY + 100);
        
        g.drawString("Reactor to Turbine: " + plantStatus.getReactorToTurbine(), textX, textY + 120);
        
        g.drawString("Energy Generated: " + plantStatus.energyGenerated(), textX, textY + 140);
        
        g.drawString("Software Failure: " + plantStatus.getSoftwareFailure(), textX, textY + 160);
        
        int offset = 0;
        for(String s : plantStatus.listFailedComponents()) {
            g.drawString("Failure: " + s, textX, textY + 180 + offset);
            offset += 20;
        }
    }
}
