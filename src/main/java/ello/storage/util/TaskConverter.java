package ello.storage.util;

import java.util.HashMap;

import ello.storage.SerializableTask;
import ello.task.Task;
import ello.task.TaskType;

/**
 * Utility class for converting between {@link SerializableTask} DTOs and {@link Task} objects.
 */
public final class TaskConverter {

    private TaskConverter() {
    }

    /**
     * Converts a serializable task DTO to a task object.
     *
     * @param dto The serializable task DTO to convert.
     * @return The corresponding task object.
     */
    public static Task toTask(SerializableTask dto) {
        if (dto == null) {
            throw new IllegalArgumentException("SerializableTask cannot be null");
        }

        TaskType taskType = TaskType.fromCommandWord(dto.type());
        HashMap<String, String> markerToDescriptionMap = dto.toMarkerMapForCreator();
        Task task = taskType.getTaskCreator().create(dto.description(), markerToDescriptionMap);

        if (dto.done()) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Converts a task object to a serializable task DTO.
     *
     * @param task The task object to convert.
     * @return The corresponding serializable task DTO.
     */
    public static SerializableTask fromTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }

        return new SerializableTask(
                task.getTaskType().getCommandWord(),
                task.getDescription(),
                task.isDone(),
                task.getMapOfMarkersAndDescription()
        );
    }
}
