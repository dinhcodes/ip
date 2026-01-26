package main.java.exception;

import main.java.task.TaskType;

import java.util.List;

public class MissingMarkerException extends InvalidCommandException {
    public MissingMarkerException() {
        super("I'm sorry, but you might missed some markers in your command.");
    }

    public MissingMarkerException(TaskType type, List<String> missingMarkers) {
        super("The command '" + type.getCommandWord()
                + "' is missing the following markers: " + String.join(", ", missingMarkers)
                + "\n" + type.buildSyntax());
    }
}
