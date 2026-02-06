package pook;
/**
 * Generates a PookException called when exceptions are encountered in the program
 */
public class PookException extends Exception {
    /**
     * Creates a PookException
     * 
     * @param message to display as the exception error
     */
    public PookException(String message) {
        super(message);
    }
}
