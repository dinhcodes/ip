package main.java.command;

import main.java.task.Task;
import main.java.storage.TaskList;
import main.java.ui.Ui;

public class AddTaskCommand implements Command {
    private final String fullCommand;

    public AddTaskCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task added = taskList.addFromCommand(fullCommand);
        if (taskList.size() == 1) {
            ui.showMessage("Got it. I've added this task:\n  " + added +
                    "\nNow you have 1 task in the list.");
            return;
        }
        ui.showMessage("Got it. I've added this task:\n  " + added +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
