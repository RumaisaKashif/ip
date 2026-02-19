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
        // Used ChatGPT to come up with error message string for injecting Garfield's personality
        super("Oh good. Another problem.\n" + message + 
                "\nTry again, preferably after bringing me lasagna.");
    }
}
