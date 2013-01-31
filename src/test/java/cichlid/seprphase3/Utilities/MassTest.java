/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cichlid.seprphase3.Utilities;

import cichlid.seprphase3.Utilities.Mass;
import cichlid.seprphase3.Utilities.Density;
import cichlid.seprphase3.Utilities.Volume;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class MassTest {

    Mass m1 = new Mass(3);
    Mass m2 = new Mass(2);

    @Test
    public void subtraction() {
        assertEquals(new Mass(1), m1.minus(m2));
    }

    @Test
    public void addition() {
        assertEquals(new Mass(5), m1.plus(m2));
    }

    @Test
    public void stringConversion() {
        assertEquals("3 kg", m1.toString());
    }

    @Test
    public void equality() {
        assertTrue(m1.equals(new Mass(3)));
    }

    @Test
    public void densityGivenVolume() {
        assertEquals(new Density(1), m1.densityAt(new Volume(3)));
    }

    @Test
    public void volumeGivenDensity() {
        assertEquals(new Volume(3), m1.volumeAt(new Density(1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowOnZeroVolume() {
        m1.volumeAt(new Density(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowOnZeroDensity() {
        m1.volumeAt(new Density(0));
    }
}
