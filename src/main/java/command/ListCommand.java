package main.java.command;

import main.java.storage.TaskList;
import main.java.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showMessage("Here are the tasks in your list:\n" + taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
