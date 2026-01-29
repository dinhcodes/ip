package main.java.exception;

/**
 * Represents an exception thrown when a user enters an invalid command.
 */
public class InvalidCommandException extends ElloException {
    /**
     * Constructs an {@code InvalidCommandException} with a default error message.
     */
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means.");
    }

    /**
     * Constructs an {@code InvalidCommandException} with a custom error message.
     *
     * @param message The custom error message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
