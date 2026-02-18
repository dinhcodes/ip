package ello.ui.util;

/**
 * Contains CSS style class name constants used throughout the UI.
 * Using constants instead of string literals helps prevent typos and makes refactoring easier.
 */
public class CssStyleConstants {
    // Command-specific styles
    public static final String ADD_COMMAND_TODO = "AddCommand_todo";
    public static final String ADD_COMMAND_DEADLINE = "AddCommand_deadline";
    public static final String ADD_COMMAND_EVENT = "AddCommand_event";
    public static final String CHANGE_MARK_COMMAND = "marked-label";
    public static final String DELETE_COMMAND = "delete-label";
    public static final String LIST_COMMAND = "list-label";
    public static final String FIND_COMMAND = "find-label";

    // System message styles
    public static final String ERROR_COMMAND = "error-label";
    public static final String ERROR_TAIL = "error-tail";
    public static final String SYSTEM_MESSAGE = "system-label";
}
