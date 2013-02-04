package cichlid.seprphase3;

import cichlid.seprphase3.GUIInterface.GUIInterface;
import cichlid.seprphase3.Simulator.Simulator;


public class Game {

    private GUIInterface gui;
    private Simulator simulator;

    public Game() throws GameOverException, QuitGameException {

        simulator = new Simulator();

        gui = new GUIInterface(simulator, simulator, simulator);
        gui.start();

        while(true) {
            gui.render();
        }
    }
}
