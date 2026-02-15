package ello.storage;

import java.util.HashMap;

import com.google.gson.annotations.SerializedName;

import ello.storage.util.TaskConverter;
import ello.task.Task;

/**
 * Data Transfer Object (DTO) for serializing and deserializing {@link Task} objects.
 * Uses a generic {@code HashMap} to store fields, making it scalable.
 */
public record SerializableTask(String type,
                               String description,
                               boolean done,
                               @SerializedName("properties")
                               HashMap<String, String> mapOfMarkersAndDescription) {

    /**
     * Constructs a {@code SerializableTask} with the specified fields, all in {@code String}.
     *
     * @param type                       The type of the task as a string.
     * @param description                The description of the task.
     * @param done                       The completion status of the task.
     * @param mapOfMarkersAndDescription A map of markers to their corresponding descriptions.
     */
    public SerializableTask(String type, String description, boolean done, HashMap<String, String> mapOfMarkersAndDescription) {
        this.type = type;
        this.description = description;
        this.done = done;
        this.mapOfMarkersAndDescription = mapOfMarkersAndDescription != null
                ? new HashMap<>(mapOfMarkersAndDescription)
                : new HashMap<>();
    }

    /**
     * Converts a task object to a serializable task DTO.
     *
     * @param task The task object to convert.
     * @return The corresponding serializable task DTO.
     */
    public static SerializableTask fromTask(Task task) {
        return TaskConverter.fromTask(task);
    }

    /**
     * Converts the map to the format expected by task creator.
     *
     * @return A new HashMap with the marker mappings.
     */
    public HashMap<String, String> toMarkerMapForCreator() {
        return new HashMap<>(mapOfMarkersAndDescription);
    }

    /**
     * Converts this serializable task DTO to a task object.
     *
     * @return The corresponding task object.
     */
    public Task toTask() {
        return TaskConverter.toTask(this);
    }
}
