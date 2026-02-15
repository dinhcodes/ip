package ello.command.exception;

import ello.command.CommandType;

/**
 * Exception thrown when there is no space after a command word in a list-related command.
 */
public class MissingSpaceAfterCommandWordException extends InvalidCommandException {
    /**
     * Constructs an exception with a default error message based on the command type.
     *
     * @param type The command type associated with the command.
     */
    public MissingSpaceAfterCommandWordException(CommandType type) {
        super("Please ensure there is a space after the command word: '" + type.getCommandWord() + "'\n"
                + type.buildSyntax());
    }
}
