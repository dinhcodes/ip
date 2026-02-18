package ello.logic.command.exception;

import java.util.List;

import ello.model.task.TaskType;

/**
 * Exception thrown when a command is missing descriptions for required markers.
 */
public class MissingMarkerDescException extends InvalidCommandException {
    /**
     * Constructs an exception with a default error message based on the task type
     * and the list of markers missing descriptions.
     *
     * @param type                          The task type associated with the command.
     * @param markersWithMissingDescription The list of markers that are missing descriptions.
     */
    public MissingMarkerDescException(TaskType type, List<String> markersWithMissingDescription) {
        super("The command '" + type.getCommandWord() + "' is missing descriptions for the following markers: "
                + String.join(", ", markersWithMissingDescription) + "\n" + type.buildSyntax());
    }
}
