package ello.model.task.impl;

import java.time.LocalDateTime;
import java.util.HashMap;

import ello.logic.command.parser.DateTimeParser;
import ello.model.task.Task;
import ello.model.task.TaskType;

/**
 * Represents a deadline task with a specific due date or time.
 */
public class Deadline extends Task {
    /**
     * The due date/time of the deadline.
     */
    private final LocalDateTime by;

    /**
     * Constructs a deadline task with the given description and due date/time.
     *
     * @param taskDescription The description of the task.
     * @param by              The due date or time for the deadline.
     */
    public Deadline(String taskDescription, LocalDateTime by) {
        super(taskDescription);
        this.by = by;
    }

    /**
     * Creates a deadline task using the provided description and marker-to-description map.
     * Parses the date string from the "/by" marker into a {@link LocalDateTime}.
     *
     * @param taskDescription The description of the task.
     * @param markerToDescMap Markers and their corresponding descriptions.
     * @return A new deadline task.
     */
    public static Deadline create(String taskDescription, HashMap<String, String> markerToDescMap) {
        String byString = markerToDescMap.get("by");
        LocalDateTime byDateTime = DateTimeParser.parse(byString);
        return new Deadline(taskDescription, byDateTime);
    }

    /**
     * Gets the deadline date/time.
     *
     * @return The date/time when this task is due.
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
