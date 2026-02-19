package ello.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ello.model.task.TaskList;
import ello.model.task.impl.Deadline;
import ello.model.task.impl.Event;
import ello.model.task.impl.Todo;

class TaskStorageTest {

    @TempDir
    Path tempDir;

    private Path testFilePath;
    private TaskStorage taskStorage;

    @BeforeEach
    void setUp() {
        testFilePath = tempDir.resolve("test-tasks.json");
        taskStorage = new TaskStorage(testFilePath);
    }

    @AfterEach
    void tearDown() throws IOException {
        if (Files.exists(testFilePath)) {
            Files.delete(testFilePath);
        }
    }

    @Test
    void load_nonExistentFile_returnsZero() {
        int count = taskStorage.load();
        assertEquals(0, count);
        assertEquals(0, taskStorage.getTaskList().size());
    }

    @Test
    void loadAndGetStatusMessage_nonExistentFile_returnsEmptyMessage() {
        String message = taskStorage.loadAndGetStatusMessage();
        assertTrue(message.contains("No existing tasks"));
        assertTrue(message.contains("empty task list"));
    }

    @Test
    void save_tasksInList_persistsToFile() {
        TaskList taskList = taskStorage.getTaskList();
        taskList.add(new Todo("Buy groceries"));
        taskList.add(new Deadline("Submit report", LocalDateTime.of(2025, 3, 1, 23, 59, 0)));

        taskStorage.save();

        assertTrue(Files.exists(testFilePath));
    }

    @Test
    void loadAndSave_roundTrip_preservesData() {
        // Save some tasks
        TaskList originalList = taskStorage.getTaskList();
        originalList.add(new Todo("Task 1"));
        Todo task2 = new Todo("Task 2");
        task2.markAsDone();
        originalList.add(task2);
        originalList.add(new Event("Meeting",
                LocalDateTime.of(2025, 3, 15, 10, 0, 0),
                LocalDateTime.of(2025, 3, 15, 11, 0, 0)));
        taskStorage.save();

        // Create new storage instance and load
        TaskStorage newStorage = new TaskStorage(testFilePath);
        int loadedCount = newStorage.load();

        assertEquals(3, loadedCount);
        TaskList loadedList = newStorage.getTaskList();
        assertEquals(3, loadedList.size());
        assertEquals("Task 1", loadedList.getTask(0).getDescription());
        assertEquals("Task 2", loadedList.getTask(1).getDescription());
        assertTrue(loadedList.getTask(1).isDone());
        assertEquals("Meeting", loadedList.getTask(2).getDescription());
    }

    @Test
    void loadAndGetStatusMessage_withExistingTasks_returnsCountMessage() throws IOException {
        // Pre-populate the file with tasks
        String json = """
                [
                  {
                    "type": "todo",
                    "description": "Task 1",
                    "done": false,
                    "properties": {}
                  },
                  {
                    "type": "todo",
                    "description": "Task 2",
                    "done": false,
                    "properties": {}
                  }
                ]
                """;
        Files.writeString(testFilePath, json);

        TaskStorage storage = new TaskStorage(testFilePath);
        String message = storage.loadAndGetStatusMessage();

        assertTrue(message.contains("Loaded 2 task(s)"));
    }

    @Test
    void loadAndGetStatusMessage_corruptedFile_returnsErrorMessage() throws IOException {
        Files.writeString(testFilePath, "{corrupted json");

        TaskStorage storage = new TaskStorage(testFilePath);
        String message = storage.loadAndGetStatusMessage();

        assertTrue(message.contains("Failed to load tasks"));
        assertTrue(message.contains("empty task list"));
    }

    @Test
    void getTaskList_returnsUnderlyingTaskList() {
        TaskList taskList = taskStorage.getTaskList();
        taskList.add(new Todo("Test"));

        assertEquals(1, taskStorage.getTaskList().size());
    }

    @Test
    void load_replacesExistingTasks() {
        // Add tasks to the in-memory list
        TaskList taskList = taskStorage.getTaskList();
        taskList.add(new Todo("Old task 1"));
        taskList.add(new Todo("Old task 2"));
        assertEquals(2, taskList.size());

        // Now load from file (which doesn't exist)
        taskStorage.load();

        // The list should be empty now
        assertEquals(0, taskList.size());
    }

    @Test
    void save_multipleTimes_overwritesFile() throws IOException {
        TaskList taskList = taskStorage.getTaskList();
        taskList.add(new Todo("Task 1"));
        taskStorage.save();

        taskList.add(new Todo("Task 2"));
        taskStorage.save();

        // Verify the file contains both tasks
        TaskStorage newStorage = new TaskStorage(testFilePath);
        newStorage.load();
        assertEquals(2, newStorage.getTaskList().size());
    }
}
