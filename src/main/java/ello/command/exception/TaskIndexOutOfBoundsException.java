package ello.command.exception;

/**
 * Exception thrown when a ello.task index provided by the user is out of bounds.
 */
public class TaskIndexOutOfBoundsException extends InvalidCommandException {
    /**
     * Constructs a {@code TaskIndexOutOfBoundsException} with a default error message.
     * @param oneBasedIndex The one-based index provided by the user.
     */
    public TaskIndexOutOfBoundsException(int oneBasedIndex) {
        super("The ello.task index " + oneBasedIndex + " provided is out of bounds. Please provide a valid ello.task number");
    }
}
