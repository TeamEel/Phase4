package cichlid.seprphase3.TextInterface;

import cichlid.seprphase3.TextInterface.TextRenderer;
import cichlid.seprphase3.TextInterface.DoNotStep;
import cichlid.seprphase3.TextInterface.Parser;
import com.fasterxml.jackson.core.JsonProcessingException;
import cichlid.seprphase3.QuitGameException;
import cichlid.seprphase3.Simulator.CannotControlException;
import cichlid.seprphase3.Simulator.CannotRepairException;
import cichlid.seprphase3.Simulator.GameManager;
import cichlid.seprphase3.Simulator.KeyNotFoundException;
import cichlid.seprphase3.Simulator.PlantController;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static cichlid.seprphase3.Utilities.Units.percent;
import java.io.IOException;

/**
 *
 * @author David
 */
public class ParserTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    @Mock
    private PlantController plantController;
    @Mock
    private GameManager gameManager;
    @Mock
    private TextRenderer textRenderer;
    private Parser parser;

    @Before
    public void setup() {
        parser = new Parser(plantController, gameManager, textRenderer);
    }

    @Test
    public void shouldMoveControlRods() throws CannotControlException, KeyNotFoundException, DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(plantController).moveControlRods(percent(50));
                oneOf(textRenderer).outputLine("Moved control rods to 50%");
            }
        });

        parser.executeCommand("movecontrolrods 50");
    }

    @Test(expected = DoNotStep.class)
    public void wrongCommandShouldNotMoveControlRods() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                allowing(textRenderer);
            }
        });

        parser.executeCommand("don'tmovecontrolrods 50");
    }

    @Test(expected = DoNotStep.class)
    public void wrongCommandShouldDisplayErrorMessage() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: Unknown command 'flibble'");
            }
        });

        parser.executeCommand("flibble 50");
    }

    @Test(expected = DoNotStep.class)
    public void shouldNotAcceptControlRodsAbove100() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("ERROR: '101' is not a valid percentage");
            }
        });

        parser.executeCommand("movecontrolrods 101");
    }

    @Test(expected = DoNotStep.class)
    public void shouldNotAcceptControlRodsBelow0() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("ERROR: '-1' is not a valid percentage");
            }
        });

        parser.executeCommand("movecontrolrods -1");
    }

    @Test(expected = DoNotStep.class)
    public void wrongNumberOfArgumentsInMoveControlRodsShouldCauseAnError() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine(
                        "ERROR: Expected at least 1 argument(s) to command 'movecontrolrods' but got only 0");
            }
        });

        parser.executeCommand("movecontrolrods");
    }

    @Test
    public void shouldOpenValve() throws DoNotStep, CannotControlException, KeyNotFoundException, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(plantController).changeValveState(1, true);
                oneOf(textRenderer).outputLine("Opened valve 1");
            }
        });

        parser.executeCommand("openvalve 1");
    }

    @Test(expected = DoNotStep.class)
    public void wrongNumberOfArgumentsInOpenValveShouldCauseAnErrorWithNoArgs() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine(
                        "ERROR: Expected at least 1 argument(s) to command 'openvalve' but got only 0");
            }
        });

        parser.executeCommand("openvalve");
    }

    @Test(expected = DoNotStep.class)
    public void shouldNotAcceptValvesBelow0() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("ERROR: '-1' is not a positive integer");
            }
        });

        parser.executeCommand("openvalve -1");
    }

    @Test(expected = DoNotStep.class)
    public void shouldNotAcceptValvesWithStringNames() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("ERROR: 'foo' is not a valid integer");
            }
        });

        parser.executeCommand("openvalve foo");
    }

    @Test
    public void shouldCloseValve() throws DoNotStep, CannotControlException, KeyNotFoundException, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(plantController).changeValveState(1, false);
                oneOf(textRenderer).outputLine("Closed valve 1");
            }
        });

        parser.executeCommand("closevalve 1");
    }

    @Test(expected = DoNotStep.class)
    public void wrongNumberOfArgumentsInCloseValveShouldCauseAnErrorWithNoArgs() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine(
                        "ERROR: Expected at least 1 argument(s) to command 'closevalve' but got only 0");
            }
        });

        parser.executeCommand("closevalve");
    }

    @Test(expected = DoNotStep.class)
    public void shouldNotAcceptValvesBelow0InCloseValve() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("ERROR: '-1' is not a positive integer");
            }
        });

        parser.executeCommand("closevalve -1");
    }

    @Test(expected = DoNotStep.class)
    public void shouldNotAcceptValvesWithStringNamesInCloseValve() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("ERROR: 'foo' is not a valid integer");
            }
        });

        parser.executeCommand("closevalve foo");
    }

    @Test
    public void shouldTurnPumpOn() throws DoNotStep, KeyNotFoundException, CannotControlException, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(plantController).changePumpState(1, true);
                oneOf(textRenderer).outputLine("Turned on pump 1");
            }
        });

        parser.executeCommand("pumpon 1");
    }

    @Test(expected = DoNotStep.class)
    public void wrongNumberOfArgumentsInPumpOnShouldCauseAnError0Args() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine(
                        "ERROR: Expected at least 1 argument(s) to command 'pumpon' but got only 0");
            }
        });

        parser.executeCommand("pumpon");
    }

    @Test(expected = DoNotStep.class)
    public void shouldNotAcceptPumpsBelow0() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("ERROR: '-1' is not a positive integer");
            }
        });

        parser.executeCommand("pumpon -1");
    }

    @Test(expected = DoNotStep.class)
    public void shouldNotAcceptStringPumpNames() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("ERROR: 'foo' is not a valid integer");
            }
        });

        parser.executeCommand("pumpon foo");
    }

    public void shouldTurnPumpOff() throws DoNotStep, KeyNotFoundException, CannotControlException, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(plantController).changePumpState(1, false);
            }
        });

        parser.executeCommand("pumpoff 1");
    }

    @Test(expected = DoNotStep.class)
    public void wrongNumberOfArgumentsInPumpOffShouldCauseAnError0Args() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine(
                        "ERROR: Expected at least 1 argument(s) to command 'pumpoff' but got only 0");
            }
        });

        parser.executeCommand("pumpoff");
    }

    @Test(expected = DoNotStep.class)
    public void shoudNotAcceptStringPumpNamesForPumpOff() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("ERROR: 'foo' is not a valid integer");
            }
        });

        parser.executeCommand("pumpoff foo");
    }

    @Test(expected = DoNotStep.class)
    public void shouldNotAcceptPumpsBelow0ForPumpOff() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("ERROR: '-1' is not a positive integer");
            }
        });

        parser.executeCommand("pumpoff -1");
    }

    @Test(expected = DoNotStep.class)
    public void shouldSaveGame() throws DoNotStep, JsonProcessingException, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(gameManager).saveGame();
                oneOf(textRenderer).outputLine("Game Saved!");
            }
        });

        parser.executeCommand("save");
    }

    @Test(expected = DoNotStep.class)
    public void shouldLoadGameWithNumber() throws DoNotStep, IOException, QuitGameException {
        context.checking(new Expectations() {
            {

                allowing(gameManager).loadGame(1);

            }
        });

        parser.executeCommand("load 1");
    }

    @Test(expected = DoNotStep.class)
    public void shouldNotLoadStringGameNames() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {

                oneOf(textRenderer).outputLine("ERROR: 'foo' is not a valid integer");
            }
        });

        parser.executeCommand("load foo");
    }

    @Test(expected = DoNotStep.class)
    public void shoulNotLoadNegativeGames() throws DoNotStep, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("ERROR: '-1' is not a positive integer");
            }
        });

        parser.executeCommand("load -1");
    }

    @Test
    public void shouldRepairPump1() throws DoNotStep, KeyNotFoundException, CannotRepairException, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(plantController).repairPump(1);
            }
        });

        parser.executeCommand("repair pump 1");
    }

    @Test
    public void shouldRepairTurbine() throws DoNotStep, CannotRepairException, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(plantController).repairTurbine();
            }
        });

        parser.executeCommand("repair turbine");
    }

    @Test
    public void shouldRepairCondenser() throws DoNotStep, CannotRepairException, QuitGameException {
        context.checking(new Expectations() {
            {
                oneOf(plantController).repairCondenser();
            }
        });

        parser.executeCommand("repair condenser");
    }
}
