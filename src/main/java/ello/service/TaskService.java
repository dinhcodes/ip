package ello.service;

import ello.storage.TasksList;
import ello.storage.TasksSaverAndLoader;
import ello.task.Task;

import java.nio.file.Path;
import java.util.List;

/**
 * Service class that orchestrates {@link Task} loading and saving operations.
 */
public class TaskService {
    private final TasksList tasksList;
    private final TasksSaverAndLoader storage;

    public TaskService(Path storagePath) {
        this.tasksList = new TasksList();
        this.storage = new TasksSaverAndLoader(storagePath);
    }

    public TasksList getTaskList() {
        return tasksList;
    }

    /**
     * Loads tasks from storage into the {@link TasksList}, using {@link TasksSaverAndLoader#load()}.
     *
     * @return The number of tasks loaded.
     */
    public int load() {
        List<Task> tasks = storage.load();
        tasksList.replaceAll(tasks);
        return tasks.size();
    }

    /**
     * Saves the current {@link TasksList} to storage, using {@link TasksSaverAndLoader#save(TasksList)}.
     */
    public void save() {
        storage.save(tasksList);
    }
}
