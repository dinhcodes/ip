package main.java.task;

import java.util.HashMap;

/**
 * Represents a deadline {@link Task} with a specific due date or time.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructs a Deadline task with the given description and due date/time.
     *
     * @param taskDescription {@code String} that represents the description of the task
     * @param by {@code String} that represents due date or time for the deadline.
     */
    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    /**
     * Creates a Deadline task using the provided description and marker-to-description map.
     * @param taskDescription {@code String} that represents the description of the task
     * @param markerToDescMap {@code HashMap<String, String>} that represents markers and their corresponding descriptions.
     * @return A new {@code Deadline} task.
     */
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
