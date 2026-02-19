package ello.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ello.model.task.Task;
import ello.model.task.TaskList;
import ello.model.task.impl.Deadline;
import ello.model.task.impl.Event;
import ello.model.task.impl.Todo;
import ello.storage.exception.CorruptedStorageException;

class TasksSaverAndLoaderTest {

    @TempDir
    Path tempDir;

    private Path testFilePath;
    private TasksSaverAndLoader saverAndLoader;

    @BeforeEach
    void setUp() {
        testFilePath = tempDir.resolve("test-tasks.json");
        saverAndLoader = new TasksSaverAndLoader(testFilePath);
    }

    @AfterEach
    void tearDown() throws IOException {
        if (Files.exists(testFilePath)) {
            Files.delete(testFilePath);
        }
    }

    @Test
    void load_nonExistentFile_returnsEmptyList() {
        List<Task> tasks = saverAndLoader.load();
        assertFalse(Files.exists(testFilePath));
        assertTrue(tasks.isEmpty());
    }

    @Test
    void load_emptyFile_returnsEmptyList() throws IOException {
        Files.writeString(testFilePath, "");
        List<Task> tasks = saverAndLoader.load();
        assertTrue(tasks.isEmpty());
    }

    @Test
    void load_blankFile_returnsEmptyList() throws IOException {
        Files.writeString(testFilePath, "   \n  \t  ");
        List<Task> tasks = saverAndLoader.load();
        assertTrue(tasks.isEmpty());
    }

    @Test
    void save_taskList_createsFileWithCorrectContent() throws IOException {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("Test task"));

        saverAndLoader.save(taskList);

        assertTrue(Files.exists(testFilePath));
        String content = Files.readString(testFilePath);
        assertTrue(content.contains("\"type\": \"todo\""));
        assertTrue(content.contains("\"description\": \"Test task\""));
    }

    @Test
    void saveAndLoad_multipleTaskTypes_preservesData() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("Buy milk"));
        Task deadline = new Deadline("Submit report", LocalDateTime.of(2025, 3, 1, 23, 59, 0));
        deadline.markAsDone();
        taskList.add(deadline);
        taskList.add(new Event("Conference",
                LocalDateTime.of(2025, 4, 1, 9, 0, 0),
                LocalDateTime.of(2025, 4, 1, 17, 0, 0)));

        saverAndLoader.save(taskList);
        List<Task> loadedTasks = saverAndLoader.load();

        assertEquals(3, loadedTasks.size());

        // Verify Todo
        assertInstanceOf(Todo.class, loadedTasks.get(0));
        assertEquals("Buy milk", loadedTasks.get(0).getDescription());
        assertFalse(loadedTasks.get(0).isDone());

        // Verify Deadline
        assertInstanceOf(Deadline.class, loadedTasks.get(1));
        assertEquals("Submit report", loadedTasks.get(1).getDescription());
        assertTrue(loadedTasks.get(1).isDone());
        Deadline loadedDeadline = (Deadline) loadedTasks.get(1);
        assertEquals(LocalDateTime.of(2025, 3, 1, 23, 59, 0), loadedDeadline.getBy());

        // Verify Event
        assertInstanceOf(Event.class, loadedTasks.get(2));
        assertEquals("Conference", loadedTasks.get(2).getDescription());
        Event loadedEvent = (Event) loadedTasks.get(2);
        assertEquals(LocalDateTime.of(2025, 4, 1, 9, 0, 0), loadedEvent.getFrom());
        assertEquals(LocalDateTime.of(2025, 4, 1, 17, 0, 0), loadedEvent.getTo());
    }

    @Test
    void load_sampleDataJson_loadsCorrectly() throws IOException {
        String sampleJson = """
                [
                  {
                    "type": "event",
                    "description": "event",
                    "done": false,
                    "properties": {
                      "from": "2025-01-01 00:00",
                      "to": "2025-02-02 00:00"
                    }
                  },
                  {
                    "type": "deadline",
                    "description": "deadline",
                    "done": false,
                    "properties": {
                      "by": "2025-01-01 00:00"
                    }
                  }
                ]
                """;
        Files.writeString(testFilePath, sampleJson);

        List<Task> tasks = saverAndLoader.load();

        assertEquals(2, tasks.size());
        assertInstanceOf(Event.class, tasks.get(0));
        assertInstanceOf(Deadline.class, tasks.get(1));
    }

    @Test
    void load_corruptedJson_throwsCorruptedStorageException() throws IOException {
        Files.writeString(testFilePath, "{invalid json content");
        assertThrows(CorruptedStorageException.class, () -> saverAndLoader.load());
    }

    @Test
    void load_invalidTaskType_throwsCorruptedStorageException() throws IOException {
        String invalidJson = """
                [
                  {
                    "type": "invalid_type",
                    "description": "test",
                    "done": false,
                    "properties": {}
                  }
                ]
                """;
        Files.writeString(testFilePath, invalidJson);
        assertThrows(CorruptedStorageException.class, () -> saverAndLoader.load());
    }

    @Test
    void save_emptyTaskList_createsEmptyJsonArray() throws IOException {
        TaskList emptyList = new TaskList();
        saverAndLoader.save(emptyList);

        String content = Files.readString(testFilePath);
        assertEquals("[]", content.trim());
    }

    @Test
    void save_createsParentDirectory() {
        Path nestedPath = tempDir.resolve("nested").resolve("dir").resolve("tasks.json");
        TasksSaverAndLoader nestedLoader = new TasksSaverAndLoader(nestedPath);
        TaskList taskList = new TaskList();
        taskList.add(new Todo("Test"));

        nestedLoader.save(taskList);

        assertTrue(Files.exists(nestedPath));
        assertTrue(Files.exists(nestedPath.getParent()));
    }
}