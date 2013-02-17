package cichlid.seprphase3;

import cichlid.seprphase3.GUIInterface.GUIWindow;
import cichlid.seprphase3.GUIInterface.PlantInterface;
import cichlid.seprphase3.Simulator.Simulator;
import cichlid.seprphase3.GUIInterface.MenuInterface;


public class Game {

    public GUIWindow gui;
    private Simulator simulator;

    public Game() throws GameOverException, QuitGameException, InterruptedException {

        simulator = new Simulator();

        gui = new GUIWindow("Nuke Dukem", 1366, 768);
        //gui.setWindow(new PlantInterface(simulator, simulator, simulator));
        gui.setWindow(new MenuInterface(gui, simulator));
        
        long lastTime = System.nanoTime();
        long elapsedTime = 0;

        while(true) {
            long currentTime = System.nanoTime();
            
            if (elapsedTime > 50000000) {
                simulator.step();
                gui.update();
                elapsedTime = 0;
            } else {
                elapsedTime += currentTime - lastTime;
                Thread.sleep(20);
            }
            
            lastTime = currentTime;
        }
    }
}
