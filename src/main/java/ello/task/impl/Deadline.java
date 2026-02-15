package ello.task.impl;

import java.time.LocalDateTime;
import java.util.HashMap;

import ello.command.parser.DateTimeParser;
import ello.task.Task;
import ello.task.TaskType;

/**
 * Represents a deadline {@link Task} with a specific due date or time.
 */
public class Deadline extends Task {
    /**
     * The due date/time of the deadline.
     */
    private final LocalDateTime by;

    /**
     * Constructs a Deadline {@link Task} with the given description and due date/time.
     *
     * @param taskDescription {@code String} that represents the description of the {@link Task}
     * @param by              {@code LocalDateTime} that represents due date or time for the deadline.
     */
    public Deadline(String taskDescription, LocalDateTime by) {
        super(taskDescription);
        this.by = by;
    }

    /**
     * Creates a Deadline {@link Task} using the provided description and marker-to-description map.
     * Parses the date string from the "/by" marker into a {@link LocalDateTime}.
     *
     * @param taskDescription {@code String} that represents the description of the {@link Task}k
     * @param markerToDescMap {@code HashMap<String, String>} that represents markers and their corresponding descriptions.
     * @return A new {@code Deadline} {@link Task}.
     */
    public static Deadline create(String taskDescription, HashMap<String, String> markerToDescMap) {
        String byString = markerToDescMap.get("by");
        LocalDateTime byDateTime = DateTimeParser.parse(byString);
        return new Deadline(taskDescription, byDateTime);
    }

    /**
     * Gets the deadline date/time.
     *
     * @return The {@link LocalDateTime} representing when this {@link Task} is due.
     */
    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    @Override
    public HashMap<String, String> getMapOfMarkersAndDescription() {
        HashMap<String, String> map = new HashMap<>();
        map.put("by", by.toString());
        return map;
    }

    @Override
    protected String detailsSuffix() {
        return String.format(" (by: %s)", DateTimeParser.format(by));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

}
