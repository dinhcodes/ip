package ello.command.exception;

import ello.task.TaskType;
import java.util.List;

/**
 * Exception thrown when a user command is missing required markers.
 */
public class MissingMarkerException extends InvalidCommandException {
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
