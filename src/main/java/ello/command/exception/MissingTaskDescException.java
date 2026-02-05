package ello.command.exception;

import ello.task.TaskType;

/**
 * Exception thrown when a command is missing the ello.task description.
 */
public class MissingTaskDescException extends InvalidCommandException {
    /**
     * Constructs a {@code MissingTaskDescException} with a default error message based on the ello.task type.
     * @param type The {@link TaskType} associated with the command.
     */
    public MissingTaskDescException(TaskType type) {
        super("The command '" + type.getCommandWord() + "' is missing ello.task description:\n"
                + type.buildSyntax());
    }
}
