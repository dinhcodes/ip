package ello.command.parser;

import ello.command.Command;
import ello.command.AddTaskCommand;
import ello.command.ExitCommand;
import ello.command.DeleteCommand;
import ello.command.ListCommand;
import ello.command.MarkCommand;
import ello.command.exception.EmptyCommandException;
import ello.command.exception.InvalidCommandException;
import ello.task.Task;
import ello.task.TaskType;

/**
 * Represents a utility class that parses user commands into <code>Command</code> objects.
 */
public class CommandParser {
    public static Command parse(String fullCommand) {
        String command = fullCommand.trim();
        
        if (command.equals("bye")) {
            return new ExitCommand();
        }
        if (command.equals("list")) {
            return new ListCommand();
        }
        if (command.startsWith("mark ")) {
            int zeroBasedTaskIndex = extractTaskIndex(command, "mark");
            return new MarkCommand(zeroBasedTaskIndex, true);
        }
        if (command.startsWith("unmark ")) {
            int zeroBasedTaskIndex = extractTaskIndex(command, "unmark");
            return new MarkCommand(zeroBasedTaskIndex, false);
        }
        if (command.startsWith("delete ")) {
            int oneBasedIndex = extractTaskIndex(command, "delete");
            return new DeleteCommand(oneBasedIndex);
        }
        if (isCommandATaskTaskCommand(command)) {
            Task addedTask = TaskParser.validateParseAndCreateTask(command);
            return new AddTaskCommand(addedTask);
        }
        parseCommandErrors(command);
        return parse("");
    }

    private static boolean isCommandATaskTaskCommand(String command) {
        for (TaskType type : TaskType.values()) {
            if (command.startsWith(type.getCommandWord())) {
                return true;
            }
        }
        return false;
    }

    // Helper method to handle parsing errors
    private static void parseCommandErrors(String command) {
        if (command.trim().isEmpty()) {
            throw new EmptyCommandException();
        }
        throw new InvalidCommandException();
    }

    /**
     * Extracts the ello.task index from a full command string. Assumes that the
     * command is in the format: {@code <index> <commandStart>}.
     *
     * @param fullCommand The complete command string entered by the user.
     * @param commandStart The starting keyword of the command (e.g., "delete", "done").
     * @return The zero-based index of the ello.task.
     * @throws InvalidCommandException If the index is not a valid integer.
     */
    public static int extractTaskIndex(String fullCommand, String commandStart) {
        String indexStr = fullCommand.substring(commandStart.length() + 1).trim();
        try {
            return Integer.parseInt(indexStr) - 1; // Convert to zero-based index
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid index: " + indexStr + " . Please provide a valid number.");
        }
    }
}