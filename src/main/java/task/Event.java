package main.java.task;

import java.util.HashMap;

/**
 * Represents an event {@link Task} with a description, start time, and end time.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Constructs an event {@link Task} with the given description, start time, and end time.
     * @param taskDescription {@code String} that represents the description of the task.
     * @param from {@code String} that represents the start time of the event.
     * @param to {@code String} that represents the end time of the event.
     */
    public Event(String taskDescription, String from, String to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an event {@link Task} using the provided description and marker-to-description map.
     * @param taskDescription {@code String} that represents the description of the task.
     * @param markerToDescMap {@code HashMap<String, String>} that represents markers and their corresponding descriptions.
     * @return A new {@code Event} task.
     */
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
