package ello.command;

import ello.task.Task;
import ello.storage.TaskList;
import ello.ui.Ui;

/**
 * Represents a {@link Command} to add a task to the TaskList.
 */
public class AddTaskCommand implements Command {
    private final Task addedTask;

    public AddTaskCommand(Task addedTask) {
        this.addedTask = addedTask;
    }

    /**
     * Executes the AddTaskCommand by adding the specified task to the TaskList
     * @param taskList the {@link TaskList} to add the task to
     * @param ui the {@link Ui} to display messages to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.add(addedTask);
        ui.showMessage("Got it. I've added this task:\n  "
                + addedTask + "\n"
                + ui.countTasks(taskList.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
