package ello.service;

import java.nio.file.Path;
import java.util.List;

import ello.storage.TaskList;
import ello.storage.TasksSaverAndLoader;
import ello.task.Task;

/**
 * Service class that orchestrates task loading and saving operations.
 */
public class TaskService {
    private final TaskList taskList;
    private final TasksSaverAndLoader storage;

    public TaskService(Path storagePath) {
        this.taskList = new TaskList();
        this.storage = new TasksSaverAndLoader(storagePath);
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
        List<Task> tasks = storage.load();
        taskList.replaceAll(tasks);
        return tasks.size();
    }

    /**
     * Saves the current task list to storage.
     */
    public void save() {
        storage.save(taskList);
    }
}
