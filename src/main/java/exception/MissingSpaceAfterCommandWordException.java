package main.java.exception;

import main.java.task.TaskType;

public class MissingSpaceAfterCommandWordException extends InvalidCommandException {
    public MissingSpaceAfterCommandWordException(TaskType type) {
        super("Please ensure there is a space after the command word: '" + type.getCommandWord() + "'\n"
                + type.buildSyntax());
    }
}
