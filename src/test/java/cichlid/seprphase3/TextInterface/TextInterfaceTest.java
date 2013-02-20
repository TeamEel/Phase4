package cichlid.seprphase3.TextInterface;

import cichlid.seprphase3.TextInterface.TextRenderer;
import cichlid.seprphase3.TextInterface.DoNotStep;
import cichlid.seprphase3.TextInterface.TextInterface;
import cichlid.seprphase3.TextInterface.LineReader;
import cichlid.seprphase3.Simulator.CannotControlException;
import cichlid.seprphase3.Simulator.GameManager;
import cichlid.seprphase3.Simulator.KeyNotFoundException;
import cichlid.seprphase3.Simulator.PlantController;
import cichlid.seprphase3.Simulator.PlantStatus;
import cichlid.seprphase3.Utilities.Energy;
import cichlid.seprphase3.Utilities.Percentage;
import cichlid.seprphase3.Utilities.Pressure;
import cichlid.seprphase3.Utilities.Temperature;
import org.jmock.Expectations;
import org.jmock.Sequence;
import org.jmock.auto.Auto;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author Marius
 */
public class TextInterfaceTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    @Mock
    private PlantController plantController;
    @Mock
    private GameManager gameManager;
    @Mock
    private PlantStatus plantStatus;
    @Mock
    private TextRenderer textRenderer;
    @Mock
    private LineReader lineReader;
    @Auto
    private Sequence lines;
    private TextInterface textInterface;

    @Before
    public void setup() {
        textInterface = new TextInterface(plantController, plantStatus, gameManager,
                                          textRenderer, lineReader);
    }

    @Ignore
    @Test
    public void shouldShowStatus() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).controlRodPosition();
                will(returnValue(new Percentage(37)));
                allowing(plantStatus).reactorTemperature();
                will(returnValue(new Temperature(350)));
                allowing(plantStatus).reactorPressure();
                will(returnValue(new Pressure(101325)));
                allowing(plantStatus).reactorWaterLevel();
                will(returnValue(new Percentage(100)));
                allowing(plantStatus).energyGenerated();
                will(returnValue(new Energy(0)));

                oneOf(textRenderer).outputLine("Control Rod Position: 37%");
                inSequence(lines);
                oneOf(textRenderer).outputLine("Reactor Temperature: 76.85 degrees C");
                inSequence(lines);
                oneOf(textRenderer).outputLine("Reactor Pressure: 1 atm");
                inSequence(lines);
                oneOf(textRenderer).outputLine("Water Level: 100%");
                inSequence(lines);
                oneOf(textRenderer).outputLine("Energy Generated: 0 J");
                inSequence(lines);
            }
        });
        textInterface.showStatus();
    }

    @Test
    public void shouldSetUsername() {

        context.checking(new Expectations() {
            {
                allowing(textRenderer).outputLine("Please Enter Username:");
                oneOf(lineReader).readLine();
                will(returnValue("James"));
                oneOf(gameManager).setUsername("James");
            }
        });
        textInterface.askForUsername();
    }

    @Test
    public void shouldProcessACommand() throws CannotControlException, KeyNotFoundException, DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {

                oneOf(lineReader).readLine();
                will(returnValue("movecontrolrods 50"));
                oneOf(plantController).moveControlRods(new Percentage(50));
                oneOf(textRenderer).outputLine("Moved control rods to 50%");
            }
        });
        textInterface.processCommand();
    }
}
