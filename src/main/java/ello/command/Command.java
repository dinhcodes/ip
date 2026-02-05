package ello.command;

import ello.storage.TaskList;
import ello.ui.Ui;

public interface Command {
    void execute(TaskList taskList, Ui ui);

    boolean isExit();
}
