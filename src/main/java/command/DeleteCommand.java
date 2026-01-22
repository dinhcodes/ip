package main.java.command;
import main.java.exception.TaskIndexOutOfBoundsException;
import main.java.ui.Ui;
import main.java.storage.TaskList;
import main.java.task.Task;

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
        ui.showMessage("Deleted task\n"
                + task + "\n"
                + ui.countTasks(taskList.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
