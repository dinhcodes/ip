package main.java.storage;

import main.java.exception.TaskIndexOutOfBoundsException;
import main.java.task.Task;
import java.util.ArrayList;

/**
 * Represents a list of {@link Task}s, stored in an {@code ArrayList<Task>}.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task {@link Task} to the task list.
     * @param task The {@link Task}to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list based on its zero-based index.
     * @param zeroBasedIndex The zero-based index of the task to be deleted.
     * @throws TaskIndexOutOfBoundsException if the provided index is out of bounds.
     */
    public void deleteFromIndex(int zeroBasedIndex) {
        if (zeroBasedIndex < 0 || zeroBasedIndex >= size()) {
            throw new TaskIndexOutOfBoundsException(zeroBasedIndex + 1);
        } else {
            tasks.remove(zeroBasedIndex);
        }
    }

    /**
     * Retrieves a task from the task list based on its zero-based index.
     * @param zeroBasedIndex The zero-based index of the task to be retrieved.
     * @return The {@link Task} at the specified index.
     * @throws IndexOutOfBoundsException if the provided index is out of bounds.
     */
    public Task getTask(int zeroBasedIndex) {
        if (zeroBasedIndex < 0 || zeroBasedIndex >= size()) {
            throw new IndexOutOfBoundsException("Task index out of bounds.");
        } else {
            return tasks.get(zeroBasedIndex);
        }
    }

    /**
     * Returns the number of tasks in the task list.
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i));
            if (i != tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
