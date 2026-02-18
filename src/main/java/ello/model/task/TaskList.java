package ello.model.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ello.logic.command.exception.TaskIndexOutOfBoundsException;

/**
 * Represents a list of task, stored in an {@code ArrayList<Task>}.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
        assert tasks.contains(task) : "Task should be in list after adding";
    }

    /**
     * Deletes a task from the task list based on its zero-based index.
     *
     * @param zeroBasedIndex The zero-based index of the task to be deleted.
     * @throws TaskIndexOutOfBoundsException if the provided index is out of bounds.
     */
    public void deleteFromIndex(int zeroBasedIndex) {
        int sizeBefore = size();

        if (zeroBasedIndex < 0 || zeroBasedIndex >= size()) {
            throw new TaskIndexOutOfBoundsException(zeroBasedIndex + 1);
        } else {
            tasks.remove(zeroBasedIndex);
            assert size() == sizeBefore - 1 : "Task list size should decrease by 1 after deletion.";
        }
    }

    /**
     * Retrieves a task from the task list based on its zero-based index.
     *
     * @param zeroBasedIndex The zero-based index of the task to be retrieved.
     * @return The task at the specified index.
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
     * Returns an unmodifiable view of the task list.
     * Useful for iteration and persistence operations.
     *
     * @return An unmodifiable list containing all tasks.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Replaces all tasks in the list with the provided tasks.
     * Useful for loading tasks from storage.
     *
     * @param newTasks The list of tasks to replace the current tasks with.
     */
    public void replaceAll(List<Task> newTasks) {
        tasks.clear();
        tasks.addAll(newTasks);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i));
            if (i != tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Finds and returns all tasks that contain the search keyword in their description.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks matching the search keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        String lowerCaseKeyword = keyword.toLowerCase();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(lowerCaseKeyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
