package main.java.util;

import main.java.exception.InvalidCommandException;

public class Utils {
    private Utils() {
        // Prevent instantiation
    }

    /**
     * Wraps the given message with horizontal lines for better readability.
     *
     * @param message The message to be wrapped.
     * @return The message wrapped with horizontal lines.
     */
    public static String wrapWithLine(String message) {
        return AppConstants.LINE + "\n" + message + "\n" + AppConstants.LINE;
    }

    /**
     * Extracts the task index from a full command string. Assumes that the
     * command is in the format: {@code <index> <commandStart>}.
     *
     * @param fullCommand The complete command string entered by the user.
     * @param commandStart The starting keyword of the command (e.g., "delete", "done").
     * @return The zero-based index of the task.
     * @throws InvalidCommandException If the index is not a valid integer.
     */
    public static int extractTaskIndex(String fullCommand, String commandStart) {
        String indexStr = fullCommand.substring(commandStart.length() + 1).trim();
        try {
            return Integer.parseInt(indexStr) - 1; // Convert to zero-based index
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid index: " + indexStr + " . Please provide a valid number.");
        }
    }
}
