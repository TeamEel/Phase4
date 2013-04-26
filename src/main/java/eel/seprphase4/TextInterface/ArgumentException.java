package eel.seprphase4.TextInterface;

/**
 * Base class for exceptions thrown due to incorrect command arguments
 *
 * @author David
 */
public class ArgumentException extends Exception {

    public ArgumentException(String msg) {
        super(msg);
    }
}
