package main.java.exception;

/**
 * Represents a custom exception class for Ello application.
 */
public class ElloException extends RuntimeException {
    /**
     * Constructs a new {@code ElloException} with the specified detail message.
     * @param message The custom error message.
     */
    public ElloException(String message) {
        super(message);
    }
}
