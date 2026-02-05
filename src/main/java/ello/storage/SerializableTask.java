package ello.storage;

import com.google.gson.annotations.SerializedName;

import ello.storage.util.TaskConverter;
import ello.task.Task;

import java.util.HashMap;

/**
 * Data Transfer Object (DTO) for serializing and deserializing {@link Task} objects.
 * Uses a generic mapOfMarkersAndDescription map to store ello.task-specific fields, making it scalable.
 */
public record SerializableTask(String type,
                               String description,
                               boolean done,
                               @SerializedName("properties")
                               HashMap<String, String> mapOfMarkersAndDescription) {

    /**
     * Constructs a {@code SerializableTask} with the specified fields, all in {@code String}.
     * @param type The type of the ello.task as a string.
     * @param description The description of the ello.task.
     * @param done The completion status of the ello.task.
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
     * Converts mapOfMarkersAndDescription to format expected by TaskCreator.
     */
    public HashMap<String, String> toMarkerMapForCreator() {
        return new HashMap<>(mapOfMarkersAndDescription);
    }

    /**
     * Converts this {@code SerializableTask} DTO to a {@link Task} object.
     * @return The corresponding {@link Task} object.
     */
    public Task toTask() {
        return TaskConverter.toTask(this);
    }

    /**
     * Converts a {@link Task} object to a {@code SerializableTask} DTO.
     * @param task The {@link Task} object to convert.
     * @return The corresponding {@code SerializableTask} DTO.
     */
    public static SerializableTask fromTask(Task task) {
        return TaskConverter.fromTask(task);
    }
}
