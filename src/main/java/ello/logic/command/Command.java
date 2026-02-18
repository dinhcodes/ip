package ello.logic.command;

import ello.model.task.TaskList;

/**
 * Represents a command that can be executed within the application.
 */
public abstract class Command {
    /**
     * The type name of this command.
     */
    protected final String commandTypeName;

    /**
     * Constructor for Command with mandatory command type name.
     *
     * @param commandTypeName The type name of this command.
     */
    protected Command(String commandTypeName) {
        this.commandTypeName = commandTypeName;
    }

    /**
     * Executes the command using the provided TaskList and returns the result.
     *
     * @param taskList The task list to operate on.
     * @return The command result containing the execution result.
     */
    public abstract CommandResult execute(TaskList taskList);

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();

    /**
     * Gets the type of this command.
     *
     * @return The command type as a string.
     */
    public String getCommandType() {
        return commandTypeName;
    }
}
