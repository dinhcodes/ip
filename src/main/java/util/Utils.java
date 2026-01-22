package main.java.util;

public class Utils {
    private Utils() {
    }

    public static String wrapWithLine(String message) {
        return AppConstants.LINE + "\n" + message + "\n" + AppConstants.LINE;
    }

    public static String extractTask(String fullCommand, String commandStart) {
        return fullCommand.substring(commandStart.length() + 1);
    }

    public static int extractIndex(String fullCommand, String commandStart) throws IllegalArgumentException {
        String indexStr = fullCommand.substring(commandStart.length() + 1).trim();
        try {
            return Integer.parseInt(indexStr) - 1; // Convert to zero-based index
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid index: " + indexStr);
        }
    }
}
