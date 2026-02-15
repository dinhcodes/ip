package ello.task;

import java.util.HashMap;

/**
 * Abstract class that represents a generic {@link Task} with a description and completion status.
 */
public abstract class Task {
    private final String taskDescription;
    private final HashMap<String, String> mapOfMarkersAndDescription = new HashMap<>();
    private boolean isDone;

    /**
     * Constructs a {@code Task} with the given description.
     *
     * @param taskDescription {@code String} that represents the description of the {@link Task}.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the given description and map of markers to descriptions.
     *
     * @param taskDescription            {@code String} that represents the description of the {@link Task}.
     * @param mapOfMarkersAndDescription A HashMap mapping marker names to their descriptions.
     */
    public Task(String taskDescription, HashMap<String, String> mapOfMarkersAndDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        this.mapOfMarkersAndDescription.putAll(mapOfMarkersAndDescription);
    }

    /**
     * Gets the {@link TaskType} of this {@link Task}.
     *
     * @return The {@link TaskType} enum value for this {@link Task}.
     */
    public abstract TaskType getTaskType();

    /**
     * Converts this {@link Task}'s type-specific fields to a map of marker names to string values.
     * Used for serialization. Subclasses should override to provide their specific fields.
     *
     * @return A HashMap with marker names (e.g., "by", "from", "to") mapped to {@code String} values.
     */
    public HashMap<String, String> getMapOfMarkersAndDescription() {
        return mapOfMarkersAndDescription;
    }

    /**
     * Gets the description of this {@link Task}.
     *
     * @return The {@link Task} description.
     */
    public String getDescription() {
        return taskDescription;
    }

    /**
     * Returns whether this {@link Task} is marked as done.
     *
     * @return {@code true} if the {@link Task} is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the {@link Task} as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the {@link Task} as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns detailed text that comes after the taskDescription, for example: date
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task other = (Task) obj;
        return this.isDone == other.isDone
                && this.taskDescription.equals(other.taskDescription)
                && this.getMapOfMarkersAndDescription().equals(other.getMapOfMarkersAndDescription());
    }

    @Override
    public int hashCode() {
        int result = taskDescription.hashCode();
        result = 31 * result + (isDone ? 1 : 0);
        result = 31 * result + getMapOfMarkersAndDescription().hashCode();
        return result;
    }
}
