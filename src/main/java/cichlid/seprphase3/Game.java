package cichlid.seprphase3;

import cichlid.seprphase3.GUIInterface.GUIWindow;
import cichlid.seprphase3.GUIInterface.PlantInterface;
import cichlid.seprphase3.Simulator.Simulator;


public class Game {

    private GUIWindow gui;
    private Simulator simulator;

    public Game() throws GameOverException, QuitGameException, InterruptedException {

        simulator = new Simulator();

        gui = new GUIWindow("Nuke Dukem", 1366, 768);
        gui.setWindow(new PlantInterface(simulator, simulator, simulator));
        
        long lastTime = System.nanoTime();
        long elapsedTime = 0;

        while(true) {
            long currentTime = System.nanoTime();
            
            if (elapsedTime > 16666666) {
                gui.update();
                simulator.step();
                elapsedTime = 0;
                System.out.println("Frame!");
            } else {
                elapsedTime += currentTime - lastTime;
            }
            
            lastTime = currentTime;
        }
    }
}
