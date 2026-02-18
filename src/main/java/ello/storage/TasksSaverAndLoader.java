package ello.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import ello.model.task.Task;
import ello.model.task.TaskList;
import ello.storage.exception.CorruptedStorageException;
import ello.storage.exception.TaskLoadException;
import ello.storage.exception.TaskSaveException;
import ello.storage.util.JsonParser;

/**
 * Handles loading and saving tasks to a JSON file.
 * Uses {@link JsonParser} for serialization/deserialization with Gson.
 */
public class TasksSaverAndLoader {
    private final Path filePath;

    /**
     * Creates a new {@code TasksSaverAndLoader} with the specified file path.
     *
     * @param filePath The path to the JSON file for storage.
     */
    public TasksSaverAndLoader(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the JSON file.
     *
     * @return A list of tasks loaded from the file, or an empty list if the file doesn't exist.
     * @throws TaskLoadException         if an I/O error occurs while reading.
     * @throws CorruptedStorageException if the file format is invalid.
     */
    public List<Task> load() {
        try {
            ensureParentDirectory();

            if (Files.notExists(filePath)) {
                return new ArrayList<>();
            }

            String content = Files.readString(filePath, StandardCharsets.UTF_8);
            if (content.isBlank()) {
                return new ArrayList<>();
            }

            List<SerializableTask> dtos = JsonParser.listFromJson(content, SerializableTask.class);

            if (dtos == null) {
                return new ArrayList<>();
            }

            return dtos.stream().map(SerializableTask::toTask).toList();
        } catch (IOException e) {
            throw new TaskLoadException(filePath, e);
        } catch (Exception e) {
            throw new CorruptedStorageException(filePath);
        }
    }

    /**
     * Saves the tasks from a task list to the JSON file.
     *
     * @param taskList The task list to save.
     * @throws TaskSaveException If an I/O error occurs while writing.
     */
    public void save(TaskList taskList) {
        save(taskList.getTasks());
    }

    private void save(List<Task> tasks) {
        try {
            ensureParentDirectory();

            List<SerializableTask> dtos = tasks.stream().map(SerializableTask::fromTask).toList();

            String json = JsonParser.toJson(dtos);
            Files.writeString(filePath, json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new TaskSaveException(filePath, e);
        }
    }

    /**
     * Ensures the parent directory exists, creating it if necessary.
     */
    private void ensureParentDirectory() throws IOException {
        Path parent = filePath.getParent();
        if (parent != null && Files.notExists(parent)) {
            Files.createDirectories(parent);
        }
    }
}
