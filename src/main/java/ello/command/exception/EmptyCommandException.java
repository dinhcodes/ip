package ello.command.exception;

/**
 * Exception thrown when a user command is empty.
 */
public class EmptyCommandException extends InvalidCommandException {
    /**
     * Constructs an {@code EmptyCommandException} with a default error message.
     */
    public EmptyCommandException() {
        super("Command cannot be empty.");
    }
}
