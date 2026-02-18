package ello.logic.command.exception;

import ello.logic.command.parser.DateTimeParser;

/**
 * Exception thrown when a date/time string cannot be parsed.
 */
public class InvalidDateTimeException extends InvalidCommandException {
    /**
     * Constructs an exception with a message describing the error.
     *
     * @param input The invalid date/time string that could not be parsed.
     */
    public InvalidDateTimeException(String input) {
        super("Invalid date/time format: '" + input + "'.\n"
                + "Supported formats:\n"
                + DateTimeParser.getSupportedFormatsDescription());
    }
}
