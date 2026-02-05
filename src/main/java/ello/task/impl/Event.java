package ello.task.impl;

import ello.command.exception.InvalidEventTimeRangeException;
import ello.command.parser.DateTimeParser;
import ello.task.Task;
import ello.task.TaskType;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * Represents an event {@link Task} with a description, start time, and end time.
 */
public class Event extends Task {
    /** The start date/time of the event. */
    private final LocalDateTime from;
    /** The end date/time of the event. */
    private final LocalDateTime to;

    /**
     * Constructs an event {@link Task} with the given description, start time, and end time.
     * @param taskDescription {@code String} that represents the description of the {@link Task}.
     * @param from {@code LocalDateTime} that represents the start time of the event.
     * @param to {@code LocalDateTime} that represents the end time of the event.
     */
    public Event(String taskDescription, LocalDateTime from, LocalDateTime to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an event {@link Task} using the provided description and marker-to-description map.
     * Parses the date strings from the "/from" and "/to" markers into {@link LocalDateTime}.
     *
     * @param taskDescription {@code String} that represents the description of the {@link Task}.
     * @param markerToDescMap {@code HashMap<String, String>} that represents markers and their corresponding descriptions.
     * @return A new {@code Event}.
     */
    public static Event create(String taskDescription, HashMap<String, String> markerToDescMap) {
        String fromString = markerToDescMap.get("from");
        String toString = markerToDescMap.get("to");
        LocalDateTime fromDateTime = DateTimeParser.parse(fromString);
        LocalDateTime toDateTime = DateTimeParser.parse(toString);

        // Check if it's a whole-day event and adjust the end time accordingly
        toDateTime = processIfIsAWholeDayEvent(fromDateTime, toDateTime);

        // Validate that the end time is after the start time
        checkForValidEventTimeRange(toDateTime, fromDateTime);

        return new Event(taskDescription, fromDateTime, toDateTime);
    }

    private static void checkForValidEventTimeRange(LocalDateTime toDateTime, LocalDateTime fromDateTime) {
        if (toDateTime.isBefore(fromDateTime) || toDateTime.equals(fromDateTime)) {
            throw new InvalidEventTimeRangeException(fromDateTime, toDateTime);
        }
    }

    private static LocalDateTime processIfIsAWholeDayEvent(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        if (isWholeDayEvent(fromDateTime, toDateTime)) {
            toDateTime = toDateTime.withHour(23).withMinute(59).withSecond(59);
        }
        return toDateTime;
    }

    /**
     * Gets the start date/time of the event.
     *
     * @return The {@link LocalDateTime} representing when this event starts.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Gets the end date/time of the event.
     *
     * @return The {@link LocalDateTime} representing when this event ends.
     */
    public LocalDateTime getTo() {
        return to;
    }

    private static boolean isWholeDayEvent(LocalDateTime from, LocalDateTime to) {
        return from.toLocalDate().equals(to.toLocalDate())
                && from.getHour() == 0 && from.getMinute() == 0
                && to.getHour() == 0 && to.getMinute() == 0;
    }
    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    @Override
    public HashMap<String, String> getMapOfMarkersAndDescription() {
        HashMap<String, String> map = new HashMap<>();
        map.put("from", from.toString());
        map.put("to", to.toString());
        return map;
    }

    @Override
    protected String detailsSuffix() {
        return String.format(" (from: %s to: %s)",
                DateTimeParser.format(from), DateTimeParser.format(to));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
