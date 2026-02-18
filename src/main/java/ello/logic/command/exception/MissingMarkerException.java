package ello.logic.command.exception;

import java.util.List;

import ello.model.task.TaskType;

/**
 * Exception thrown when a user command is missing required markers.
 */
public class MissingMarkerException extends InvalidCommandException {
    /**
     * Constructs an exception with a custom error message based on the task type
     * and the list of missing markers.
     *
     * @param type           The task type associated with the command.
     * @param missingMarkers The list of missing markers.
     */
    public MissingMarkerException(TaskType type, List<String> missingMarkers) {
        super("The command '" + type.getCommandWord()
                + "' is missing the following markers: " + String.join(", ", missingMarkers)
                + "\n" + type.buildSyntax());
    }
}
