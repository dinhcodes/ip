package main.java.task;

import java.util.HashMap;

public class Deadline extends Task {
    private final String by;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    public static Deadline create(String taskDescription, HashMap<String, String> markerToDescMap) {
        return new Deadline(taskDescription,
                markerToDescMap.get("/by"));
    }

    @Override
    protected String detailsSuffix() {
        return String.format(" (by: %s)", by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

}
