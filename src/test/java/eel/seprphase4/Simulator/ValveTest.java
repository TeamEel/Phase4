/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase4.Simulator;

import eel.seprphase4.Simulator.Valve;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author james
 */
public class ValveTest {

    @Test
    public void shouldInitialiseValve() {
        Valve valve = new Valve();
        assertEquals(true, valve.getOpen());

    }

    @Test
    public void shouldSetClosed() {
        Valve valve = new Valve();
        valve.setOpen(false);
        assertEquals(false, valve.getOpen());


    }

    @Test
    public void shouldSetOpen() {
        Valve valve = new Valve();
        valve.setOpen(true);
        assertEquals(true, valve.getOpen());


    }
}
