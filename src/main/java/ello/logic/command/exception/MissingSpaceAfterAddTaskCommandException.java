package ello.logic.command.exception;

import ello.model.task.TaskType;

/**
 * Exception thrown when there is no space after the command word in a user command.
 */
public class MissingSpaceAfterAddTaskCommandException extends InvalidCommandException {
    /**
     * Constructs an exception with a default error message based on the task type.
     *
     * @param type The task type associated with the command.
     */
    public MissingSpaceAfterAddTaskCommandException(TaskType type) {
        super("Please ensure there is a space after the command word: '" + type.getCommandWord() + "'\n"
                + type.buildSyntax());
    }
}
