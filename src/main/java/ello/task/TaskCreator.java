package ello.task;

import java.util.HashMap;

/**
 * Functional interface for creating {@link Task} objects.
 */
@FunctionalInterface
public interface TaskCreator {
    /**
     * Creates a {@link Task} object based on the provided description and marker-to-description map.
     * @param taskDescription {@code String} that represents the description of the {@link Task}.
     * @param markerToDescMap {@code HashMap<String, String>} that represents markers and their corresponding descriptions, if any.
     * @return A new {@code Task} object.
     */
    Task create(String taskDescription, HashMap<String, String> markerToDescMap);
}