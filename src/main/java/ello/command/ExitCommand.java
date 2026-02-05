package ello.command;

import ello.storage.TaskList;
import ello.ui.Ui;

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