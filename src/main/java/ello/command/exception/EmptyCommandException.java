package ello.command.exception;

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
}
