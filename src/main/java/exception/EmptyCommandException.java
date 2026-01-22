package main.java.exception;

public class EmptyCommandException extends ElloException {
    public EmptyCommandException() {
        super("Command cannot be empty.");
    }

    public EmptyCommandException(String message) {
        super(message);
    }
}
