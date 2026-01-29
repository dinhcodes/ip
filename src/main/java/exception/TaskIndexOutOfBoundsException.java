package main.java.exception;

/**
 * Represents an exception thrown when a task index provided by the user is out of bounds.
 */
public class TaskIndexOutOfBoundsException extends InvalidCommandException {
    /**
     * Constructs a {@code TaskIndexOutOfBoundsException} with a custom error message.
     * @param message The custom error message.
     */
    public TaskIndexOutOfBoundsException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code TaskIndexOutOfBoundsException} with a default error message.
     * @param oneBasedIndex The one-based index provided by the user.
     */
    public TaskIndexOutOfBoundsException(int oneBasedIndex) {
        super("The task index " + oneBasedIndex + " provided is out of bounds. Please provide a valid task number");
    }
}
