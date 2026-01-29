package main.java.exception;

import main.java.task.TaskType;

/**
 * Represents an exception thrown when there is no space after the command word in a user command.
 */
public class MissingSpaceAfterCommandWordException extends InvalidCommandException {
    /**
     * Constructs a {@code MissingSpaceAfterCommandWordException} with a default error message based on the task type.
     * @param type The {@link TaskType} associated with the command.
     */
    public MissingSpaceAfterCommandWordException(TaskType type) {
        super("Please ensure there is a space after the command word: '" + type.getCommandWord() + "'\n"
                + type.buildSyntax());
    }
}
