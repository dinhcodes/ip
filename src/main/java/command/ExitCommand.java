package main.java.command;

import main.java.storage.TaskList;
import main.java.ui.Ui;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}