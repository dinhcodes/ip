package ello.command;

import ello.storage.TaskList;
import ello.ui.Ui;

/**
 * Represents a {@link Command} to list all tasks in the TaskList.
 */
public class ListCommand implements Command {
    /**
     * Executes the ListCommand by displaying all tasks in the TaskList.
     *
     * @param taskList The {@link TaskList} to operate on.
     * @param ui The {@link Ui} for user interaction.
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
