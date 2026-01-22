package main.java.exception;

public class InvalidCommandException extends ElloException {
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means.");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
