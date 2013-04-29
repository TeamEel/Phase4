package eel.seprphase4.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author georgestephenson
 */
public class Quencher {

    @JsonProperty
    private boolean used = false;

    public void quenchReactor(Reactor reactor) {
        if (used == false) {
            reactor.quench();
            used = true;
        }
    }

    public boolean used() {
        return this.used;
    }
}
