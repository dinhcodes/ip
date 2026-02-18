package ello.logic.command.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;

import ello.logic.command.exception.InvalidDateTimeException;

/**
 * Utility class for parsing date and time strings into {@link LocalDateTime} objects.
 * Supports multiple input formats and provides a consistent output format.
 */
public class DateTimeParser {
    /**
     * Output format for displaying dates: e.g., "Dec 02 2019, 6:00 PM"
     */
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a", Locale.ENGLISH);

    /**
     * Date-time formatters that include both date and time.
     */
    private static final DateTimeFormatter[] DATETIME_FORMATTERS = {
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d/M/yyyy H:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    };

    /**
     * Date-only formatters (time defaults to start of day).
     */
    private static final DateTimeFormatter[] DATE_ONLY_FORMATTERS = {
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
    };

    /**
     * Parses a date/time string into a {@link LocalDateTime} object.
     * Tries multiple formats and returns the first successful parse.
     * Accepts optional custom formatters via varargs.
     *
     * @param dateTimeStr      The string to parse.
     * @param customFormatters Optional custom formatters to try first.
     * @return The parsed {@link LocalDateTime}.
     * @throws InvalidDateTimeException if the string cannot be parsed with any supported format.
     */
    public static LocalDateTime parse(String dateTimeStr, DateTimeFormatter... customFormatters) {
        if (dateTimeStr == null || dateTimeStr.isBlank()) {
            throw new InvalidDateTimeException(dateTimeStr);
        }

        String trimmed = dateTimeStr.trim();

        // Try custom formatters first if provided
        for (DateTimeFormatter formatter : customFormatters) {
            try {
                return LocalDateTime.parse(trimmed, formatter);
            } catch (DateTimeParseException e) {
                // Try as date-only
                try {
                    return LocalDate.parse(trimmed, formatter).atStartOfDay();
                } catch (DateTimeParseException e2) {
                    // Continue to next format
                }
            }
        }

        // Try date-time formats
        for (DateTimeFormatter formatter : DATETIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(trimmed, formatter);
            } catch (DateTimeParseException e) {
                // Continue to next format
            }
        }

        // Try date-only formats (defaults to start of day)
        for (DateTimeFormatter formatter : DATE_ONLY_FORMATTERS) {
            try {
                return LocalDate.parse(trimmed, formatter).atStartOfDay();
            } catch (DateTimeParseException e) {
                // Continue to next format
            }
        }

        throw new InvalidDateTimeException(dateTimeStr);
    }

    /**
     * Returns a description of supported date/time formats.
     *
     * @return Formatted string describing supported formats.
     */
    public static String getSupportedFormatsDescription() {
        StringBuilder sb = new StringBuilder("Supported formats:\n");
        sb.append("Date-time formats:\n");
        Arrays.stream(DATETIME_FORMATTERS).forEach(formatter
                -> sb.append("  - ").append(formatter.toString()).append("\n"));
        sb.append("Date-only formats:\n");
        Arrays.stream(DATE_ONLY_FORMATTERS).forEach(formatter
                -> sb.append("  - ").append(formatter.toString()).append("\n"));
        return sb.toString();
    }

    /**
     * Formats a date / time to a user-friendly {@code String}.
     *
     * @param dateTime The date / time to format.
     * @return The formatted {@code String}, e.g., "Dec 02 2019, 6:00 PM".
     */
    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(OUTPUT_FORMAT);
    }
}
