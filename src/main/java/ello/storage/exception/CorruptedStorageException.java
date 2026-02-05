package ello.storage.exception;

import java.nio.file.Path;

/**
 * Exception thrown when a ello.storage file is corrupted or has invalid format.
 */
public class CorruptedStorageException extends StorageException {

    /**
     * Constructs a new {@code CorruptedStorageException} with file path information.
     *
     * @param filePath The path of the corrupted file.
     */
    public CorruptedStorageException(Path filePath) {
        super("Storage file is corrupted or has invalid format: " + filePath);
    }
}
