package cichlid.seprphase3;

import cichlid.seprphase3.GUIInterface.PlantInterface;
import cichlid.seprphase3.TextInterface.AsciiArt;
import cichlid.seprphase3.TextInterface.TerminalRenderer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App {
    
    /**
     * The main entry point for the application
     *
     * @param args
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Game game;
        
        while (true) {
            game = new Game();
            try {
                game.run();
            } catch (GameOverException e) {
                
            } catch (QuitGameException e) {
                System.out.println("");
            }
        }
    }
}
