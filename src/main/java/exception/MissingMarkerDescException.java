package main.java.exception;

import main.java.task.TaskType;

import java.util.List;

public class MissingMarkerDescException extends InvalidCommandException {
    public MissingMarkerDescException(String message) {
        super(message);
    }

    public MissingMarkerDescException(TaskType type, List<String> markersWithMissingDescription) {
        super("The command '" + type.getCommandWord() + "' is missing descriptions for the following markers: "
                + String.join(", ", markersWithMissingDescription) + "\n" + type.buildSyntax());
    }
}
