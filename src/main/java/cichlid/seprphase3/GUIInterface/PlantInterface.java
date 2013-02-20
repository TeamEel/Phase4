package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.GameOverException;
import cichlid.seprphase3.Simulator.CannotRepairException;
import cichlid.seprphase3.Simulator.GameManager;
import cichlid.seprphase3.Simulator.KeyNotFoundException;
import cichlid.seprphase3.Simulator.PlantController;
import cichlid.seprphase3.Simulator.PlantStatus;
import cichlid.seprphase3.Utilities.Percentage;
import cichlid.seprphase3.Simulator.Pump;
import cichlid.seprphase3.Simulator.SoftwareFailure;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This is the main interface which will be shown during the game. It is responsible for drawing the representation of
 * the plant to the screen.
 */
public class PlantInterface extends JPanel implements MouseListener {

    // These allow access to the plant's methods.
    private PlantController plantController;
    private PlantStatus plantStatus;
    private GameManager gameManager;
    
    private PlantGUIElement plantBackground;
    private PlantGUIElement logo;
    // These represent parts of the plant which will be drawn to the screen.
    // Each has a location and an image which is set up in setupComponents().
    // Water tanks - the reactor and condenser.
    private PlantGUIElement reactor;
    private PlantGUIElement condenser;
    // The pumps (static images) and the rotors (animations).
    private PlantGUIElement pump1;
    private PlantGUIElement coolingPump;
    private AnimatedPlantGUIElement pump1Rotors;
    private AnimatedPlantGUIElement coolingPumpRotors;
    // Valves.
    private AnimatedPlantGUIElement valve1;
    private AnimatedPlantGUIElement valve2;
    // Pipes.
    private PlantGUIElement coolingPipe;
    private PlantGUIElement reactorToCondenserPipe;
    private PlantGUIElement condenserToReactorPipe;
    // Reactor elements.
    private PlantGUIElement fuelRods;
    private PlantGUIElement controlRods;
    private PlantGUIElement rodGlow;
    // Turbine.
    private AnimatedPlantGUIElement turbineLeft;
    private PlantGUIElement turbineMiddle;
    private AnimatedPlantGUIElement turbineRight;
    private PlantGUIElement turbineHousing;
    private PlantGUIElement turbineHousing2;
    
    // Computer
    private PlantGUIElement computer;
    private Rectangle debugButton;
    private Rectangle saveButton;
    
    // Font for displaying information about the game.
    private Font gameFont;
    private Font scoreFont;
    // The height of the water in the Reactor and Condenser in pixels.
    private final int MAX_WATER_HEIGHT = 275;
    // The global scale applied to images in the plant to make them the right size on the screen.
    private final int X_OFFSET = 300;
    private final int Y_OFFSET = 100;
    private final float SCALE_AMOUNT = 0.6f;
    // An AffineTransform is one which preserves straight lines. This is used to rotate the valve image
    // by 90 degrees so the valve above the condenser can be drawn in the right orientation.
    AffineTransformOp rotateValve90Deg;

    /**
     * The interface is set up with references to the plant classes it needs for getting and setting information.
     *
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

        // Add a mouse listener to listen to mouse events (so we can click things!)
        addMouseListener(this);
    }

    /**
     * Gives all of the plant components the right images and locations on the screen. Each component has the form:
     * BufferedImage image = loadImage("images/image.png"); component = new PlantGUIElement( image, location x, location
     * y, scale amount);
     *
     */
    private void setupComponents() {
        
        // Background.
        BufferedImage backgroundImage = ImageUtils.loadImage("images/background.png");
        plantBackground = new PlantGUIElement(backgroundImage, 0, 0, 1.0f, 0, 0);
        
        BufferedImage logoImage = ImageUtils.loadImage("images/logo.png");
        logo = new PlantGUIElement(logoImage, 20, 20, 0.5f, 0, 0);

        
        // Reactor and Condenser
        BufferedImage containerImage = ImageUtils.loadImage("images/container.png");
        reactor = new PlantGUIElement(containerImage, 0, 0, 0.9f, X_OFFSET, Y_OFFSET);
        condenser = new PlantGUIElement(containerImage, 665, 275, 0.9f, X_OFFSET, Y_OFFSET);
        
        
        // Static Pump Images
        BufferedImage pumpImage = ImageUtils.loadImage("images/pump.png");
        pump1 = new PlantGUIElement(pumpImage, 572, 535, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        coolingPump = new PlantGUIElement(pumpImage, 915, 545, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);

        
        // Animated Pump Images
        pump1Rotors = new AnimatedPlantGUIElement(false, "animations/workingpump", "animations/startpump", "animations/stoppump", 598, 567, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        coolingPumpRotors = new AnimatedPlantGUIElement(false, "animations/workingpump", "animations/startpump", "animations/stoppump", 941, 577, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        pump1Rotors.setAnimation(PlantAnimationType.ON);
        coolingPumpRotors.setAnimation(PlantAnimationType.ON);

        
        // Animated Valves
        valve1 = new AnimatedPlantGUIElement(true, "animations/closevalve", "animations/closevalve", "animations/openvalve", 307, -51, SCALE_AMOUNT + 0.1f, X_OFFSET, Y_OFFSET);

        // This sets up the transform used to rotate the valve by 90 degrees.
        // Uses getRotateInstance, which takes an origin. The origin is the middle of the image,
        // so we pass in image.getWidth()/2 and image.getHeight()/2
        rotateValve90Deg = new AffineTransformOp(
                AffineTransform.getRotateInstance(
                Math.PI / 2, // Rotate by 90 degrees
                valve1.image.getWidth() / 2, // Rotate round this X point
                (valve1.image.getHeight() / 3) * 2), // Rotate round this Y point
                AffineTransformOp.TYPE_BILINEAR // Use bilinear filtering to reconstruct pixels
                );

        valve2 = new AnimatedPlantGUIElement(true, "animations/closevalve", "animations/closevalve", "animations/openvalve", 761, 170, SCALE_AMOUNT + 0.1f, X_OFFSET, Y_OFFSET, rotateValve90Deg);

        
        // Pipes
        BufferedImage coolingPipeImage = ImageUtils.loadImage("images/coolingPipe.png");
        coolingPipe = new PlantGUIElement(coolingPipeImage, 750, 450, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);

        BufferedImage reactorToCondenserPipeImage = ImageUtils.loadImage("images/reactorToCondenser.png");
        reactorToCondenserPipe = new PlantGUIElement(reactorToCondenserPipeImage, 137, -2, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);

        BufferedImage condenserToReactorPipeImage = ImageUtils.loadImage("images/condenserToReactor.png");
        condenserToReactorPipe = new PlantGUIElement(condenserToReactorPipeImage, 154, 334, SCALE_AMOUNT + 0.3f, X_OFFSET, Y_OFFSET);

        
        // Fuel rods and glow
        BufferedImage fuelRodsImage = ImageUtils.loadImage("images/fuel_rods.png");
        fuelRods = new PlantGUIElement(fuelRodsImage, 63, 216, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);

        BufferedImage controlRodsImage = ImageUtils.loadImage("images/control_rods.png");
        controlRods = new PlantGUIElement(controlRodsImage, 65, -230, SCALE_AMOUNT + 0.2f, X_OFFSET, Y_OFFSET);
        
        BufferedImage glowImage = ImageUtils.loadImage("images/glow.png");
        rodGlow = new PlantGUIElement(glowImage, 47, 206, SCALE_AMOUNT+0.2f, X_OFFSET, Y_OFFSET);

        
        // Turbine
        turbineLeft = new AnimatedPlantGUIElement(false, "animations/leftturbine/on", "animations/leftturbine/start", "animations/leftturbine/stop", 680, 67, SCALE_AMOUNT + 0.3f, X_OFFSET, Y_OFFSET);

        BufferedImage turbineMiddleImage = ImageUtils.loadImage("images/turbine_middle.png");
        turbineMiddle = new PlantGUIElement(turbineMiddleImage, 720, 95, SCALE_AMOUNT + 0.5f, X_OFFSET, Y_OFFSET);

        turbineRight = new AnimatedPlantGUIElement(false, "animations/rightturbine/on", "animations/rightturbine/on", "animations/rightturbine/off", 810, 60, SCALE_AMOUNT + 0.3f, X_OFFSET, Y_OFFSET);

        BufferedImage turbineHousingImage = ImageUtils.loadImage("images/turbineC2.png");
        turbineHousing = new PlantGUIElement(turbineHousingImage, 650, 36, SCALE_AMOUNT + 0.3f, X_OFFSET, Y_OFFSET);
        turbineHousing2 = new PlantGUIElement(turbineHousingImage, 770, 36, SCALE_AMOUNT + 0.3f, X_OFFSET, Y_OFFSET);
        
        BufferedImage computerImage = ImageUtils.loadImage("images/computer.png");
        computer = new PlantGUIElement(computerImage, 70, 480, SCALE_AMOUNT + 0.2f, 0, 0);
        debugButton = new Rectangle(100, 640, 60, 20);
        saveButton = new Rectangle(280, 640, 75, 20);

        
        // Fonts
        gameFont = new Font("Impact", Font.PLAIN, 15);
        scoreFont = new Font("Impact", Font.PLAIN, 30);
    }


    /**
     * Draws a plantGUIElement to a specified Graphics2D (the window). It takes the X position and Y position and draws
     * the specified image there.
     *
     * @param g          The screen to draw the image to.
     * @param guiElement The GUI element to draw.
     */
    public void drawPlantGUIElement(Graphics2D g, PlantGUIElement guiElement, Boolean failed) {
        if (!failed) {
            g.drawImage(guiElement.image, guiElement.x(), guiElement.y(), null);
        } else {
            BufferedImageOp tintFilter = ImageUtils.createTintOp((short)2, (short).5, (short).5);
            BufferedImage tintedImage = tintFilter.filter(guiElement.image, null);
            g.drawImage(tintedImage, guiElement.x(), guiElement.y(), null);
        }
        
    }

    public void drawAnimatedGUIElement(Graphics2D g, AnimatedPlantGUIElement guiElement, Boolean failed) {
        if (!failed) {
            g.drawImage(guiElement.stepImage(), guiElement.x(), guiElement.y(), null);
        } else {
            guiElement.setAnimation(PlantAnimationType.OFF);
            BufferedImageOp tintFilter = ImageUtils.createTintOp((short)1.5, (short).5, (short).5);
            BufferedImage tintedImage = tintFilter.filter(guiElement.getImage(), null);
            g.drawImage(tintedImage, guiElement.x(), guiElement.y(), null);
        }
    }

    /**
     * This overrides the jPanel's paintComponent method and is called every time the jPanel is repainted. The method is
     * passed a 'Graphics' by the library, which represents the part of the screen which will be drawn on. All draw
     * calls are made on this graphics object.
     *
     * @param _g The Graphics object to draw to.
     */
    @Override
    public void paintComponent(Graphics _g) {
        // Calls the jPanel method paintComponent() which clears the screen.
        super.paintComponent(_g);

        // Casts the Graphics object to a Graphics2D since we are drawing to a 2D screen.
        // This makes some method calls more obvious.
        Graphics2D g = (Graphics2D)_g;
        
        // Turn the turbine animation off if it has failed.
        updateComponents();
        
        // Draw the background.
        drawBackgroundAndLogo(g);

        // Draw all of the plant components.
        drawPlant(g);

        // Draw water in the plant and in the pipes.
        drawWater(g);

        // Draw steam and bubbles.
        drawEffects(g);
        
        // Draw left hand side information.
        drawInfo(g);

        // Draw any text around the screen.
        drawText(g);
    }
    
    private void updateComponents() {
        if (plantStatus.getReactorToTurbine() && !plantStatus.componentList().get("turbine").hasFailed()) {
            if (turbineLeft.getCurrentAnimation() != PlantAnimationType.ON) {
                turbineLeft.setAnimation(PlantAnimationType.ON);
            }
            if (turbineRight.getCurrentAnimation() != PlantAnimationType.ON) {
                turbineRight.setAnimation(PlantAnimationType.ON);
            }
        } else {
            if (turbineLeft.getCurrentAnimation() != PlantAnimationType.OFF) {
                turbineLeft.setAnimation(PlantAnimationType.OFF);
            }
            if (turbineRight.getCurrentAnimation() != PlantAnimationType.OFF) {
                turbineRight.setAnimation(PlantAnimationType.OFF);
            }
        }
    }
    
    private void drawBackgroundAndLogo(Graphics2D g) {
        drawPlantGUIElement(g, plantBackground, false);
        drawPlantGUIElement(g, logo, false);
    }

    /**
     * Draws all of the plant components to the screen.
     */
    private void drawPlant(Graphics2D g) {
        drawPlantGUIElement(g, reactor, plantStatus.componentList().get("reactor").hasFailed());
        drawPlantGUIElement(g, condenser, plantStatus.componentList().get("condenser").hasFailed());

        drawPlantGUIElement(g, reactorToCondenserPipe, false);
        drawPlantGUIElement(g, condenserToReactorPipe, false);
        drawPlantGUIElement(g, coolingPipe, false);

        drawPlantGUIElement(g, pump1, plantStatus.componentList().get("pump1").hasFailed());
        drawPlantGUIElement(g, coolingPump, plantStatus.componentList().get("coolingPump").hasFailed());

        drawAnimatedGUIElement(g, pump1Rotors, plantStatus.componentList().get("pump1").hasFailed());
        drawAnimatedGUIElement(g, coolingPumpRotors, plantStatus.componentList().get("coolingPump").hasFailed());

        drawPlantGUIElement(g, turbineHousing, false);
        drawPlantGUIElement(g, turbineHousing2, false);
        drawPlantGUIElement(g, turbineMiddle, plantStatus.componentList().get("turbine").hasFailed());
        drawAnimatedGUIElement(g, turbineLeft, plantStatus.componentList().get("turbine").hasFailed());
        drawAnimatedGUIElement(g, turbineRight, plantStatus.componentList().get("turbine").hasFailed());

        drawAnimatedGUIElement(g, valve1, false);
        drawAnimatedGUIElement(g, valve2, false);

        drawPlantGUIElement(g, controlRods, false);
        drawPlantGUIElement(g, fuelRods, false);
        drawPlantGUIElement(g, rodGlow, false);
    }

    /**
     * Draws all of the water effects to the screen.
     */
    private void drawWater(Graphics2D g) {
        g.setColor(new Color(0.5f, 0.6f, 0.8f, 0.7f));

        if (plantStatus.reactorWaterLevel() != null) {
            g.fillRect(X_OFFSET + 6,
                       Y_OFFSET + 62 + inverseWaterHeight(MAX_WATER_HEIGHT, plantStatus.reactorWaterLevel()),
                       185,
                       waterHeight(MAX_WATER_HEIGHT, plantStatus.reactorWaterLevel()));
        }

        if (plantStatus.condenserWaterLevel() != null) {
            g.fillRect(X_OFFSET + 671,
                       Y_OFFSET + 338 + inverseWaterHeight(MAX_WATER_HEIGHT, plantStatus.condenserWaterLevel()),
                       185,
                       waterHeight(MAX_WATER_HEIGHT, plantStatus.condenserWaterLevel()));
        }
        
        g.fillRect(X_OFFSET + 636,
                   Y_OFFSET + 572,
                   36,
                   13);
        
        g.fillRect(X_OFFSET + 980,
                   Y_OFFSET + 581,
                   90,
                   12);
        
        if (((Pump)plantStatus.componentList().get("pump1")).getStatus() && !((Pump)plantStatus.componentList().get("pump1")).hasFailed()) {
            g.fillRect(X_OFFSET + 158,
                       Y_OFFSET + 333,
                       18,
                       219);
            
            g.fillRect(X_OFFSET + 212,
                       Y_OFFSET + 570,
                       367,
                       14);
            
        }
        
        if (((Pump)plantStatus.componentList().get("coolingPump")).getStatus() && !((Pump)plantStatus.componentList().get("coolingPump")).hasFailed()) {
            g.fillRect(X_OFFSET + 784,
                       Y_OFFSET + 579,
                       138,
                       16);
            
            g.fillRect(X_OFFSET + 785,
                       Y_OFFSET + 539,
                       45,
                       16);
            
            g.fillRect(X_OFFSET + 784,
                       Y_OFFSET + 500,
                       45,
                       16);
            
            g.fillRect(X_OFFSET + 785,
                       Y_OFFSET + 460,
                       320,
                       16);
        }

        g.setColor(Color.BLACK);
    }

    private int waterHeight(int maxWaterHeight, Percentage waterLevel) {
        return (int)Math.floor(maxWaterHeight * waterLevel.ratio());
    }

    private int inverseWaterHeight(int maxWaterHeight, Percentage waterLevel) {
        return (int)Math.floor(maxWaterHeight * (1.0f - waterLevel.ratio()));
    }

    /**
     * Draws any effects e.g. bubbles or steam.
     */
    private void drawEffects(Graphics2D g) {
    }
    
    private void drawBorderRect(Graphics2D g, int x, int y, int width, int height) {
        g.setColor(new Color(0.4f, 0.4f, 0.4f, 0.8f));
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
    
    private void drawBorderRect(Graphics2D g, int x, int y, int width, int height, Color color, Color borderColor) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(borderColor);
        g.drawRect(x, y, width, height);
    }
    
    private void drawInfo(Graphics2D g) {
        g.setFont(gameFont);
        drawBorderRect(g, 80, 310 , 190, 130);
        g.drawString("Damage", 140, 330);
        g.drawString("Condenser: " + plantStatus.componentList().get("condenser").wear(), 90, 360);
        g.drawString("Pump 1: " + plantStatus.componentList().get("pump1").wear(), 90, 380);
        g.drawString("Cooling Pump: " + plantStatus.componentList().get("coolingPump").wear(), 90, 400);
        g.drawString("Turbine: " + plantStatus.componentList().get("turbine").wear(), 90, 420);
        
        
        drawPlantGUIElement(g, computer, false);
        
        
        g.setColor(Color.GREEN);
        g.fillRect(saveButton.x, saveButton.y, saveButton.width, saveButton.height);
        g.setColor(Color.BLACK);
        g.drawString("SAVE GAME", saveButton.x + 5, saveButton.y + 15);

        
        if (plantStatus.getSoftwareFailure() != SoftwareFailure.None) {
            g.setColor(Color.GREEN);
            g.drawString("> error", 120, 540);
            g.drawString("> segfault 0xFF3190E", 120, 560);
            g.drawString("  # stacktrace:", 120, 580);
            g.drawString("    # " + plantStatus.getSoftwareFailure(), 120, 600);
            
            g.fillRect(debugButton.x, debugButton.y, debugButton.width, debugButton.height);
            
            g.setColor(Color.BLACK);
            
            g.drawString("DEBUG", debugButton.x + 15, debugButton.y + 15);
        }
    }

    /**
     * Draws any text to the screen.
     */
    private void drawText(Graphics2D g) {

        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(gameFont);

        drawBorderRect(g, 500, 230, 170, 110);
        g.drawString("Pressure: " + plantStatus.reactorPressure(), 510, 250);
        g.drawString("Temperature: " + plantStatus.reactorTemperature(), 510, 275);
        g.drawString("Water Level: " + plantStatus.reactorWaterLevel(), 510, 300);
        g.drawString("Control rods: " + plantStatus.controlRodPosition(), 510, 325);

        drawBorderRect(g, 1165, 460, 170, 80);
        g.drawString("Pressure: " + plantStatus.condenserPressure(), 1175, 480);
        g.drawString("Temperature: " + plantStatus.condenserTemperature(), 1175, 505);
        g.drawString("Water Level: " + plantStatus.condenserWaterLevel(), 1175, 530);

        drawBorderRect(g, 575, 130, 110, 30);
        if(plantStatus.getReactorToTurbine()) {
            g.drawString("Valve 1: OPEN", 585, 150);
        } else {
            g.drawString("Valve 1: CLOSED", 585, 150);
        }
        
        drawBorderRect(g, 940, 305, 110, 30);
        if(plantStatus.getTurbineToCondenser()) {
            g.drawString("Valve 2: OPEN", 950, 325);
        } else {
            g.drawString("Valve 2: CLOSED", 950, 325);
        }
        
        drawBorderRect(g, 860, 600, 90, 30);
        if(((Pump)plantStatus.componentList().get("pump1")).getStatus()) {
            g.drawString("Pump 1: ON", 870, 620);
        } else {
            g.drawString("Pump 1: OFF", 870, 620);
        }
        
        drawBorderRect(g, 1180, 610, 130, 30);
        if(((Pump)plantStatus.componentList().get("coolingPump")).getStatus()) {
            g.drawString("Cooling Pump: ON", 1190, 630);
        } else {
            g.drawString("Cooling Pump: OFF", 1190, 630);
        }
        
        drawBorderRect(g, 1080, 10, 260, 50);
        g.setFont(scoreFont); g.setColor(Color.decode("#000000"));
        g.drawString("Score: " + plantStatus.energyGenerated(), 1100, 50);
        g.setFont(gameFont); g.setColor(Color.BLACK);
    }

    /**
     * Called when the mouse is clicked on the screen by the MouseListener.
     * The method is passed a MouseEvent 'click' which contains a point on the screen where the mouse was clicked.
     * This can be checked against each component to see which component was clicked on, and the appropriate action
     * is taken.
     * @param click    The event information for the mouse click.
     */
    @Override
    public void mouseClicked(MouseEvent click) {

        // If the left mouse button is clicked, check if it was clicked on a component.
        if (SwingUtilities.isLeftMouseButton(click)) {
            
            if (pump1.location.contains(click.getPoint())) {
                if(!(plantStatus.getSoftwareFailure() == SoftwareFailure.pumpStateChange)) {
                    Pump cPump1 = (Pump)plantStatus.componentList().get("pump1");
                    boolean state = cPump1.getStatus();

                    if (state) {
                        cPump1.setStatus(false);
                        pump1Rotors.setAnimation(PlantAnimationType.TURNINGOFF);
                    } else {
                        cPump1.setStatus(true);
                        pump1Rotors.setAnimation(PlantAnimationType.TURNINGON);
                    }
                }
            }

            if (coolingPump.location.contains(click.getPoint())) {
                if(!(plantStatus.getSoftwareFailure() == SoftwareFailure.pumpStateChange)) {
                    Pump cCoolingPump = (Pump)plantStatus.componentList().get("coolingPump");
                    boolean state = cCoolingPump.getStatus();

                    if (state) {
                        cCoolingPump.setStatus(false);
                        coolingPumpRotors.setAnimation(PlantAnimationType.TURNINGOFF);
                    } else {
                        cCoolingPump.setStatus(true);
                        coolingPumpRotors.setAnimation(PlantAnimationType.TURNINGON);
                    }
                }
            }

            if (valve1.location.contains(click.getPoint())) {
                if(!(plantStatus.getSoftwareFailure() == SoftwareFailure.valveStateChange)) {
                    boolean state = plantStatus.connectionList().get("reactorToTurbine").getOpen();

                    if (state) {
                        plantStatus.connectionList().get("reactorToTurbine").setOpen(false);
                        valve1.setAnimation(PlantAnimationType.TURNINGON);
                    } else {
                        plantStatus.connectionList().get("reactorToTurbine").setOpen(true);
                        valve1.setAnimation(PlantAnimationType.TURNINGOFF);
                    }
                }
            }

            if (valve2.location.contains(click.getPoint())) {
                if(!(plantStatus.getSoftwareFailure() == SoftwareFailure.valveStateChange)) {
                    boolean state = plantStatus.connectionList().get("turbineToCondenser").getOpen();

                    if (state) {
                        plantStatus.connectionList().get("turbineToCondenser").setOpen(false);
                        valve2.setAnimation(PlantAnimationType.TURNINGON);
                    } else {
                        plantStatus.connectionList().get("turbineToCondenser").setOpen(true);
                        valve2.setAnimation(PlantAnimationType.TURNINGOFF);
                    }
                }
            }
            
                        
            if (debugButton.contains(click.getPoint())) {
                plantController.repairSoftware();
            }
            
            if (saveButton.contains(click.getPoint())) {
                try {
                    gameManager.saveGame();
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(PlantInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } else if (SwingUtilities.isRightMouseButton(click)) {
            
            if (condenser.location.contains(click.getPoint())) {
                try {
                    plantController.repairCondenser();
                } catch (CannotRepairException ex) {
                }
            }

            if (pump1.location.contains(click.getPoint())) {
                try {
                    plantController.repairPump(1);

                    if (((Pump)plantStatus.componentList().get("pump1")).getStatus()) {
                        pump1Rotors.setAnimation(PlantAnimationType.ON);
                    } else {
                        pump1Rotors.setAnimation(PlantAnimationType.OFF);
                    }
                    
                } catch (KeyNotFoundException ex) {
                } catch (CannotRepairException ex) {
                }
            }

            if (coolingPump.location.contains(click.getPoint())) {
                try {
                    plantController.repairPump(2);
                    
                    if (((Pump)plantStatus.componentList().get("coolingPump")).getStatus()) {
                        coolingPumpRotors.setAnimation(PlantAnimationType.ON);
                    } else {
                        coolingPumpRotors.setAnimation(PlantAnimationType.OFF);
                    }
                } catch (KeyNotFoundException ex) {
                } catch (CannotRepairException ex) {
                }
            }

            if ((turbineLeft.location.contains(click.getPoint()) || turbineRight.location.contains(click.getPoint()))) {
                try {
                    plantController.repairTurbine();
                } catch (CannotRepairException ex) {
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
