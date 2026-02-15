package ello.command;

import ello.command.exception.InvalidCommandException;
import ello.storage.TaskList;
import ello.task.Task;
import ello.ui.Ui;

/**
 * Represents a command to mark a task as done or undone in the task list.
 */
public class MarkCommand implements Command {
    private final int index;
    private final boolean isDone;

    /**
     * Creates a mark command to mark or unmark the task at the specified index.
     *
     * @param index  The zero-based index of the task to be marked or unmarked.
     * @param isDone {@code true} to mark the task as done, {@code false} to mark it as undone.
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Executes the mark command by marking or unmarking the task at the specified index.
     *
     * @param taskList The task list to operate on.
     * @param ui       The user interface for user interaction.
     */
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
