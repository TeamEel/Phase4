package eel.seprphase4;

/**
 * Thrown to end the game.
 *
 * @author David
 */
public class GameOverException extends Exception {

    public GameOverException() {
    }

    public GameOverException(String message) {
        super(message);
    }
}
