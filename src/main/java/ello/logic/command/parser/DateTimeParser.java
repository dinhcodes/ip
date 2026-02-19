package ello.logic.command.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import ello.logic.command.exception.InvalidDateTimeException;

/**
 * Utility class for parsing date and time strings into {@link LocalDateTime} objects.
 * Supports multiple input formats and provides a consistent output format.
 */
public class DateTimeParser {
    /**
     * Date-time format patterns.
     */
    private static final String[] DATETIME_PATTERNS = {
            "d/M/yyyy HH:mm",
            "d/M/yyyy H:mm",
            "yyyy-MM-dd HH:mm",
    };

    /**
     * Date-only format patterns.
     */
    private static final String[] DATE_ONLY_PATTERNS = {
            "d/M/yyyy",
            "dd/MM/yyyy",
            "yyyy-MM-dd",
    };

    /**
     * Date-time formatters that include both date and time.
     */
    private static final DateTimeFormatter[] DATETIME_FORMATTERS =
            Arrays.stream(DATETIME_PATTERNS)
                    .map(DateTimeFormatter::ofPattern)
                    .toArray(DateTimeFormatter[]::new);

    /**
     * Date-only formatters (time defaults to start of day).
     */
    private static final DateTimeFormatter[] DATE_ONLY_FORMATTERS =
            Arrays.stream(DATE_ONLY_PATTERNS)
                    .map(DateTimeFormatter::ofPattern)
                    .toArray(DateTimeFormatter[]::new);

    /**
     * Output formatter for displaying dates.
     */
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a", java.util.Locale.ENGLISH);

    /**
     * Storage formatter for serializing dates consistently.
     */
    private static final DateTimeFormatter STORAGE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Returns a description of supported date/time formats.
     *
     * @return Formatted string describing supported formats.
     */
    public static String getSupportedFormatsDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Date-time formats:\n");
        Arrays.stream(DATETIME_PATTERNS).forEach(pattern
                -> sb.append("  - ").append(pattern).append("\n"));

        sb.append("Date-only formats:\n");
        Arrays.stream(DATE_ONLY_PATTERNS).forEach(pattern
                -> sb.append("  - ").append(pattern).append("\n"));
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Parses the input string into a {@link LocalDateTime} object.
     *
     * @param input The date/time string to parse.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws InvalidDateTimeException If the input is invalid or does not match any supported format.
     */
    public static LocalDateTime parse(String input) throws InvalidDateTimeException {
        // Validate input
        if (input == null || input.isBlank()) {
            throw new InvalidDateTimeException(input == null ? "null" : input);
        }

        // Try parsing with date-time formatters first
        for (DateTimeFormatter formatter : DATETIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                // Continue trying other formats
            }
        }

        // If date-time parsing fails, try date-only formatters
        for (DateTimeFormatter formatter : DATE_ONLY_FORMATTERS) {
            try {
                LocalDate date = LocalDate.parse(input, formatter);
                return date.atStartOfDay(); // Default time to 00:00
            } catch (DateTimeParseException e) {
                // Continue trying other formats
            }
        }

        // If all parsing attempts fail, throw an exception
        throw new InvalidDateTimeException(input);
    }

    /**
     * Formats a {@link LocalDateTime} object into a string using the output formatter.
     *
     * @param dateTime The LocalDateTime to format.
     * @return A formatted string representation of the date and time, or an empty string if the input is null.
     */
    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(OUTPUT_FORMATTER);
    }

    /**
     * Formats a {@link LocalDateTime} object into a string for storage.
     * Uses a consistent format (yyyy-MM-dd HH:mm) to ensure reliable serialization and deserialization.
     *
     * @param dateTime The LocalDateTime to format for storage.
     * @return A formatted string in storage format, or an empty string if the input is null.
     */
    public static String formatForStorage(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(STORAGE_FORMATTER);
    }
}
