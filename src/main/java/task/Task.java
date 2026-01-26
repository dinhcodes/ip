package main.java.task;

import java.util.HashMap;

public abstract class Task {
    private final String taskDescription;
    private final HashMap<String, String> mapOfMarkersAndDescription = new HashMap<>();
    private boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public Task(String taskDescription, HashMap<String, String> mapOfMarkersAndDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        this.mapOfMarkersAndDescription.putAll(mapOfMarkersAndDescription);
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /** Returns detailed text that comes after the taskDescription, for example: date **/
    protected String detailsSuffix() {
        return "";
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + taskDescription + detailsSuffix();
    }
}
