package main.java.exception;

import main.java.task.TaskType;
import java.util.List;

/**
 * Represents an exception thrown when a command is missing descriptions for required markers.
 */
public class MissingMarkerDescException extends InvalidCommandException {
    /**
     * Constructs a {@code MissingMarkerDescException} with a custom error message.
     * @param message
     */
    public MissingMarkerDescException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code MissingMarkerDescException} with a default error message based on the task type
     * and the list of markers missing descriptions.
     * @param type The {@link TaskType} associated with the command.
     * @param markersWithMissingDescription The list of markers that are missing descriptions.
     */
    public MissingMarkerDescException(TaskType type, List<String> markersWithMissingDescription) {
        super("The command '" + type.getCommandWord() + "' is missing descriptions for the following markers: "
                + String.join(", ", markersWithMissingDescription) + "\n" + type.buildSyntax());
    }
}
