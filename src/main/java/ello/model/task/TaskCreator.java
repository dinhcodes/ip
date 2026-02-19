package ello.model.task;

import java.util.HashMap;

/**
 * Functional interface for creating {@link Task} objects.
 */
@FunctionalInterface
public interface TaskCreator {
    /**
     * Creates a task based on the provided description and marker-to-description map.
     *
     * @param taskDescription {@code String} that represents the description of the {@link Task}.
     * @param markerToDescMap A map between markers and their corresponding descriptions, if any.
     * @return The created task.
     */
    Task create(String taskDescription, HashMap<String, String> markerToDescMap);
}
