package ello.command;

import ello.storage.TasksList;
import ello.ui.Ui;

public interface Command {
    void execute(TasksList tasksList, Ui ui);

    boolean isExit();
}
