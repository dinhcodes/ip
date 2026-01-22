package main.java.exception;

public class TaskIndexOutOfBoundsException extends InvalidCommandException {
    public TaskIndexOutOfBoundsException(String message) {
        super(message);
    }

    public TaskIndexOutOfBoundsException(int oneBasedIndex) {
        super("The task index " + oneBasedIndex + " provided is out of bounds. Please provide a valid task number");
    }
}
