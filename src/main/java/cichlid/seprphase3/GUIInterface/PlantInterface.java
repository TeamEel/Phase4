package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.GameOverException;
import cichlid.seprphase3.Simulator.GameManager;
import cichlid.seprphase3.Simulator.PlantController;
import cichlid.seprphase3.Simulator.PlantStatus;
import cichlid.seprphase3.Simulator.SoftwareFailure;
import cichlid.seprphase3.Utilities.Percentage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.JPanel;

/**
 * This is the main interface which will be shown during the game.
 * It is responsible for drawing the representation of the plant to the screen.
 */
public class PlantInterface extends JPanel {

    // These allow access to the plant's methods.
    private PlantController plantController;
    private PlantStatus plantStatus;
    private GameManager gameManager;

    // These represent parts of the plant which will be drawn to the screen.
    // Each has a location and an image which is set up in setupComponents().
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

    // The height of the water in the Reactor and Condenser in pixels.
    private final int MAX_WATER_HEIGHT = 240;

    // The global scale applied to images in the plant to make them the right size on the screen.
    private final int X_OFFSET = 350;
    private final int Y_OFFSET = 100;
    private final float SCALE_AMOUNT = 0.6f;

    // An AffineTransform is one which preserves straight lines. This is used to rotate the valve image
    // by 90 degrees so the valve above the condenser can be drawn in the right orientation.
    AffineTransformOp rotateValve90Deg;

    /**
     * The interface is set up with references to the plant classes it needs for getting and setting information.
     * @param plantController
     * @param plantStatus
     * @param gameManager 
     */
    public PlantInterface(PlantController plantController, PlantStatus plantStatus, GameManager gameManager) {
        this.plantController = plantController;
        this.plantStatus = plantStatus;
        this.gameManager = gameManager;
        
        // Give all of the plant components the right images and location on the screen.
        setupComponents();
    }
    
    /**
     * Gives all of the plant components the right images and locations on the screen.
     * Each component has the form:
     *      BufferedImage image = loadImage("images/image.png");
     *      component = new PlantGUIElement( image, location x, location y, scale amount);
     *      
     */
    private void setupComponents() {
        BufferedImage reactorImage = loadImage("images/reactor.png");
        reactor = new PlantGUIElement(reactorImage, 0, 0, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        
        BufferedImage condenserImage = loadImage("images/reactor.png");
        condenser = new PlantGUIElement(condenserImage, 665, 275, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        
        BufferedImage pumpImage = loadImage("images/pump.png");
        pump1 = new PlantGUIElement(pumpImage, 572, 493, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        coolingPump = new PlantGUIElement(pumpImage, 915, 515, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        
        BufferedImage valveImage = loadImage("images/valve.png");
        valve1 = new PlantGUIElement(valveImage, 295, -35, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        valve2 = new PlantGUIElement(valveImage, 740, 180, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        
        // This sets up the transform used to rotate the valve by 90 degrees.
        // Uses getRotateInstance, which takes an origin. The origin is the middle of the image,
        // so we pass in image.getWidth()/2 and image.getHeight()/2
        rotateValve90Deg = new AffineTransformOp(AffineTransform.getRotateInstance(Math.PI/2, valve1.image.getWidth()/2, (valve1.image.getHeight()/3)*2), AffineTransformOp.TYPE_BILINEAR);
        
        BufferedImage coolingPipeImage = loadImage("images/pipe1.png");
        coolingPipe = new PlantGUIElement(coolingPipeImage, 750, 420, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        
        BufferedImage reactorToCondenserPipeImage = loadImage("images/pipe2.png");
        reactorToCondenserPipe = new PlantGUIElement(reactorToCondenserPipeImage, 128, -5, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        
        BufferedImage condenserToReactorPipeImage = loadImage("images/pipe3.png");
        condenserToReactorPipe = new PlantGUIElement(condenserToReactorPipeImage, 120, 306, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        
        BufferedImage fuelRodsImage = loadImage("images/fuel_rods.png");
        fuelRods = new PlantGUIElement(fuelRodsImage, 58, 190, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        
        BufferedImage controlRodsImage = loadImage("images/control_rods.png");
        controlRods = new PlantGUIElement(controlRodsImage, 58, -20, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        
        BufferedImage turbineImage = loadImage("images/turbine.png");
        turbine = new PlantGUIElement(turbineImage, 675, 45, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        
        BufferedImage turbineHousingImage = loadImage("images/turbineC2.png");
        turbineHousing = new PlantGUIElement(turbineHousingImage, 650, 36, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        turbineHousing2 = new PlantGUIElement(turbineHousingImage, 770, 30, SCALE_AMOUNT+0.1f, X_OFFSET, Y_OFFSET);
    }
    
    private BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("Error loading image resources: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Draws a plantGUIElement to a specified Graphics2D (the window). It takes the X position and Y position
     * and draws the specified image there.
     * @param g                 The screen to draw the image to.
     * @param guiElement        The GUI element to draw.
     */
    public void drawPlantGUIElement(Graphics2D g, PlantGUIElement guiElement) {
        g.drawImage(guiElement.image, guiElement.x(), guiElement.y(), null);
    }
    
    /**
     * Same as plantGUIElement, but applies a transform to the image first. Used for the valve above the
     * condenser.
     * @param g                 The screen to draw to.
     * @param guiElement        The GUI element to draw.
     * @param transform         The transform to apply before the image is drawn.
     */
    public void drawTransformedGUIElement(Graphics2D g, PlantGUIElement guiElement, AffineTransformOp transform) {
        g.drawImage(transform.filter(guiElement.image, null), guiElement.x(), guiElement.y(), null);
    }

    /**
     * This overrides the jPanel's paintComponent method and is called every time the jPanel is repainted. The method
     * is passed a 'Graphics' by the library, which represents the part of the screen which will be drawn on. All
     * draw calls are made on this graphics object.
     * @param _g        The Graphics object to draw to.
     */
    @Override
    public void paintComponent(Graphics _g) {
        // Calls the jPanel method paintComponent() which clears the screen.
        super.paintComponent(_g);
        
        // Casts the Graphics object to a Graphics2D since we are drawing to a 2D screen.
        // This makes some method calls more obvious.
        Graphics2D g = (Graphics2D)_g;

        // Draw all of the plant components.
        drawPlant(g);
        
        // Draw water in the plant and in the pipes.
        drawWater(g);
        
        // Draw steam and bubbles.
        drawEffects(g);
        
        // Draw any text around the screen.
        drawText(g);
    }
    
    /**
     * Draws all of the plant components to the screen.
     */
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
    
    /**
     * Draws all of the water effects to the screen.
     */
    private void drawWater(Graphics2D g) {
        g.setColor(new Color(0.2f, 0.2f, 1.0f, 0.5f));
        
        if(plantStatus.reactorWaterLevel() != null) {
            g.fillRect(X_OFFSET + 5,
                       Y_OFFSET + 62 + inverseWaterHeight(MAX_WATER_HEIGHT, plantStatus.reactorWaterLevel()),
                       173,
                       waterHeight(MAX_WATER_HEIGHT, plantStatus.reactorWaterLevel()));
        }
        
        if(plantStatus.condenserWaterLevel() != null) {
            g.fillRect(X_OFFSET + 670,
                       Y_OFFSET + 338 + inverseWaterHeight(MAX_WATER_HEIGHT, plantStatus.condenserWaterLevel()),
                       173,
                       waterHeight(MAX_WATER_HEIGHT, plantStatus.condenserWaterLevel()));
        }
        
        g.setColor(Color.BLACK);
    }
    
    private int waterHeight(int maxWaterHeight, Percentage waterLevel) {
        return (int)(maxWaterHeight * waterLevel.ratio());
    }
    
    private int inverseWaterHeight(int maxWaterHeight, Percentage waterLevel) {
        return (int)(maxWaterHeight * (1.0f - waterLevel.ratio()));
    }
    
    /**
     * Draws any effects e.g. bubbles or steam.
     */
    private void drawEffects(Graphics2D g) {
        
    }
    
    /**
     * Draws any text to the screen.
     */
    private void drawText(Graphics2D g) {
        int textX = 50;
        int textY = 100;
        
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
