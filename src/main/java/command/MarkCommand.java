package main.java.command;

import main.java.task.Task;
import main.java.storage.TaskList;
import main.java.ui.Ui;

public class MarkCommand implements Command {
    private final int index;
    private final boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task = taskList.getTask(index);
        if (isDone) {
            task.markAsDone();
            ui.showMessage("Nice! I've marked this task as done:\n  " + task);
        } else {
            task.markAsUndone();
            ui.showMessage("OK, I've marked this task as not done yet:\n  " + task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
