package ello.command;

import ello.storage.TasksList;
import ello.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TasksList tasksList, Ui ui) {
        if (tasksList.size() == 0) {
            ui.showMessage("Your ello.task list is empty.");
            return;
        }
        ui.showMessage("Here are the tasks in your list:\n" + tasksList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
