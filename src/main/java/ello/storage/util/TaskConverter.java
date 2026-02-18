package ello.storage.util;

import java.util.HashMap;

import ello.model.task.Task;
import ello.model.task.TaskType;
import ello.storage.SerializableTask;

/**
 * Utility class for converting between {@link SerializableTask} DTOs and {@link Task} objects.
 */
public final class TaskConverter {

    private TaskConverter() {
    }

    /**
     * Converts a {@link SerializableTask} DTO to a {@link Task} object.
     *
     * @param dto The serialized task to convert.
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
     * Converts a {@link Task} object to a {@link SerializableTask} DTO.
     *
     * @param task The task object to convert.
     * @return The corresponding serialized task.
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
