package ello.logic.command;

import ello.logic.command.exception.InvalidCommandException;
import ello.model.task.Task;
import ello.model.task.TaskList;

/**
 * Represents a command to mark a task as done or undone in the task list.
 */
public class MarkCommand extends Command {
    private final int taskIndex;
    private final boolean isDone;

    /**
     * Creates a mark command to mark or unmark the task at the specified index.
     *
     * @param taskIndex The zero-based index of the task to be marked or unmarked.
     * @param isDone    True to mark the task as done, false to mark it as undone.
     */
    public MarkCommand(int taskIndex, boolean isDone) {
        super("ChangeMarkCommand");
        this.taskIndex = taskIndex;
        this.isDone = isDone;
    }

    /**
     * Executes the mark command by marking or unmarking the task at the specified index.
     *
     * @param taskList The task list to operate on.
     * @return The command result containing the execution result
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        Task task;
        try {
            task = taskList.getTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Task index " + (taskIndex + 1) + " out of bounds. Please provide a valid task number.");
        }

        String feedback;
        if (isDone) {
            task.markAsDone();
            feedback = "Nice! I've marked this task as done:\n  " + task;
        } else {
            task.markAsUndone();
            feedback = "OK, I've marked this task as not done yet:\n  " + task;
        }

        return new CommandResult(feedback, getCommandType());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
