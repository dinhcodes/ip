package ello.logic.command.exception;

import ello.model.task.TaskType;

/**
 * Exception thrown when a command is missing the task description.
 */
public class MissingTaskDescException extends InvalidCommandException {
    /**
     * Constructs an exception with a default error message based on the task type.
     *
     * @param type The task type associated with the command.
     */
    public MissingTaskDescException(TaskType type) {
        super("The command '" + type.getCommandWord()
                + "' is missing task description:\n"
                + type.buildSyntax());
    }
}
