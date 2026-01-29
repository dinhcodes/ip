package main.java.exception;

/**
 * Represents an exception thrown when a user command is empty.
 */
public class EmptyCommandException extends InvalidCommandException {
    /**
     * Constructs an {@code EmptyCommandException} with a default error message.
     */
    public EmptyCommandException() {
        super("Command cannot be empty.");
    }

    /**
     * Constructs an {@code EmptyCommandException} with a custom error message.
     *
     * @param message The custom error message.
     */
    public EmptyCommandException(String message) {
        super(message);
    }
}
