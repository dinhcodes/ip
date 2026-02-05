package ello.command.parser;

import ello.command.exception.*;
import ello.task.Task;
import ello.task.TaskType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a utility class that parses user commands to create Tasks.
 */
public class TaskParser {

    private TaskParser() {
    // Prevent instantiation
    }

    /**
     * Validates, parses and creates a {@link Task} from the given command.
     * @param command The input command by user.
     * @return The created {@link Task}
     */
    public static Task validateParseAndCreateTask(String command) {
        TaskType type = extractTaskType(command);

        // Parse the task description
        String taskDescription = extractDescription(command, type);
        validateTaskDescription(taskDescription, type);

        // Parse the markers and their descriptions, putting them into a hashmap
        HashMap<String, String> markerToDescMap = extractMarkerToDescMap(command, type);


        // Create event with parsed details
        return type.getTaskCreator().create(taskDescription, markerToDescMap);
    }

    private static void validateTaskDescription(String taskDescription, TaskType type) {
        if (taskDescription.isEmpty()) {
            throw new MissingTaskDescException(type);
        }
    }

    private static TaskType extractTaskType(String command) {
        for (TaskType type : TaskType.values()) {
            if (command.startsWith(type.getCommandWord() + " ")) {
                return type;
            }
            if (command.equals(type.getCommandWord())) {
                return type;
            }
            if (command.startsWith(type.getCommandWord())
                    && command.length() > type.getCommandWord().length()) {
                throw new MissingSpaceAfterCommandWordException(type);
            }
        }
        throw new InvalidCommandException();
    }
    
    private static String extractDescription(String command, TaskType type) {
        String[] markers = type.getMarkers();

        int indexOfTaskDescriptionStart = type.getCommandWord().length();

        if (markers.length == 0) {
            return command.substring(indexOfTaskDescriptionStart).trim();
        }

        int indexOfFirstMarker = command.indexOf("/" + type.getMarkers()[0]);
        if (indexOfFirstMarker != -1) {
            return command.substring(indexOfTaskDescriptionStart, indexOfFirstMarker).trim();
        }

        return command.substring(indexOfTaskDescriptionStart).trim();
    }

    // ExtractS markers and their corresponding descriptions from the command.
    private static HashMap<String, String> extractMarkerToDescMap(String command, TaskType type) {
        // Retrieve all markers for the given task type
        HashMap<String, String> map = new HashMap<>();
        String[] markers = type.getMarkers();

        if (markers.length == 0) {
            return map;
        }

        // Validate that all markers are present in the command
        validateAllMarkersPresent(command, type, markers);

        List<String> markersWithMissingValues = new ArrayList<>();

        // Extract each marker's description
        for (int i = 0; i < markers.length; i++) {
            String currentMarker = "/" + markers[i];
            int startIndex = command.indexOf(currentMarker) + currentMarker.length();
            int endIndex = command.length();

            if (i + 1 < markers.length) {
                String nextMarker = "/" + markers[i + 1];
                int nextIndex = command.indexOf(nextMarker);

                if (nextIndex < startIndex) {
                    throw new MarkersOutOfOrderException(type);
                }
                endIndex = nextIndex;
            }

            String value = command.substring(startIndex, endIndex).trim();
            if (value.isEmpty()) {
                markersWithMissingValues.add(markers[i]);
            }
            map.put(markers[i], value);
        }

        // Check for any markers that are missing values
        if (!markersWithMissingValues.isEmpty()) {
            throw new MissingMarkerDescException(type, markersWithMissingValues);
        }

        return map;
    }

    private static void validateAllMarkersPresent(String command, TaskType type, String[] markers) {
        List<String> missingMarkers = new ArrayList<>();
        for (String marker : markers) {
            if (!command.contains("/" + marker)) {
                missingMarkers.add(marker);
            }
        }
        if (!missingMarkers.isEmpty()) {
            throw new MissingMarkerException(type, missingMarkers);
        }
    }
}
