package eel.seprphase4.Simulator;

import eel.seprphase4.Simulator.PlantStatus;
import eel.seprphase4.Simulator.FailureModel;
import eel.seprphase4.Simulator.PlantController;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static eel.seprphase4.Utilities.Units.*;
import org.junit.Ignore;

/**
 *
 * @author david
 
public class FailureModelTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    @Mock
    private PlantController plantController;
    @Mock
    private PlantStatus plantStatus;
    private FailureModel model;

    public FailureModelTest() {
    }

    @Before
    public void setup() {
        model = new FailureModel(plantController, plantStatus, new RandomProbabilitySource());
    }

    @Ignore
    @Test
    public void testStep_0args() throws Exception {
    }

    @Ignore
    @Test
    public void testFailStateCheck() {
    }





    @Test
    public void testMoveControlRods() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(plantController).moveControlRods(percent(57));
            }
        });
        model.moveControlRods(percent(57));
    }

    @Test
    public void changeValveState() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(plantController).changeValveState(1, true);
            }
        });
        model.changeValveState(1, true);
    }

    @Test
    public void changePumpState() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(plantController).changePumpState(1, true);
            }
        });
        model.changePumpState(1, true);
    }

    @Test
    public void repairPump() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(plantController).repairPump(1);
            }
        });
        model.repairPump(1);
    }

    @Test
    public void repairCondenser() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(plantController).repairCondenser();
            }
        });
        model.repairCondenser();
    }

    @Test
    public void repairTurbine() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(plantController).repairTurbine();
            }
        });
        model.repairTurbine();
    }

    @Test
    public void shouldGetControlRodPosition() {
        context.checking(new Expectations() {
            {
                
                will(returnValue(SoftwareFailure.None));
                allowing(plantStatus).controlRodPosition();
                will(returnValue(percent(28)));
            }
        });
        assertEquals(percent(28), model.controlRodPosition());
    }

    @Test
    public void shouldGetFailedControlRodPosition() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).getSoftwareFailure();
                will(returnValue(SoftwareFailure.controlRodRead));
                allowing(plantStatus).controlRodPosition();
                will(returnValue(percent(28)));
            }
        });
        assertEquals(null, model.controlRodPosition());
    }

    @Test
    public void shouldGetReactorPressure() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).getSoftwareFailure();
                will(returnValue(SoftwareFailure.None));
                allowing(plantStatus).reactorPressure();
                will(returnValue(pascals(10)));
            }
        });
        assertEquals(pascals(10), model.reactorPressure());
    }

    @Test
    public void shouldGetFailedReactorPressure() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).getSoftwareFailure();
                will(returnValue(SoftwareFailure.reactorPressureRead));
                allowing(plantStatus).reactorPressure();
                will(returnValue(pascals(10)));
            }
        });
        assertEquals(null, model.reactorPressure());
    }

    @Test
    public void shouldGetReactorTemperature() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).getSoftwareFailure();
                will(returnValue(SoftwareFailure.None));
                allowing(plantStatus).reactorTemperature();
                will(returnValue(kelvin(1000)));
            }
        });
        assertEquals(kelvin(1000), model.reactorTemperature());
    }

    @Test
    public void shouldGetFailedReactorTemperature() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).getSoftwareFailure();
                will(returnValue(SoftwareFailure.reactorTemperatureRead));
                allowing(plantStatus).reactorTemperature();
                will(returnValue(kelvin(1000)));
            }
        });
        assertEquals(null, model.reactorTemperature());
    }

    @Test
    public void shouldGetReactorWaterLevel() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).getSoftwareFailure();
                will(returnValue(SoftwareFailure.None));
                allowing(plantStatus).reactorWaterLevel();
                will(returnValue(percent(89)));
            }
        });
        assertEquals(percent(89), model.reactorWaterLevel());
    }

    @Test
    public void shouldGetFailedReactorWaterLevel() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).getSoftwareFailure();
                will(returnValue(SoftwareFailure.reactorWaterRead));
                allowing(plantStatus).reactorWaterLevel();
                will(returnValue(percent(89)));
            }
        });
        assertEquals(null, model.reactorWaterLevel());
    }

    @Test
    public void shouldGetEnergyGenerated() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).energyGenerated();
                will(returnValue(joules(100000)));
            }
        });
        assertEquals(joules(100000), model.energyGenerated());
    }
    
    @Test
    public void shouldGetCondenserTemperature() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).getSoftwareFailure();
                will(returnValue(SoftwareFailure.None));
                allowing(plantStatus).condenserTemperature();
                will(returnValue(kelvin(100)));
            }
        });
        assertEquals(kelvin(100), model.condenserTemperature());
    }

    @Test
    public void shouldGetFailedCondenserTemperature() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).getSoftwareFailure();
                will(returnValue(SoftwareFailure.condenserTemperatureRead));
                allowing(plantStatus).condenserTemperature();
                will(returnValue(kelvin(100)));
            }
        });
        assertEquals(null, model.condenserTemperature());
    }

    @Test
    public void shouldGetCondenserPressure() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).getSoftwareFailure();
                will(returnValue(SoftwareFailure.None));
                allowing(plantStatus).condenserPressure();
                will(returnValue(pascals(1000)));
            }
        });
        assertEquals(pascals(1000), model.condenserPressure());
    }

    @Test
    public void shouldGetFailedCondenserPressure() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).getSoftwareFailure();
                will(returnValue(SoftwareFailure.condenserPressureRead));
                allowing(plantStatus).condenserPressure();
                will(returnValue(pascals(1000)));
            }
        });
        assertEquals(null, model.condenserPressure());
    }

    @Test
    public void shouldGetCondenserWaterLevel() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).getSoftwareFailure();
                will(returnValue(SoftwareFailure.None));
                allowing(plantStatus).condenserWaterLevel();
                will(returnValue(percent(56)));
            }
        });
        assertEquals(percent(56), model.condenserWaterLevel());
    }

    @Test
    public void shouldGetFailedCondenserWaterLevel() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).getSoftwareFailure();
                will(returnValue(SoftwareFailure.condenserWaterRead));
                allowing(plantStatus).condenserWaterLevel();
                will(returnValue(percent(56)));
            }
        });
        assertEquals(null, model.condenserWaterLevel());
    }

    @Test
    public void shouldGetReactorMinimumWaterLevel() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).reactorMinimumWaterLevel();
                will(returnValue(percent(50)));
            }
        });
        assertEquals(percent(50), model.reactorMinimumWaterLevel());
    }

    @Test
    public void failCondenser() {
        context.checking(new Expectations() {
            {
                oneOf(plantController).failCondenser();
            }
        });
        model.failCondenser();
    }

    @Test
    public void failReactor() {
        context.checking(new Expectations() {
            {
                oneOf(plantController).failReactor();
            }
        });
        model.failReactor();
    }

    @Ignore
    @Test
    public void testStep_int() throws Exception {
    }

    @Test
    public void shouldGetTurbineHasFailed() {
        context.checking(new Expectations() {
            {
                oneOf(plantStatus).turbineHasFailed();
                will(returnValue(true));
            }
        });
        assertEquals(true, model.turbineHasFailed());
    }

    @Ignore
    @Test
    public void testComponents() {
    }
}
*/