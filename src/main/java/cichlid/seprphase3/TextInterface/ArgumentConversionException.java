package cichlid.seprphase3.TextInterface;

/**
 * Exception thrown when an argument cannot be converted to the type required by a command
 * @author David
 */
public class ArgumentConversionException extends ArgumentException {

    public ArgumentConversionException(String msg) {
        super(msg);
    }
}
