package ello.storage.exception;

import ello.common.exception.ElloException;

import java.io.IOException;

/**
 * Exception thrown for ello.storage-related errors.
 */
public class StorageException extends ElloException {

    /**
     * Constructs a new {@code StorageException} with the specified message.
     *
     * @param message The error message.
     */
    public StorageException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code StorageException} with the specified message and cause.
     *
     * @param message The error message.
     * @param cause   The underlying {@link IOException}.
     */
    public StorageException(String message, IOException cause) {
        super(message + ": " + cause.getMessage());
    }
}
