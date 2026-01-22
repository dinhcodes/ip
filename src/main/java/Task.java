package main.java;

public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    public Task returnTaskFromCommandArgs(String command) {
        return this;
    }

    /** Returns detailed text that comes after the description, for example: date **/
    protected String detailsSuffix() {
        return "";
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description + detailsSuffix();
    }
}
