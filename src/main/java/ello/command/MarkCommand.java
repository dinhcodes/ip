package ello.command;

import ello.command.exception.InvalidCommandException;
import ello.task.Task;
import ello.storage.TaskList;
import ello.ui.Ui;

public class MarkCommand implements Command {
    private final int index;
    private final boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task;
        try {
            task = taskList.getTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Task index " + (index + 1) + " out of bounds. Please provide a valid task number.");
        }
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
