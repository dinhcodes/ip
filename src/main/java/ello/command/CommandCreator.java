package ello.command;

/**
 * Functional interface for creating {@link Command} objects.
 * Used to define command creation logic in {@link CommandType} enum.
 */
@FunctionalInterface
public interface CommandCreator {
    /**
     * Creates a command with the given argument.
     *
     * @param argument The argument string for the command (e.g., keyword for find, index for mark/delete).
     * @return The created command.
     */
    Command create(String argument);
}
