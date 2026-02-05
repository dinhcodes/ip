package ello.command;

import ello.command.exception.InvalidCommandException;
import ello.task.Task;
import ello.storage.TasksList;
import ello.ui.Ui;

public class MarkCommand implements Command {
    private final int index;
    private final boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TasksList tasksList, Ui ui) {
        Task task;
        try {
            task = tasksList.getTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Task index " + (index + 1) + " out of bounds. Please provide a valid ello.task number.");
        }
        if (isDone) {
            task.markAsDone();
            ui.showMessage("Nice! I've marked this ello.task as done:\n  " + task);
        } else {
            task.markAsUndone();
            ui.showMessage("OK, I've marked this ello.task as not done yet:\n  " + task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
