package ello.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import ello.model.task.Task;
import ello.model.task.impl.Deadline;
import ello.model.task.impl.Event;
import ello.model.task.impl.Todo;

class SerializableTaskTest {

    @Test
    void fromTask_todoTask_createsCorrectSerializableTask() {
        Task todo = new Todo("Buy groceries");
        todo.markAsDone();

        SerializableTask serializable = SerializableTask.fromTask(todo);

        assertEquals("todo", serializable.type());
        assertEquals("Buy groceries", serializable.description());
        assertTrue(serializable.done());
        assertNotNull(serializable.mapOfMarkersAndDescription());
        assertTrue(serializable.mapOfMarkersAndDescription().isEmpty());
    }

    @Test
    void fromTask_deadlineTask_createsCorrectSerializableTask() {
        LocalDateTime by = LocalDateTime.of(
                2025, 1, 1, 0, 0, 0
        );
        Task deadline = new Deadline("Submit assignment", by);

        SerializableTask serializable = SerializableTask.fromTask(deadline);

        assertEquals("deadline", serializable.type());
        assertEquals("Submit assignment", serializable.description());
        assertFalse(serializable.done());
        // Uses consistent storage format yyyy-MM-dd HH:mm
        assertEquals("2025-01-01 00:00",
                serializable.mapOfMarkersAndDescription().get("by"));
    }

    @Test
    void fromTask_eventTask_createsCorrectSerializableTask() {
        LocalDateTime from = LocalDateTime.of(
                2025, 1, 1, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(
                2025, 2, 2, 0, 0, 0);
        Task event = new Event("Team meeting", from, to);

        SerializableTask serializable = SerializableTask.fromTask(event);

        assertEquals("event", serializable.type());
        assertEquals("Team meeting", serializable.description());
        assertFalse(serializable.done());
        // Uses consistent storage format yyyy-MM-dd HH:mm
        assertEquals("2025-01-01 00:00", serializable.mapOfMarkersAndDescription()
                .get("from"));
        assertEquals("2025-02-02 00:00", serializable.mapOfMarkersAndDescription()
                .get("to"));
    }

    @Test
    void toTask_todoSerializableTask_createsCorrectTask() {
        HashMap<String, String> markers = new HashMap<>();
        SerializableTask serializable = new SerializableTask(
                "todo", "Buy groceries", true, markers
        );

        Task task = serializable.toTask();

        assertInstanceOf(Todo.class, task);
        assertEquals("Buy groceries", task.getDescription());
        assertTrue(task.isDone());
    }

    @Test
    void toTask_deadlineSerializableTask_createsCorrectTask() {
        HashMap<String, String> markers = new HashMap<>();
        markers.put("by", "2025-01-01 00:00");
        SerializableTask serializable = new SerializableTask(
                "deadline", "Submit assignment", false, markers
        );

        Task task = serializable.toTask();

        assertInstanceOf(Deadline.class, task);
        assertEquals("Submit assignment", task.getDescription());
        assertFalse(task.isDone());
        Deadline deadline = (Deadline) task;
        assertEquals(LocalDateTime.of(2025, 1, 1, 0, 0, 0), deadline.getBy());
    }

    @Test
    void toTask_eventSerializableTask_createsCorrectTask() {
        HashMap<String, String> markers = new HashMap<>();
        markers.put("from", "2025-01-01 00:00");
        markers.put("to", "2025-02-02 00:00");
        SerializableTask serializable = new SerializableTask(
                "event", "Team meeting", false, markers
        );

        Task task = serializable.toTask();

        assertInstanceOf(Event.class, task);
        assertEquals("Team meeting", task.getDescription());
        assertFalse(task.isDone());
        Event event = (Event) task;
        assertEquals(LocalDateTime.of(2025, 1, 1, 0, 0, 0),
                event.getFrom());
        assertEquals(LocalDateTime.of(2025, 2, 2, 0, 0, 0),
                event.getTo());
    }

    @Test
    void toMarkerMapForCreator_returnsDefensiveCopy() {
        HashMap<String, String> original = new HashMap<>();
        original.put("by", "2025-01-01T00:00:00");
        SerializableTask serializable = new SerializableTask("deadline", "Test", false, original);

        HashMap<String, String> copy = serializable.toMarkerMapForCreator();
        copy.put("extra", "value");

        // Ensure the original is not modified
        assertFalse(serializable.mapOfMarkersAndDescription().containsKey("extra"));
    }

    @Test
    void roundTrip_preservesTaskData() {
        LocalDateTime from = LocalDateTime.of(2025, 1, 1, 10, 30, 0);
        LocalDateTime to = LocalDateTime.of(2025, 1, 1, 12, 30, 0);
        Task originalEvent = new Event("Workshop", from, to);
        originalEvent.markAsDone();

        SerializableTask serializable = SerializableTask.fromTask(originalEvent);
        Task reconstructed = serializable.toTask();

        assertEquals(originalEvent.getDescription(), reconstructed.getDescription());
        assertEquals(originalEvent.isDone(), reconstructed.isDone());
        assertInstanceOf(Event.class, reconstructed);
        Event reconstructedEvent = (Event) reconstructed;
        assertEquals(from, reconstructedEvent.getFrom());
        assertEquals(to, reconstructedEvent.getTo());
    }
}