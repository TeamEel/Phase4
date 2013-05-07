/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.Simulator;

import eel.seprphase4.Simulator.Port;
import eel.seprphase4.Simulator.Pump;
import eel.seprphase4.Utilities.Percentage;
import static eel.seprphase4.Utilities.Units.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Marius
 */
public class PumpTest {

    @Test
    public void shouldNotPumpWaterOverCapacity() {
        Port input = new Port();
        Port output = new Port();
        input.mass = kilograms(10);
        output.mass = kilograms(0);
        Pump pump = new Pump(input, output);
        pump.setCapacity(kilograms(2));
        pump.step();
        assertEquals(kilograms(2), output.mass);
    }

    @Test
    public void shouldPumpAllWaterIfUnderCapacity() {
        Port input = new Port();
        Port output = new Port();
        input.mass = kilograms(2);
        output.mass = kilograms(0);
        Pump pump = new Pump(input, output);
        pump.setCapacity(kilograms(10));
        pump.step();
        assertEquals(kilograms(2), output.mass);
    }

    @Test
    public void shouldSendWaterAtSameTemperature() {

        Port input = new Port();
        Port output = new Port();
        input.mass = kilograms(2);
        output.mass = kilograms(0);
        input.temperature = kelvin(100);
        Pump pump = new Pump(input, output);
        pump.setCapacity(kilograms(10));
        pump.step();
        assertEquals(kelvin(100), output.temperature);
    }

    @Test
    public void shouldChangeStatus() {
        Port input = new Port();
        Port output = new Port();
        Pump pump = new Pump(input, output);
        pump.setOnState(true);
        assertEquals(pump.isOn(), true);
    }

    @Test
    public void shouldNotPumpIfOff() {
        Port input = new Port();
        Port output = new Port();
        input.mass = kilograms(2);
        output.mass = kilograms(0);
        input.temperature = kelvin(100);
        Pump pump = new Pump(input, output);
        pump.setOnState(false);
        pump.setCapacity(kilograms(10));
        pump.step();
        assertEquals(kilograms(0), output.mass);
    }

    @Test
    public void shouldNotPumpIfFailed() {
        Port input = new Port();
        Port output = new Port();
        input.mass = kilograms(2);
        output.mass = kilograms(0);
        input.temperature = kelvin(100);
        Pump pump = new Pump(input, output);
        pump.setOnState(true);
        pump.fail();
        pump.setCapacity(kilograms(10));
        pump.step();
        assertEquals(kilograms(0), output.mass);
    }

    @Test
    public void shouldCalculateWearDelta() {
        Port input = new Port();
        Port output = new Port();
        input.mass = kilograms(2);
        output.mass = kilograms(0);
        input.temperature = kelvin(100);
        Pump pump = new Pump(input, output);
        pump.setCapacity(kilograms(10));
        pump.step();
        assertEquals(new Percentage(0.05), pump.wear());
    }
}
