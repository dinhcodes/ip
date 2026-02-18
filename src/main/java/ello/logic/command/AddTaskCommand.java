package ello.logic.command;

import ello.model.task.Task;
import ello.model.task.TaskList;

/**
 * Represents a command to add a task to the task list.
 */
public class AddTaskCommand extends Command {
    private final Task addedTask;

    public AddTaskCommand(Task addedTask, String commandWord) {
        super("AddCommand_" + commandWord);
        this.addedTask = addedTask;
    }

    /**
     * Executes the add task command by adding the specified task to the task list.
     *
     * @param taskList The task list to add the task to.
     * @return The command result containing the execution result
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.add(addedTask);

        String taskCountMessage = generateTaskCountMessage(taskList.size());
        String feedback = "Got it. I've added this task:\n  "
                + addedTask + "\n"
                + taskCountMessage;

        return new CommandResult(feedback, getCommandType());
    }

    @Override
    public boolean isExit() {
        return false;
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
