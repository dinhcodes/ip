package ello.ui.util;

/**
 * Contains CSS style class name constants used throughout the UI.
 * Using constants instead of string literals helps prevent typos and makes refactoring easier.
 */
public class CssStyleConstants {
    // Command-specific styles
    public static final String ADD_COMMAND_TODO = "add-todo-label";
    public static final String ADD_COMMAND_DEADLINE = "add-deadline-label";
    public static final String ADD_COMMAND_EVENT = "add-event-label";
    public static final String CHANGE_MARK_COMMAND = "marked-label";
    public static final String DELETE_COMMAND = "delete-label";
    public static final String LIST_COMMAND = "list-label";
    public static final String FIND_COMMAND = "find-label";
    public static final String HELP_MESSAGE = "help-label";

    // System message styles
    public static final String ERROR_COMMAND = "error-label";
    public static final String ERROR_TAIL = "error-tail";
    public static final String HELP_TAIL = "help-tail";
    public static final String SYSTEM_MESSAGE = "system-label";
}
