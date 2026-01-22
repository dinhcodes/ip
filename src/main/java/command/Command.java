package main.java.command;

import main.java.storage.TaskList;
import main.java.ui.Ui;

public interface Command {
    void execute(TaskList taskList, Ui ui);

    boolean isExit();
}
