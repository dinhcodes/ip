package main.java.task;

import java.util.HashMap;

/**
 * Abstract class that represents a generic task with a description and completion status.
 */
public abstract class Task {
    private final String taskDescription;
    private final HashMap<String, String> mapOfMarkersAndDescription = new HashMap<>();
    private boolean isDone;

    /**
     * Constructs a {@code Task} with the given description.
     * @param taskDescription {@code String} that represents the description of the task.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the given description and map of markers to descriptions.
     * @param taskDescription {@code String} that represents the description of the task.
     * @param mapOfMarkersAndDescription
     */
    public Task(String taskDescription, HashMap<String, String> mapOfMarkersAndDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        this.mapOfMarkersAndDescription.putAll(mapOfMarkersAndDescription);
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     *  Returns detailed text that comes after the taskDescription, for example: date
     */
    protected String detailsSuffix() {
        return "";
    }

    private String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + taskDescription + detailsSuffix();
    }
}
