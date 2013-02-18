package cichlid.seprphase3.GUIInterface;

import cichlid.seprphase3.GameOverException;
import cichlid.seprphase3.Simulator.CannotRepairException;
import cichlid.seprphase3.Simulator.GameManager;
import cichlid.seprphase3.Simulator.KeyNotFoundException;
import cichlid.seprphase3.Simulator.PlantController;
import cichlid.seprphase3.Simulator.PlantStatus;
import cichlid.seprphase3.Utilities.Percentage;
import cichlid.seprphase3.Simulator.Pump;
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
    // These represent parts of the plant which will be drawn to the screen.
    // Each has a location and an image which is set up in setupComponents().
    private PlantGUIElement reactor;
    private PlantGUIElement condenser;
    private PlantGUIElement pump1;
    private PlantGUIElement coolingPump;
    private AnimatedPlantGUIElement valve1;
    private AnimatedPlantGUIElement valve2;
    private PlantGUIElement coolingPipe;
    private PlantGUIElement reactorToCondenserPipe;
    private PlantGUIElement condenserToReactorPipe;
    private PlantGUIElement fuelRods;
    private PlantGUIElement controlRods;
    private PlantGUIElement turbineLeft;
    private PlantGUIElement turbineMiddle;
    private PlantGUIElement turbineRight;
    private PlantGUIElement turbineHousing;
    private PlantGUIElement turbineHousing2;
    private Font gameFont;
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

        addMouseListener(this);

        // Give all of the plant components the right images and location on the screen.
        setupComponents();
    }

    /**
     * Gives all of the plant components the right images and locations on the screen. Each component has the form:
     * BufferedImage image = loadImage("images/image.png"); component = new PlantGUIElement( image, location x, location
     * y, scale amount);
     *
     */
    private void setupComponents() {
        BufferedImage reactorImage = loadImage("images/container.png");
        reactor = new PlantGUIElement(reactorImage, 0, 0, 0.9f, X_OFFSET, Y_OFFSET);

        BufferedImage condenserImage = loadImage("images/container.png");
        condenser = new PlantGUIElement(condenserImage, 665, 275, 0.9f, X_OFFSET, Y_OFFSET);

        BufferedImage pumpImage = loadImage("images/pump.png");
        pump1 = new PlantGUIElement(pumpImage, 572, 535, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);
        coolingPump = new PlantGUIElement(pumpImage, 915, 545, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);

        valve1 = new AnimatedPlantGUIElement("animations/closevalve", null, null, null, null, 307, -51, SCALE_AMOUNT +
                                                                                                        0.1f, X_OFFSET,
                                             Y_OFFSET);

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

        valve2 = new AnimatedPlantGUIElement("animations/closevalve", null, null, null, null, 761, 170, SCALE_AMOUNT +
                                                                                                        0.1f, X_OFFSET,
                                             Y_OFFSET, rotateValve90Deg);

        BufferedImage coolingPipeImage = loadImage("images/coolingPipe.png");
        coolingPipe = new PlantGUIElement(coolingPipeImage, 750, 450, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);

        BufferedImage reactorToCondenserPipeImage = loadImage("images/reactorToCondenser.png");
        reactorToCondenserPipe = new PlantGUIElement(reactorToCondenserPipeImage, 137, -2, SCALE_AMOUNT, X_OFFSET,
                                                     Y_OFFSET);

        BufferedImage condenserToReactorPipeImage = loadImage("images/condenserToReactor.png");
        condenserToReactorPipe = new PlantGUIElement(condenserToReactorPipeImage, 154, 334, SCALE_AMOUNT + 0.3f,
                                                     X_OFFSET, Y_OFFSET);

        BufferedImage fuelRodsImage = loadImage("images/fuel_rods.png");
        fuelRods = new PlantGUIElement(fuelRodsImage, 63, 216, SCALE_AMOUNT, X_OFFSET, Y_OFFSET);

        BufferedImage controlRodsImage = loadImage("images/control_rods.png");
        controlRods = new PlantGUIElement(controlRodsImage, 65, -290, SCALE_AMOUNT + 0.2f, X_OFFSET, Y_OFFSET);

        BufferedImage turbineLeftImage = loadImage("images/turbine_left.png");
        turbineLeft = new PlantGUIElement(turbineLeftImage, 675, 57, SCALE_AMOUNT + 0.2f, X_OFFSET, Y_OFFSET);

        BufferedImage turbineMiddleImage = loadImage("images/turbine_middle.png");
        turbineMiddle = new PlantGUIElement(turbineMiddleImage, 720, 80, SCALE_AMOUNT + 0.2f, X_OFFSET, Y_OFFSET);

        BufferedImage turbineRightImage = loadImage("images/turbine_right.png");
        turbineRight = new PlantGUIElement(turbineRightImage, 800, 50, SCALE_AMOUNT + 0.2f, X_OFFSET, Y_OFFSET);

        BufferedImage turbineHousingImage = loadImage("images/turbineC2.png");
        turbineHousing = new PlantGUIElement(turbineHousingImage, 650, 36, SCALE_AMOUNT + 0.1f, X_OFFSET, Y_OFFSET);
        turbineHousing2 = new PlantGUIElement(turbineHousingImage, 770, 36, SCALE_AMOUNT + 0.1f, X_OFFSET, Y_OFFSET);

        gameFont = new Font("Impact", Font.PLAIN, 20);
    }

    private BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("Error loading image resources> " + filePath + "  :  " + e.getMessage());
        }

        return null;
    }

    /**
     * Draws a plantGUIElement to a specified Graphics2D (the window). It takes the X position and Y position and draws
     * the specified image there.
     *
     * @param g          The screen to draw the image to.
     * @param guiElement The GUI element to draw.
     */
    public void drawPlantGUIElement(Graphics2D g, PlantGUIElement guiElement) {
        g.drawImage(guiElement.image, guiElement.x(), guiElement.y(), null);
    }

    public void drawAnimatedGUIElement(Graphics2D g, AnimatedPlantGUIElement guiElement) {
        g.drawImage(guiElement.working.stepImage(), guiElement.x(), guiElement.y(), null);
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

        drawPlantGUIElement(g, reactorToCondenserPipe);
        drawPlantGUIElement(g, condenserToReactorPipe);
        drawPlantGUIElement(g, coolingPipe);

        drawPlantGUIElement(g, pump1);
        drawPlantGUIElement(g, coolingPump);

        drawPlantGUIElement(g, turbineHousing);
        drawPlantGUIElement(g, turbineHousing2);
        drawPlantGUIElement(g, turbineMiddle);
        drawPlantGUIElement(g, turbineLeft);
        drawPlantGUIElement(g, turbineRight);

        drawAnimatedGUIElement(g, valve1);
        drawAnimatedGUIElement(g, valve2);

        drawPlantGUIElement(g, controlRods);
        drawPlantGUIElement(g, fuelRods);
    }

    /**
     * Draws all of the water effects to the screen.
     */
    private void drawWater(Graphics2D g) {
        g.setColor(new Color(0.4f, 0.4f, 1.0f, 0.7f));

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

        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(gameFont);

        g.drawString("Reactor Pressure: " + plantStatus.reactorPressure(), textX, textY);
        g.drawString("Reactor Temp: " + plantStatus.reactorTemperature(), textX, textY + 30);
        g.drawString("Reactor Water: " + plantStatus.reactorWaterLevel(), textX, textY + 60);

        g.drawString("Condenser Pressure: " + plantStatus.condenserPressure(), textX, textY + 90);
        g.drawString("Condenser Temp: " + plantStatus.condenserTemperature(), textX, textY + 120);
        g.drawString("Condenser Water: " + plantStatus.condenserWaterLevel(), textX, textY + 150);

        g.drawString("Reactor to Turbine: " + plantStatus.getReactorToTurbine(), textX, textY + 180);

        g.drawString("Energy Generated: " + plantStatus.energyGenerated(), textX, textY + 210);

        g.drawString("Control rods: " + plantStatus.controlRodPosition(), textX, textY + 240);

        g.drawString("Software Failure: " + plantStatus.getSoftwareFailure(), textX, textY + 270);

        int offset = 0;
        for (String s : plantStatus.listFailedComponents()) {
            g.drawString("Failure: " + s, textX, textY + 300 + offset);
            offset += 30;
        }
    }

    @Override
    public void mouseClicked(MouseEvent click) {


        if (condenser.location.contains(click.getPoint()) && SwingUtilities.isRightMouseButton(click)) {
            try {
                plantController.repairCondenser();
            } catch (CannotRepairException ex) {
            }
        }

        if (pump1.location.contains(click.getPoint()) && SwingUtilities.isRightMouseButton(click)) {
            try {
                plantController.repairPump(1);
            } catch (KeyNotFoundException ex) {
            } catch (CannotRepairException ex) {
            }
        }

        if (pump1.location.contains(click.getPoint()) && SwingUtilities.isLeftMouseButton(click)) {
            Pump pump1 = (Pump)plantStatus.componentList().get("pump1");
            boolean state = pump1.getStatus();

            valve1.setAnimation(PlantAnimationType.WORKING);
            //valve2.setAnimation(PlantAnimationType.STOPPING);

            if (state) {
                pump1.setStatus(false);
            } else {
                pump1.setStatus(true);
            }
        }

        if (coolingPump.location.contains(click.getPoint()) && SwingUtilities.isRightMouseButton(click)) {
            try {
                plantController.repairPump(2);
            } catch (KeyNotFoundException ex) {
            } catch (CannotRepairException ex) {
            }
        }

        if (coolingPump.location.contains(click.getPoint()) && SwingUtilities.isLeftMouseButton(click)) {
            Pump coolingPump = (Pump)plantStatus.componentList().get("coolingPump");
            boolean state = coolingPump.getStatus();

            if (state) {
                coolingPump.setStatus(false);
            } else {
                coolingPump.setStatus(true);
            }
        }

        if ((turbineLeft.location.contains(click.getPoint()) || turbineRight.location.contains(click.getPoint())) &&
            SwingUtilities.isRightMouseButton(click)) {
            try {
                plantController.repairTurbine();
            } catch (CannotRepairException ex) {
            }
        }

        if (valve1.location.contains(click.getPoint()) && SwingUtilities.isLeftMouseButton(click)) {
            boolean state = plantStatus.connectionList().get("reactorToTurbine").getOpen();

            if (state)
            {
                plantStatus.connectionList().get("reactorToTurbine").setOpen(false);
            
            }
            else{
                plantStatus.connectionList().get("reactorToTurbine").setOpen(true);
            }
        }

        if (valve2.location.contains(click.getPoint()) && SwingUtilities.isLeftMouseButton(click)) {
            boolean state = plantStatus.connectionList().get("turbineToCondenser").getOpen();

            if (state)
            {   
                plantStatus.connectionList().get("turbineToCondenser").setOpen(false);
            }
            else{
                plantStatus.connectionList().get("turbineToCondenser").setOpen(true);
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
