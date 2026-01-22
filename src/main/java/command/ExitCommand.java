package main.java.command;

import main.java.storage.TaskList;
import main.java.ui.Ui;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}