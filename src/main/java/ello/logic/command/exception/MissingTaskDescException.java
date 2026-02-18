package ello.logic.command.exception;

import ello.model.task.TaskType;

/**
 * Exception thrown when a command is missing the task description.
 */
public class MissingTaskDescException extends InvalidCommandException {
    /**
     * <<<<<<< Updated upstream
     * Constructs an exception with a default error message based on the task type.
     *
     * @param type The task type associated with the command.
     *             =======
     *             Constructs a {@code MissingTaskDescException} with a default error message based on the ello.task type.
     * @param type The {@link TaskType} associated with the command.
     *             >>>>>>> Stashed changes
     */
    public MissingTaskDescException(TaskType type) {
        super("The command '" + type.getCommandWord() + "' is missing task description:\n"
                + type.buildSyntax());
    }
}
