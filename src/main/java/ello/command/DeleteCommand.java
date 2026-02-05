package ello.command;
import ello.command.exception.TaskIndexOutOfBoundsException;
import ello.ui.Ui;
import ello.storage.TaskList;
import ello.task.Task;

public class DeleteCommand implements Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task;
        try {
            task = taskList.getTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(taskIndex + 1);
        }
        taskList.deleteFromIndex(taskIndex);
        ui.showMessage("Deleted Task\n"
                + task + "\n"
                + ui.countTasks(taskList.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
