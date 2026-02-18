package ello.logic.command.exception;

/**
 * Exception thrown when a task index provided by the user is out of bounds.
 */
public class TaskIndexOutOfBoundsException extends InvalidCommandException {
    /**
     * Constructs an exception with a default error message.
     *
     * @param oneBasedIndex The one-based index provided by the user.
     */
    public TaskIndexOutOfBoundsException(int oneBasedIndex) {
        super("The task index " + oneBasedIndex + " provided is out of bounds. Please provide a valid task number");
    }
}
