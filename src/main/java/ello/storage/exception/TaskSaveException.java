package ello.storage.exception;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Exception thrown when tasks cannot be saved to ello.storage.
 */
public class TaskSaveException extends StorageException {

    /**
     * Constructs a new {@code TaskSaveException} with file path information.
     *
     * @param filePath The path that failed to save.
     * @param cause    The underlying {@link IOException}.
     */
    public TaskSaveException(Path filePath, IOException cause) {
        super("Failed to save tasks to: " + filePath, cause);
    }
}
