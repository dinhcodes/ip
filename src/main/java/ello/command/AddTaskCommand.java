package ello.command;

import ello.task.Task;
import ello.storage.TaskList;
import ello.ui.Ui;

public class AddTaskCommand implements Command {
    private final Task addedTask;

    public AddTaskCommand(Task addedTask) {
        this.addedTask = addedTask;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.add(addedTask);
        ui.showMessage("Got it. I've added this ello.task:\n  "
                + addedTask + "\n"
                + ui.countTasks(taskList.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
