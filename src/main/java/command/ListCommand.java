package main.java.command;

import main.java.storage.TaskList;
import main.java.ui.Ui;

public class ListCommand implements Command {
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
