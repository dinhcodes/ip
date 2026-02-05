package ello.command;

import ello.storage.TasksList;
import ello.ui.Ui;

public class ExitCommand implements Command {
    @Override
    public void execute(TasksList tasksList, Ui ui) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}