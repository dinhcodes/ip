package main.java.exception;

import main.java.task.TaskType;
import java.util.List;

/**
 * Represents an exception thrown when a user command is missing required markers.
 */
public class MissingMarkerException extends InvalidCommandException {
    /**
     * Constructs a {@code MissingMarkerException} with a default error message.
     */
    public MissingMarkerException() {
        super("I'm sorry, but you might missed some markers in your command.");
    }

    /**
     * Constructs a {@code MissingMarkerException} with a custom error message based on the {@link TaskType}
     * and the list of missing markers.
     * @param type The {@link TaskType} associated with the command.
     * @param missingMarkers The list of missing markers as {@code List<String>}.
     */
    public MissingMarkerException(TaskType type, List<String> missingMarkers) {
        super("The command '" + type.getCommandWord()
                + "' is missing the following markers: " + String.join(", ", missingMarkers)
                + "\n" + type.buildSyntax());
    }
}
