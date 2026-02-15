package ello.command.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import ello.command.exception.InvalidDateTimeException;

/**
 * Utility class for parsing date and time strings into {@link LocalDateTime} objects.
 * Supports multiple input formats and provides a consistent output format.
 */
public class DateTimeParser {

    /**
     * Output format for displaying dates: e.g., "Dec 02 2019, 6:00 PM"
     */
    public static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * List of supported input formats for parsing dates, in {@code String} format.
     */
    private static final List<String> DATE_TIME_PATTERNS = Arrays.asList(
            "d/M/yyyy HH:mm",
            "dd/MM/yyyy HH:mm",
            "yyyy-mm-dd HH:mm"
    );

    /**
     * Formats for date-only input (default time to 00:00).
     */
    private static final List<String> DATE_ONLY_PATTERNS = Arrays.asList(
            "d/M/yyyy",
            "dd/MM/yyyy",
            "yyyy-mm-dd HH:mm"
    );

    private static final List<DateTimeFormatter> INPUT_FORMATS = DATE_TIME_PATTERNS.stream()
            .map(DateTimeFormatter::ofPattern)
            .toList();

    private static final List<DateTimeFormatter> DATE_ONLY_FORMATS = DATE_ONLY_PATTERNS.stream()
            .map(DateTimeFormatter::ofPattern)
            .toList();

    /**
     * Returns a formatted string of all supported date/time formats.
     *
     * @return A string listing all supported formats.
     */
    public static String getSupportedFormatsDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Date-time formats:\n");
        for (String pattern : DATE_TIME_PATTERNS) {
            sb.append("  - ").append(pattern).append("\n");
        }
        sb.append("Date-only formats:\n");
        for (String pattern : DATE_ONLY_PATTERNS) {
            sb.append("  - ").append(pattern).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Parses a date/time string into a {@link LocalDateTime} object.
     * Tries multiple formats and returns the first successful parse.
     *
     * @param dateTimeString The string to parse.
     * @return The parsed {@link LocalDateTime}.
     * @throws InvalidDateTimeException if the string cannot be parsed with any supported format.
     */
    public static LocalDateTime parse(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.trim().isEmpty()) {
            throw new InvalidDateTimeException("(empty)");
        }

        String trimmed = dateTimeString.trim();

        // Try date-time formats
        for (DateTimeFormatter formatter : INPUT_FORMATS) {
            try {
                return LocalDateTime.parse(trimmed, formatter);
            } catch (DateTimeParseException e) {
            }
        }

        // Try date-only formats
        for (DateTimeFormatter formatter : DATE_ONLY_FORMATS) {
            try {
                return java.time.LocalDate.parse(trimmed, formatter).atStartOfDay();
            } catch (DateTimeParseException e) {
            }
        }


        throw new InvalidDateTimeException(dateTimeString);
    }

    /**
     * Formats a {@link LocalDateTime} to a user-friendly {@code String}.
     *
     * @param dateTime The {@link LocalDateTime} to format.
     * @return The formatted {@code String}, e.g., "Dec 02 2019, 6:00 PM".
     */
    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(OUTPUT_FORMAT);
    }
}
