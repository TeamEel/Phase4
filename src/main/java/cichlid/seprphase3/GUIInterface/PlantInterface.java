package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.Simulator.CannotRepairException;
import cichlid.seprphase3.Simulator.GameManager;
import cichlid.seprphase3.Simulator.KeyNotFoundException;
import cichlid.seprphase3.Simulator.PlantController;
import cichlid.seprphase3.Simulator.PlantStatus;
import cichlid.seprphase3.Simulator.Pump;
import cichlid.seprphase3.Simulator.SoftwareFailure;
import cichlid.seprphase3.Utilities.Percentage;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * This is the main interface which will be shown during the game. It is responsible for drawing the representation of
 * the plant to the screen.
 */
public class PlantInterface extends BaseInterface implements MouseListener {

    // These allow access to the plant's methods.
    private PlantController plantController;
    private PlantStatus plantStatus;
    private GameManager gameManager;
    // This is the GUIWindow the interface is being shown in.
    private GUIWindow parent;
    // Misc UI elements.
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
    private ValveControl valve1;
    private AnimatedPlantGUIElement valve2;
    // Pipes.
    private PlantGUIElement coolingPipe;
    private PlantGUIElement reactorToCondenserPipe;
    private PlantGUIElement condenserToReactorPipe;
    // Reactor elements.
    private Rectangle controlRodDownButton;
    private Rectangle controlRodUpButton;
    private PlantGUIElement fuelRods;
    private PlantGUIElement controlRods;
    private PlantGUIElement rodGlow;
    // Turbine.elements.
    private AnimatedPlantGUIElement turbineLeft;
    private PlantGUIElement turbineMiddle;
    private AnimatedPlantGUIElement turbineRight;
    private PlantGUIElement turbineHousing;
    private PlantGUIElement turbineHousing2;
    // Computer elements.
    private PlantGUIElement computer;
    private Rectangle debugButton;
    private Rectangle saveButton;
    private Rectangle quitButton;
    // Font for displaying information about the game.
    private Font gameFont;
    private Font scoreFont;
    // Misc pixel values for setting water height and control rod height.
    private final int MAX_WATER_HEIGHT = 275;
    private final int INITIAL_CONTROL_ROD_HEIGHT = -230;
    private final int CONTROL_ROD_HEIGHT = 120;
    // The global offset to apply to power plant components.
    private final int X_OFFSET = 300;
    private final int Y_OFFSET = 100;
    // The global scale applied to images in the plant to make them the right size on the screen.
    private final float SCALE_AMOUNT = 0.6f;
    // Number of remaining ticks to make the saved game button green to show the user the game has been saved.
    private int savedGameTicks = 0;
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
    public PlantInterface(GUIWindow parent, PlantController plantController, PlantStatus plantStatus,
                          GameManager gameManager) {
        this.plantController = plantController;
        this.plantStatus = plantStatus;
        this.gameManager = gameManager;

        this.parent = parent;
        
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
        BufferedImage backgroundImage = ImageUtils.loadImage("background.png");
        plantBackground = new PlantGUIElement(backgroundImage, 0, 0, 1.0f);

        BufferedImage logoImage = ImageUtils.loadImage("logo.png");
        logo = new PlantGUIElement(logoImage, 20, 20, 0.5f);


        // Reactor and Condenser
        BufferedImage containerImage = ImageUtils.loadImage("container.png");
        reactor = new PlantGUIElement(containerImage, X_OFFSET + 0, Y_OFFSET + 0, SCALE_AMOUNT + 0.3f);
        condenser = new PlantGUIElement(containerImage, X_OFFSET + 665, Y_OFFSET + 275, SCALE_AMOUNT + 0.3f);


        // Static Pump Images
        BufferedImage pumpImage = ImageUtils.loadImage("pump.png");
        pump1 = new PlantGUIElement(pumpImage, X_OFFSET + 572, Y_OFFSET + 535, SCALE_AMOUNT);
        coolingPump = new PlantGUIElement(pumpImage, X_OFFSET + 915, Y_OFFSET + 545, SCALE_AMOUNT);


        // Animated Pump Images
        pump1Rotors = new AnimatedPlantGUIElement(true, "animations/workingpump", "animations/startpump",
                                                  "animations/stoppump", X_OFFSET + 598, Y_OFFSET + 567, SCALE_AMOUNT);
        coolingPumpRotors = new AnimatedPlantGUIElement(true, "animations/workingpump", "animations/startpump",
                                                        "animations/stoppump", X_OFFSET + 941, Y_OFFSET + 577,
                                                        SCALE_AMOUNT);
        pump1Rotors.setAnimation(PlantAnimationType.ON);
        coolingPumpRotors.setAnimation(PlantAnimationType.ON);

        valve1 = new ValveControl(plantStatus, "reactorToTurbine",
                                  X_OFFSET + 307, Y_OFFSET - 51, SCALE_AMOUNT + 0.1f);

        // This sets up the transform used to rotate the valve by 90 degrees.
        // Uses getRotateInstance, which takes an origin. The origin is the middle of the image,
        // so we pass in image.getWidth()/2 and image.getHeight()/2
        rotateValve90Deg = new AffineTransformOp(
                AffineTransform.getRotateInstance(
                Math.PI / 2, // Rotate by 90 degrees
                valve1.getImage().getWidth() / 2, // Rotate round this X point
                (valve1.getImage().getHeight() / 3) * 2), // Rotate round this Y point
                AffineTransformOp.TYPE_BILINEAR // Use bilinear filtering to reconstruct pixels
                );

        valve2 =
        new AnimatedPlantGUIElement(false, "animations/closevalve", "animations/closevalve",
                                    "animations/openvalve", X_OFFSET + 761, Y_OFFSET + 170, SCALE_AMOUNT + 0.1f,
                                    rotateValve90Deg);


        // Pipes
        BufferedImage coolingPipeImage = ImageUtils.loadImage("coolingPipe.png");
        coolingPipe = new PlantGUIElement(coolingPipeImage, X_OFFSET + 750, Y_OFFSET + 450, SCALE_AMOUNT);

        BufferedImage reactorToCondenserPipeImage = ImageUtils.loadImage("reactorToCondenser.png");
        reactorToCondenserPipe = new PlantGUIElement(reactorToCondenserPipeImage, X_OFFSET + 137, Y_OFFSET - 2,
                                                     SCALE_AMOUNT);

        BufferedImage condenserToReactorPipeImage = ImageUtils.loadImage("condenserToReactor.png");
        condenserToReactorPipe = new PlantGUIElement(condenserToReactorPipeImage, X_OFFSET + 154, Y_OFFSET + 334,
                                                     SCALE_AMOUNT + 0.3f);


        // Fuel rods and glow
        BufferedImage fuelRodsImage = ImageUtils.loadImage("fuel_rods.png");
        fuelRods = new PlantGUIElement(fuelRodsImage, X_OFFSET + 63, Y_OFFSET + 216, SCALE_AMOUNT);

        BufferedImage controlRodsImage = ImageUtils.loadImage("control_rods.png");
        controlRods = new PlantGUIElement(controlRodsImage, X_OFFSET + 65, Y_OFFSET - 230, SCALE_AMOUNT + 0.2f);

        BufferedImage glowImage = ImageUtils.loadImage("glow.png");
        rodGlow = new PlantGUIElement(glowImage, X_OFFSET + 47, Y_OFFSET + 206, SCALE_AMOUNT + 0.2f);

        controlRodUpButton = new Rectangle(120, 180, 100, 30);
        controlRodDownButton = new Rectangle(120, 230, 100, 30);


        // Turbine
        turbineLeft = new AnimatedPlantGUIElement(true, "animations/leftturbine/on", "animations/leftturbine/start",
                                                  "animations/leftturbine/stop", X_OFFSET + 680, Y_OFFSET + 67,
                                                  SCALE_AMOUNT + 0.3f);

        BufferedImage turbineMiddleImage = ImageUtils.loadImage("turbine_middle.png");
        turbineMiddle = new PlantGUIElement(turbineMiddleImage, X_OFFSET + 720, Y_OFFSET + 95, SCALE_AMOUNT + 0.5f);

        turbineRight = new AnimatedPlantGUIElement(true, "animations/rightturbine/on", "animations/rightturbine/start",
                                                   "animations/rightturbine/stop", X_OFFSET + 810, Y_OFFSET + 60,
                                                   SCALE_AMOUNT + 0.3f);

        BufferedImage turbineHousingImage = ImageUtils.loadImage("turbineC2.png");
        turbineHousing = new PlantGUIElement(turbineHousingImage, X_OFFSET + 650, Y_OFFSET + 36, SCALE_AMOUNT + 0.3f);
        turbineHousing2 = new PlantGUIElement(turbineHousingImage, X_OFFSET + 770, Y_OFFSET + 36, SCALE_AMOUNT + 0.3f);


        // Computer
        BufferedImage computerImage = ImageUtils.loadImage("computer.png");
        computer = new PlantGUIElement(computerImage, 70, 480, SCALE_AMOUNT + 0.2f);
        debugButton = new Rectangle(100, 640, 60, 20);
        saveButton = new Rectangle(280, 640, 75, 20);
        quitButton = new Rectangle(200, 640, 45, 20);


        // Fonts
        gameFont = new Font("Impact", Font.PLAIN, 15);
        scoreFont = new Font("Impact", Font.PLAIN, 30);
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

        // Update locations of components and failure status of the turbine.
        updateComponents();

        // Draw the background.
        drawBackgroundAndLogo(g);

        // Draw all of the plant components.
        drawPlant(g);

        // Draw water in the plant and in the pipes.
        drawWater(g);

        // Draw steam and bubbles.
        // TODO: this?
        drawEffects(g);

        // Draw left hand side information.
        drawInfo(g);

        // Draw information about each component.
        drawComponentStatuses(g);
    }

    /**
     * Updates the status of the turbine based on the valves. Also sets the control rods to their correct height.
     */
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

        if (plantStatus.controlRodPosition() != null) {
            controlRods.setY(Y_OFFSET + (int)(INITIAL_CONTROL_ROD_HEIGHT + (1.0 - plantStatus.controlRodPosition()
                                                                            .ratio()) * CONTROL_ROD_HEIGHT));
        } else {
            controlRods.setY(Y_OFFSET + (int)(INITIAL_CONTROL_ROD_HEIGHT * 5));
        }

    }

    /**
     * Draws the background and the logo.
     *
     * @param g
     */
    private void drawBackgroundAndLogo(Graphics2D g) {
        plantBackground.draw(g);
        logo.draw(g);
    }

    /**
     * Draws all of the plant components to the screen. Some pass their failure status to determine if they should be
     * drawn tinted red.
     */
    private void drawPlant(Graphics2D g) {
        reactor.draw(g, failureState("reactor"));
        condenser.draw(g, failureState("condenser"));

        reactorToCondenserPipe.draw(g);
        condenserToReactorPipe.draw(g);
        coolingPipe.draw(g);

        pump1.draw(g, failureState("pump1"));
        coolingPump.draw(g, failureState("coolingPump"));

        pump1Rotors.draw(g, failureState("pump1"));
        coolingPumpRotors.draw(g, failureState("coolingPump"));

        turbineHousing.draw(g);
        turbineHousing2.draw(g);

        turbineMiddle.draw(g, failureState("turbine"));
        turbineLeft.draw(g, failureState("turbine"));
        turbineRight.draw(g, failureState("turbine"));

        valve1.draw(g);
        valve2.draw(g);

        controlRods.draw(g);
        fuelRods.draw(g);
        rodGlow.draw(g);
    }

    /**
     * Draws all of the water effects to the screen. Most of these are just slightly transparent blue rectangles.
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

        if (((Pump)plantStatus.componentList().get("pump1")).getStatus() && !((Pump)plantStatus.componentList().get(
                                                                              "pump1")).hasFailed()) {
            g.fillRect(X_OFFSET + 158,
                       Y_OFFSET + 333,
                       18,
                       219);

            g.fillRect(X_OFFSET + 212,
                       Y_OFFSET + 570,
                       367,
                       14);

        }

        if (((Pump)plantStatus.componentList().get("coolingPump")).getStatus() && !((Pump)plantStatus.componentList()
                                                                                    .get("coolingPump")).hasFailed()) {
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

    /**
     * Helper functions for determining the water height of the reactor.
     *
     * @param maxWaterHeight The max water height in pixels.
     * @param waterLevel     The current water level.
     *
     * @return
     */
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

    /**
     * Helper function for drawing bordered rectangles for the plant information
     *
     * @param g      The screen to draw to.
     * @param x      The X location.
     * @param y      The Y location.
     * @param width  The width.
     * @param height The height.
     */
    private void drawBorderRect(Graphics2D g, int x, int y, int width, int height) {
        // Set the color to a predefined grey and draw the box.
        g.setColor(new Color(0.4f, 0.4f, 0.4f, 0.8f));
        g.fillRect(x, y, width, height);

        // Draw the border.
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    /**
     * Draw all of the left border information.
     *
     * @param g
     */
    private void drawInfo(Graphics2D g) {
        g.setFont(gameFont);


        // Damage information.
        drawBorderRect(g, 80, 310, 190, 130);
        g.drawString("Damage", 140, 330);
        g.drawString("Condenser: " + plantStatus.componentList().get("condenser").wear(), 90, 360);
        g.drawString("Pump 1: " + plantStatus.componentList().get("pump1").wear(), 90, 380);
        g.drawString("Cooling Pump: " + plantStatus.componentList().get("coolingPump").wear(), 90, 400);
        g.drawString("Turbine: " + plantStatus.componentList().get("turbine").wear(), 90, 420);

        // Computer.
        computer.draw(g);

        // Computer Screen.

        // SavedGameTicks used to provide an indication the game has been saved
        // by setting the button to green for a certain number of game ticks.
        if (savedGameTicks > 0) {
            g.setColor(Color.GREEN);
            savedGameTicks--;
        } else {
            g.setColor(Color.RED);
        }

        // Draw the save button.
        g.fillRect(saveButton.x, saveButton.y, saveButton.width, saveButton.height);
        g.setColor(Color.BLACK);
        g.drawString("SAVE GAME", saveButton.x + 5, saveButton.y + 15);

        // Draw the quit button.
        g.setColor(Color.CYAN);
        g.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
        g.setColor(Color.BLACK);
        g.drawString("QUIT", quitButton.x + 10, quitButton.y + 15);

        // Show the software failures.
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
     * Draws all of the plant status information to the screen.
     */
    private void drawComponentStatuses(Graphics2D g) {

        // Set antialialising for prettier text.
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(gameFont);

        // Reactor information.
        drawBorderRect(g, 500, 230, 170, 135);
        g.drawString("Pressure: " + plantStatus.reactorPressure(), 510, 250);
        g.drawString("Temperature: " + plantStatus.reactorTemperature(), 510, 275);
        g.drawString("Water Level: " + plantStatus.reactorWaterLevel(), 510, 300);
        g.drawString("Control rods: " + plantStatus.controlRodPosition(), 510, 325);
        if (plantStatus.quencherUsed()) {
            g.drawString("Quencher used: YES", 510, 350);
        } else {
            g.drawString("Quencher used: NO", 510, 350);
        }

        // Button text for rod up and rod down.
        g.setColor(Color.CYAN);
        g.fillRect(controlRodUpButton.x, controlRodUpButton.y, controlRodUpButton.width, controlRodUpButton.height);
        g.fillRect(controlRodDownButton.x, controlRodDownButton.y, controlRodDownButton.width,
                   controlRodDownButton.height);
        g.setColor(Color.BLACK);
        g.drawString("Rod Up", controlRodUpButton.x + 20, controlRodUpButton.y + 20);
        g.drawString("Rod Down", controlRodDownButton.x + 20, controlRodDownButton.y + 20);

        // Condenser information.
        drawBorderRect(g, 1165, 460, 170, 80);
        g.drawString("Pressure: " + plantStatus.condenserPressure(), 1175, 480);
        g.drawString("Temperature: " + plantStatus.condenserTemperature(), 1175, 505);
        g.drawString("Water Level: " + plantStatus.condenserWaterLevel(), 1175, 530);

        // Valve 1 information.
        drawBorderRect(g, 575, 130, 110, 30);
        if (plantStatus.getReactorToTurbine()) {
            g.drawString("Valve 1: OPEN", 585, 150);
        } else {
            g.drawString("Valve 1: CLOSED", 585, 150);
        }

        // Valve 2 information.
        drawBorderRect(g, 940, 305, 110, 30);
        if (plantStatus.getTurbineToCondenser()) {
            g.drawString("Valve 2: OPEN", 950, 325);
        } else {
            g.drawString("Valve 2: CLOSED", 950, 325);
        }

        // Pump 1 information.
        drawBorderRect(g, 860, 600, 90, 30);
        if (((Pump)plantStatus.componentList().get("pump1")).getStatus()) {
            g.drawString("Pump 1: ON", 870, 620);
        } else {
            g.drawString("Pump 1: OFF", 870, 620);
        }

        // Cooling Pump information.
        drawBorderRect(g, 1180, 610, 130, 30);
        if (((Pump)plantStatus.componentList().get("coolingPump")).getStatus()) {
            g.drawString("Cooling Pump: ON", 1190, 630);
        } else {
            g.drawString("Cooling Pump: OFF", 1190, 630);
        }

        // Score.
        drawBorderRect(g, 1080, 10, 260, 50);
        g.setFont(scoreFont);
        g.setColor(Color.decode("#000000"));
        g.drawString("Score: " + plantStatus.energyGenerated(), 1100, 50);
        g.setFont(gameFont);
        g.setColor(Color.BLACK);
    }

    /**
     * Called when the mouse is clicked on the screen by the MouseListener. The method is passed a MouseEvent 'click'
     * which contains a point on the screen where the mouse was clicked. This can be checked against each component to
     * see which component was clicked on, and the appropriate action is taken.
     *
     * @param click The event information for the mouse click.
     */
    @Override
    public void mouseClicked(MouseEvent click) {

        // If the left mouse button is clicked, check if it was clicked on a component.
        if (leftClick(click)) {

            // If a pump was clicked, change its status and animate it changing.
            if (pump1.clicked(click)) {
                if (!(plantStatus.getSoftwareFailure() == SoftwareFailure.pumpStateChange)) {
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

            // If a pump was clicked, change its status and animate it changing.
            if (coolingPump.clicked(click)) {
                if (!(plantStatus.getSoftwareFailure() == SoftwareFailure.pumpStateChange)) {
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

            valve1.handleClick(click);

            // If a valve was clicked, change its status and animate it changing.
            if (valve2.clicked(click)) {
                if (!(plantStatus.getSoftwareFailure() == SoftwareFailure.valveStateChange)) {
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

            // If the control rod up button was clicked, move the control rods up.
            if (clicked(controlRodUpButton, click)) {
                if (plantStatus.controlRodPosition().points() < 100 && !(plantStatus.getSoftwareFailure() ==
                                                                         SoftwareFailure.rodStateChange)) {
                    plantController.moveControlRods(plantStatus.controlRodPosition().plus(new Percentage(10.0)));
                }
            }

            // If the control rod down button was clicked, move the control rods down.
            if (clicked(controlRodDownButton, click)) {
                if (plantStatus.controlRodPosition().points() > 0 && !(plantStatus.getSoftwareFailure() ==
                                                                       SoftwareFailure.rodStateChange)) {
                    plantController.moveControlRods(plantStatus.controlRodPosition().minus(new Percentage(10.0)));
                }
            }

            // If the debug button was clicked, repair the software.
            if (clicked(debugButton, click)) {
                plantController.repairSoftware();
            }

            // If the quit button was clicked, quit to the menu.
            if (clicked(quitButton, click)) {
                parent.state = GameState.NotStarted;
                parent.showMenu();
            }

            // If the save button was clicked, save the game.
            if (clicked(saveButton, click)) {
                try {
                    gameManager.saveGame();

                    // Set the button to green for 10 game ticks to acknowledge the save.
                    savedGameTicks = 10;
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(PlantInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // The right mouse button was clicked, check if it was clicked on a component.
        } else if (rightClick(click)) {

            // If the mouse was clicked on reactor, quench it.

            if (reactor.clicked(click)) {
                plantController.quenchReactor();
            }

            // If the mouse was clicked on any other component, repair it.

            if (condenser.clicked(click)) {
                try {
                    plantController.repairCondenser();
                } catch (CannotRepairException ex) {
                }
            }

            if (pump1.clicked(click)) {
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

            if (coolingPump.clicked(click)) {
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

            if (turbineLeft.clicked(click) || turbineMiddle.clicked(click) || turbineRight.clicked(click)) {
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

    private boolean failureState(String id) {
        return plantStatus.componentList().get(id).hasFailed();
    }
}
