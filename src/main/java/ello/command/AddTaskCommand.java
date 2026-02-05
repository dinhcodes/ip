package ello.command;

import ello.task.Task;
import ello.storage.TasksList;
import ello.ui.Ui;

public class AddTaskCommand implements Command {
    private final Task addedTask;

    public AddTaskCommand(Task addedTask) {
        this.addedTask = addedTask;
    }

    @Override
    public void execute(TasksList tasksList, Ui ui) {
        tasksList.add(addedTask);
        ui.showMessage("Got it. I've added this ello.task:\n  "
                + addedTask + "\n"
                + ui.countTasks(tasksList.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
