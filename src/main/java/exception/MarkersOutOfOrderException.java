package main.java.exception;

import main.java.task.TaskType;

public class MarkersOutOfOrderException extends InvalidCommandException {
    public MarkersOutOfOrderException(TaskType type) {
        super("The markers order is incorrect. Please check the correct command syntax!\n"
            + type.buildSyntax());
    }

    public MarkersOutOfOrderException(String message) {
        super(message);
    }
}
