package ello.logic.command.exception;

import ello.model.task.TaskType;

/**
 * Exception thrown when the markers in a user command are out of order.
 */
public class MarkersOutOfOrderException extends InvalidCommandException {
    /**
     * Constructs an exception with a default error message based on the task type.
     *
     * @param type The task type associated with the command.
     */
    public MarkersOutOfOrderException(TaskType type) {
        super("The markers order is incorrect. Please check the correct command syntax!\n"
                + type.buildSyntax());
    }
}
