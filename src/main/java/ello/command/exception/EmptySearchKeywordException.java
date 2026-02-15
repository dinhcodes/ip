package ello.command.exception;

/**
 * Exception thrown when a search keyword is empty or contains only whitespace.
 */
public class EmptySearchKeywordException extends InvalidCommandException {
    /**
     * Constructs an exception with a default error message.
     */
    public EmptySearchKeywordException() {
        super("Search keyword cannot be empty. Please provide a keyword to search for.");
    }
}
