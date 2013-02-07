package cichlid.seprphase3;

import cichlid.seprphase3.GUIInterface.GUIWindow;
import cichlid.seprphase3.GUIInterface.PlantInterface;
import cichlid.seprphase3.Simulator.Simulator;


public class Game {

    private GUIWindow gui;
    private Simulator simulator;

    public Game() throws GameOverException, QuitGameException, InterruptedException {

        simulator = new Simulator();

        gui = new GUIWindow("Nuke Dukem", 1600, 900);
        gui.setWindow(new PlantInterface(simulator, simulator, simulator));

        while(true) {
            gui.update();
            simulator.step();
        }
    }
}
