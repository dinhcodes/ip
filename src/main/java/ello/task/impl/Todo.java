package ello.task.impl;

import ello.task.Task;
import ello.task.TaskType;

import java.util.HashMap;

/**
 * Represents a to-do {@link Task} with a description.
 */
public class Todo extends Task {
    /**
     * Constructs a to-do {@link Task} with the given description.
     * @param taskDescription {@code String} that represents the description of the {@link Task}.
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    public static Todo create(String taskDescription, HashMap<String, String> markerToDescMap) {
        return new Todo(taskDescription);
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
