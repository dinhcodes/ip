package ello.command.exception;

import java.time.LocalDateTime;

/**
 * Exception thrown when event time range is invalid.
 */
public class InvalidEventTimeRangeException extends InvalidCommandException {

    /**
     * Constructs exception when end time is before or equal to start time.
     *
     * @param from The start date/time.
     * @param to The end date/time.
     */
    public InvalidEventTimeRangeException(LocalDateTime from, LocalDateTime to) {
        super(buildMessage(from, to));
    }

    private static String buildMessage(LocalDateTime from, LocalDateTime to) {
        if (from.equals(to)) {
            return "Event start and end times cannot be the same: " + from + "\n"
                    + "For a whole-day event, specify the same date for both from and to fields without time.";
        }
        return "Event end time (" + to + ") cannot be before start time (" + from + ").";
    }
}
