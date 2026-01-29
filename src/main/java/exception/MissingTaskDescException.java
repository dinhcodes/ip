package main.java.exception;

import main.java.task.TaskType;

import java.util.List;

/**
 * Represents an exception thrown when a command is missing the task description.
 */
public class MissingTaskDescException extends InvalidCommandException {
    /**
     * Constructs a {@code MissingTaskDescException} with a default error message based on the task type.
     * @param type The {@link TaskType} associated with the command.
     */
    public MissingTaskDescException(TaskType type) {
        super("The command '" + type.getCommandWord() + "' is missing task description:\n"
                + type.buildSyntax());
    }
}
