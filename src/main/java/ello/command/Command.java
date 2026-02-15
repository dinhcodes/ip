package ello.command;

import ello.storage.TaskList;
import ello.ui.Ui;

/**
 * Represents a command that can be executed within the application.
 */
public interface Command {
    /**
     * Executes the command using the provided TaskList and Ui.
     *
     * @param taskList The {@link TaskList} to operate on.
     * @param ui The {@link Ui} for user interaction.
     */
    void execute(TaskList taskList, Ui ui);

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    boolean isExit();
}
