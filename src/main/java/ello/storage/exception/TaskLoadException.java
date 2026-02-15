package ello.storage.exception;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Exception thrown when tasks cannot be loaded from storage.
 */
public class TaskLoadException extends StorageException {

    /**
     * Constructs an exception with file path information.
     *
     * @param filePath The path that failed to load.
     * @param cause    The underlying I/O exception.
     */
    public TaskLoadException(Path filePath, IOException cause) {
        super("Failed to load tasks from: " + filePath, cause);
    }
}
