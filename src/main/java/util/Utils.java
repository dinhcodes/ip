package main.java.util;

import main.java.exception.InvalidCommandException;

public class Utils {
    private Utils() {
    }

    public static String wrapWithLine(String message) {
        return AppConstants.LINE + "\n" + message + "\n" + AppConstants.LINE;
    }

    public static String extractTask(String fullCommand, String commandStart) {
        return fullCommand.substring(commandStart.length() + 1).trim();
    }

    public static int extractTaskIndex(String fullCommand, String commandStart) {
        String indexStr = fullCommand.substring(commandStart.length() + 1).trim();
        try {
            return Integer.parseInt(indexStr) - 1; // Convert to zero-based index
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid index: " + indexStr + " . Please provide a valid number.");
        }
    }
}
