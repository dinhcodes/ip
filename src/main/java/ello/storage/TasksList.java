package ello.storage;

import ello.command.exception.TaskIndexOutOfBoundsException;
import ello.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a list of {@link Task}s, stored in an {@code ArrayList<Task>}.
 */
public class TasksList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a ello.task {@link Task} to the ello.task list.
     * @param task The {@link Task}to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a ello.task from the ello.task list based on its zero-based index.
     * @param zeroBasedIndex The zero-based index of the ello.task to be deleted.
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
     * Retrieves a ello.task from the ello.task list based on its zero-based index.
     * @param zeroBasedIndex The zero-based index of the ello.task to be retrieved.
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
     * Returns an unmodifiable view of the ello.task list.
     * Useful for iteration and persistence operations.
     *
     * @return An unmodifiable {@code List<Task>} containing all tasks.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Replaces all tasks in the list with the provided tasks.
     * Useful for loading tasks from ello.storage.
     *
     * @param newTasks The list of tasks to replace the current tasks with.
     */
    public void replaceAll(List<Task> newTasks) {
        tasks.clear();
        tasks.addAll(newTasks);
    }

    /**
     * Returns the number of tasks in the ello.task list.
     * @return The size of the ello.task list.
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
