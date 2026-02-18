package ello.logic.command;

import java.util.Collections;
import java.util.List;

import ello.logic.command.exception.TaskIndexOutOfBoundsException;
import ello.model.task.Task;
import ello.model.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final CommandInfo COMMAND_INFO = new CommandInfo(
            "delete",
            "Deletes a task from the list",
            "Syntax: delete <task_number>"
    );

    private final int taskIndex;

    /**
     * Creates a delete command to delete the task at the specified index.
     *
     * @param taskIndex The zero-based index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        super("delete");
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command by deleting the task at the specified index from the task list.
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
            throw new TaskIndexOutOfBoundsException(taskIndex + 1);
        }

        taskList.deleteFromIndex(taskIndex);

        String taskCountMessage = generateTaskCountMessage(taskList.size());
        String feedback = "Deleted Task\n"
                + task + "\n"
                + taskCountMessage;

        return new CommandResult(feedback, getCommandType());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public List<CommandInfo> getCommandInfoList() {
        return Collections.singletonList(COMMAND_INFO);
    }

    /**
     * Generates a message indicating the current count of tasks in the list.
     *
     * @param count The number of tasks.
     * @return A String message indicating the task count.
     */
    private String generateTaskCountMessage(int count) {
        assert (count >= 0) : "Task count should not be negative.";
        if (count == 1) {
            return "Now you have 1 Task in the list.";
        }
        return "Now you have " + count + " tasks in the list.";
    }
}
