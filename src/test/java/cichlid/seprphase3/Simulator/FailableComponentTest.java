/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cichlid.seprphase3.Simulator;

import cichlid.seprphase3.GameOverException;
import cichlid.seprphase3.Simulator.FailableComponent;
import cichlid.seprphase3.Simulator.Reactor;
import cichlid.seprphase3.Simulator.Turbine;
import cichlid.seprphase3.Utilities.Percentage;
import cichlid.seprphase3.Utilities.Pressure;
import cichlid.seprphase3.Utilities.Temperature;
import org.junit.Test;
import static org.junit.Assert.*;
import static cichlid.seprphase3.Utilities.Units.*;
import org.junit.Ignore;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author Marius
 */
public class FailableComponentTest {

    public FailableComponentTest() {
    }

    @Test
    public void shouldInitializeReactorComponentStateToNotFailed() {

        FailableComponent reactor;
        reactor = new Reactor();
        assertFalse(reactor.hasFailed());
    }

    @Test
    public void shouldSetReactorFailedStateToFailed() {
        FailableComponent reactor;
        reactor = new Reactor();
        reactor.fail();
        assertTrue(reactor.hasFailed());
    }

    @Test
    public void shouldInitializeReactorWearTo0() {
        FailableComponent reactor = new Reactor();
        reactor.wear();
        assertEquals(percent(0), reactor.wear());
    }

    @Test
    public void shouldInitializeTurbineComponentStateToNotFailed() {
        FailableComponent turbine;
        turbine = new Turbine();
        assertFalse(turbine.hasFailed());
    }

    @Test
    public void shouldSetTurbineFailedStateToFailed() {
        FailableComponent turbine;
        turbine = new Turbine();
        turbine.fail();
        assertTrue(turbine.hasFailed());
    }

    @Test
    public void shouldInitializeTurbineWearTo0() {
        FailableComponent turbine = new Turbine();
        turbine.wear();
        assertEquals(percent(0), turbine.wear());
    }

    @Test
    @Ignore
    public void shouldIncreaseWearOfReactorWhenRunning() throws GameOverException {
        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
                                      new Temperature(400), new Pressure(101325));
        reactor.step();
        assertThat(reactor.wear().ratio(), greaterThan(0.0));
    }

    @Test
    public void shouldIncreaseWearOfTurbineWhenRunning() {
        Turbine turbine = new Turbine();
        turbine.step();
        assertThat(turbine.wear().ratio(), greaterThan(0.0));
    }

    @Test
    @Ignore
    public void shouldNotIncreaseWearOver100() throws GameOverException {
        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
                                      new Temperature(400), new Pressure(101325));
        Turbine turbine = new Turbine();

        for (int i = 0; i < 150; i++) {
            reactor.step();
            turbine.step();
        }
        assertEquals(percent(100), reactor.wear());
        assertEquals(percent(100), turbine.wear());
    }
}
