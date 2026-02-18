package ello.logic.command;

/**
 * Represents the result of executing a command.
 * Encapsulates the feedback to show to the user and metadata about the command execution.
 */
public class CommandResult {
    /**
     * The feedback message to display to the user.
     */
    private final String feedbackToUser;

    /**
     * Whether the application should exit after this command.
     */
    private final boolean exit;

    /**
     * The type of command that was executed.
     */
    private final String typeOfCommand;

    /**
     * Creates a CommandResult with the specified feedback, exit status, and command type.
     *
     * @param feedbackToUser The feedback message to display to the user.
     * @param exit           Whether the application should exit after this command.
     * @param typeOfCommand  The type of command that was executed.
     */
    public CommandResult(String feedbackToUser, boolean exit, String typeOfCommand) {
        this.feedbackToUser = feedbackToUser;
        this.exit = exit;
        this.typeOfCommand = typeOfCommand;
    }

    /**
     * Creates a CommandResult for a non-exit command (exit set to false, showHelp set to false).
     *
     * @param feedbackToUser The feedback message to display to the user.
     * @param typeOfCommand  The type of command that was executed.
     */
    public CommandResult(String feedbackToUser, String typeOfCommand) {
        this(feedbackToUser, false, typeOfCommand);
    }

    /**
     * Gets the feedback message to display to the user.
     *
     * @return The feedback message.
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Gets whether the application should exit.
     *
     * @return True if the application should exit, false otherwise.
     */
    public boolean shouldExit() {
        return exit;
    }

    /**
     * Gets the type of command that was executed.
     *
     * @return The command type.
     */
    public String getTypeOfCommand() {
        return typeOfCommand;
    }
}
