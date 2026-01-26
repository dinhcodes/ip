package main.java.exception;

import main.java.task.TaskType;

import java.util.List;

public class MissingTaskDescException extends InvalidCommandException {
    public MissingTaskDescException(String message) {
        super(message);
    }

    public MissingTaskDescException(TaskType type) {
        super("The command '" + type.getCommandWord() + "' is missing task description:\n"
                + type.buildSyntax());
    }
}
