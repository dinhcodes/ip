package ello.command;

import ello.storage.TaskList;
import ello.task.Task;
import ello.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddTaskCommand implements Command {
    private final Task newTask;

    public AddTaskCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * Executes the add task command by adding the specified task to the task list.
     *
     * @param taskList The task list to add the task to.
     * @param ui       The user interface for displaying messages to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.add(newTask);
        ui.showMessage("Got it. I've added this task:\n  "
                + newTask + "\n"
                + ui.countTasks(taskList.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
