package main.java.command;

import main.java.task.Task;
import main.java.storage.TaskList;
import main.java.ui.Ui;

public class AddTaskCommand implements Command {
    private Task addedTask;

    public AddTaskCommand(Task addedTask) {
        this.addedTask = addedTask;
    }

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
