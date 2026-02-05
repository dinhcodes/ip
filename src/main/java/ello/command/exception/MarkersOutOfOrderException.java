package ello.command.exception;

import ello.task.TaskType;

/**
 * Represents an exception thrown when the markers in a user command are out of order.
 */
public class MarkersOutOfOrderException extends InvalidCommandException {
    /**
     * Constructs a {@code MarkersOutOfOrderException} with a default error message based on the ello.task type.
     *
     * @param type The {@link TaskType} associated with the command.
     */
    public MarkersOutOfOrderException(TaskType type) {
        super("The markers order is incorrect. Please check the correct command syntax!\n"
            + type.buildSyntax());
    }
}
