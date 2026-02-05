package ello.ui.util;

import ello.common.util.AppConstants;

public class UiConstants {
    /**
     * A line separator used in the UI.
     */
    public static final String LINE = "____________________________________________________________";

    public static final String WELCOME_MESSAGE = "Hello! I'm " + AppConstants.NAME + "\n"
            + "What can I do for you?";
//            + "Type 'help' to see a list of commands.";

    public static final String GOODBYE_MESSAGE = "Goodbye! Hope to see you again soon.";

    /**
     * Wraps the given message with horizontal lines for better readability.
     *
     * @param message The message to be wrapped.
     * @return The message wrapped with horizontal lines.
     */
    public static String wrapWithLine(String message) {
        return LINE + "\n" + message + "\n" + LINE;
    }
}