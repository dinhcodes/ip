package ello.storage;

import java.nio.file.Path;
import java.util.List;

import ello.model.task.Task;
import ello.model.task.TaskList;
import ello.storage.exception.StorageException;

/**
 * Service class that orchestrates task loading and saving operations.
 */
public class TaskStorage {
    private final TaskList taskList;
    private final TasksSaverAndLoader tasksSaverAndLoader;

    public TaskStorage(Path storagePath) {
        this.taskList = new TaskList();
        this.tasksSaverAndLoader = new TasksSaverAndLoader(storagePath);
    }

    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Loads tasks from storage into the task list.
     *
     * @return The number of tasks loaded.
     */
    public int load() {
        List<Task> tasks = tasksSaverAndLoader.load();
        taskList.replaceAll(tasks);
        return tasks.size();
    }

    /**
     * Loads tasks from storage and returns a status message.
     * This method handles the initialization of tasks.
     *
     * @return A status message describing the load result.
     */
    public String loadAndGetStatusMessage() {
        try {
            int count = load();
            if (count > 0) {
                return "Loaded " + count + " task(s) from storage.";
            } else {
                return "No existing tasks found. Starting with an empty task list.";
            }
        } catch (StorageException e) {
            return "Failed to load tasks from storage:\n" + e.getMessage() + "\nStarting with an empty task list.";
        }
    }

    /**
     * Saves the current task list to storage.
     */
    public void save() {
        tasksSaverAndLoader.save(taskList);
    }
}
