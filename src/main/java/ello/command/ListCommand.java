package ello.command;

import ello.storage.TaskList;
import ello.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {
    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param taskList The task list to operate on.
     * @param ui       The user interface for user interaction.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (taskList.size() == 0) {
            ui.showMessage("Your task list is empty.");
            return;
        }
        ui.showMessage("Here are the tasks in your list:\n" + taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
