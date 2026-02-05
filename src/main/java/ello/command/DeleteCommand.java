package ello.command;
import ello.command.exception.TaskIndexOutOfBoundsException;
import ello.ui.Ui;
import ello.storage.TaskList;
import ello.task.Task;

/**
 * Represents a {@link Command} to delete a task from the TaskList.
 */
public class DeleteCommand implements Command {
    private final int taskIndex;

    /**
     * Creates a DeleteCommand to delete the task at the specified index.
     * @param taskIndex The zero-based index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the DeleteCommand by deleting the task at the specified index from the TaskList.
     *
     * @param taskList The {@link TaskList} to operate on.
     * @param ui The {@link Ui} for user interaction.
     */
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
