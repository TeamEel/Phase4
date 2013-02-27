package cichlid.seprphase3.Simulator;

import cichlid.seprphase3.Simulator.Quencher;
import cichlid.seprphase3.Simulator.Reactor;
import static org.junit.Assert.*;
import org.junit.Test;

import static cichlid.seprphase3.Utilities.Units.*;

/**
 *
 * @author georgestephenson
 */
public class QuencherTest {
    
    @Test
    public void shouldInitializeQuencher() {
        Quencher quencher = new Quencher();
        assertEquals(false, quencher.used());
    }
    
    @Test
    public void shouldQuenchReactor() {
        Quencher quencher = new Quencher();
        Reactor reactor = new Reactor(percent(0), percent(100),
                                                kelvin(450), pascals(101325));
        
        quencher.quenchReactor(reactor);
        assertEquals(kelvin(350),reactor.temperature());
        assertEquals(true, quencher.used());
    }
}
