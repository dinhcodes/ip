package ello.service;

import ello.storage.TaskList;
import ello.storage.TasksSaverAndLoader;
import ello.task.Task;

import java.nio.file.Path;
import java.util.List;

/**
 * Service class that orchestrates {@link Task} loading and saving operations.
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
     * Loads tasks from storage into the {@link TaskList}, using {@link TasksSaverAndLoader#load()}.
     *
     * @return The number of tasks loaded.
     */
    public int load() {
        List<Task> tasks = storage.load();
        taskList.replaceAll(tasks);
        return tasks.size();
    }

    /**
     * Saves the current {@link TaskList} to storage, using {@link TasksSaverAndLoader#save(TaskList)}.
     */
    public void save() {
        storage.save(taskList);
    }
}
