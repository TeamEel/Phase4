package cichlid.seprphase3.Simulator;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 * @author David
 */
public interface GameManager {

    String[] listGames();

    void loadGame(int gameNumber);

    void saveGame() throws JsonProcessingException;

    void setUsername(String userName);
    
    String getUsername();
}
