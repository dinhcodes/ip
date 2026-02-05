package ello.command;
import ello.command.exception.TaskIndexOutOfBoundsException;
import ello.ui.Ui;
import ello.storage.TasksList;
import ello.task.Task;

public class DeleteCommand implements Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TasksList tasksList, Ui ui) {
        Task task;
        try {
            task = tasksList.getTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(taskIndex + 1);
        }
        tasksList.deleteFromIndex(taskIndex);
        ui.showMessage("Deleted Task\n"
                + task + "\n"
                + ui.countTasks(tasksList.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
