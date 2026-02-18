package ello.model.task.impl;

import java.util.HashMap;

import ello.model.task.Task;
import ello.model.task.TaskType;

/**
 * Represents a to-do task with a description.
 */
public class Todo extends Task {
    /**
     * Constructs a to-do task with the given description.
     *
     * @param taskDescription The description of the task.
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
