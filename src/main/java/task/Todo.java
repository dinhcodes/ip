package main.java.task;

import java.util.HashMap;

/**
 * Represents a to-do {@link Task} with a description.
 */
public class Todo extends Task {
    /**
     * Constructs a to-do {@link Task} with the given description.
     * @param taskDescription {@code String} that represents the description of the task.
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Creates a to-do {@link Task} using the provided description and marker-to-description map.
     * @param taskDescription {@code String} that represents the description of the task.
     * @param markerToDescMap {@code HashMap<String, String>} that represents markers and their corresponding descriptions.
     * @return A new {@code Todo} task.
     */
    public static Todo create(String taskDescription, HashMap<String, String> markerToDescMap) {
        return new Todo(taskDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
