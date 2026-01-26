package main.java.task;

import java.util.HashMap;

public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String taskDescription, String from, String to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
    }

    public static Event create(String taskDescription, HashMap<String, String> markerToDescMap) {
        return new Event(taskDescription,
                markerToDescMap.get("/from"),
                markerToDescMap.get("/to")
                );
    }

    @Override
    protected String detailsSuffix() {
        return String.format(" (from: %s to: %s)", from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
