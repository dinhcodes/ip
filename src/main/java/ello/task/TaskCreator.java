package ello.task;

import java.util.HashMap;

/**
 * Functional interface for creating {@link Task} objects.
 */
@FunctionalInterface
public interface TaskCreator {
    /**
     * Creates a task object based on the provided description and marker-to-description map.
     *
     * @param taskDescription The description of the task.
     * @param markerToDescMap Markers and their corresponding descriptions, if any.
     * @return A new task object.
     */
    Task create(String taskDescription, HashMap<String, String> markerToDescMap);
}