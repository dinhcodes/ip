package ello.logic.command.parser;

import ello.logic.command.AddTaskCommand;
import ello.logic.command.Command;
import ello.logic.command.DeleteCommand;
import ello.logic.command.ExitCommand;
import ello.logic.command.FindCommand;
import ello.logic.command.HelpCommand;
import ello.logic.command.ListCommand;
import ello.logic.command.MarkCommand;
import ello.logic.command.exception.EmptyCommandException;
import ello.logic.command.exception.InvalidCommandException;
import ello.model.task.Task;
import ello.model.task.TaskType;

/**
 * Represents a utility class that parses user commands into {@link Command} objects.
 */
public class CommandParser {
    /**
     * Parses a full command string entered by the user and returns the corresponding Command object.
     *
     * @param fullCommand The complete command string entered by the user.
     * @return The Command object corresponding to the parsed command string.
     */
    public static Command parse(String fullCommand) {
        String command = fullCommand.trim();

        if (command.equals("bye")) {
            return new ExitCommand();
        }
        if (command.equals("list")) {
            return new ListCommand();
        }
        if (command.equals("help")) {
            return new HelpCommand();
        }
        if (command.startsWith("help ")) {
            return parseHelpCommand(command);
        }
        if (command.startsWith("find ")) {
            String searchKeyword = command.substring(5);
            return new FindCommand(searchKeyword);
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
        if (isACreateTaskCommand(command)) {
            TaskType taskType = extractTaskType(command);
            Task task = AddTaskCommandParser.validateParseAndCreateTask(command);
            return new AddTaskCommand(task, taskType);
        }
        parseCommandErrors(command);
        return parse("");
    }

    private static Command parseHelpCommand(String command) {
        String[] parts = command.trim().split("\\s+");
        if (parts.length == 2) {
            try {
                int commandNumber = Integer.parseInt(parts[1]);
                return new HelpCommand(commandNumber);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("Invalid command number for help. Please provide a valid number.");
            }
        }
        throw new InvalidCommandException("Invalid help command format. Use 'help' or 'help <number>'.");
    }

    private static boolean isACreateTaskCommand(String command) {
        for (TaskType type : TaskType.values()) {
            if (command.startsWith(type.getCommandWord())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Extracts the command word from a full command string.
     *
     * @param command The complete command string entered by the user.
     * @return The command word (e.g., "todo", "deadline", "event").
     */
    private static String extractCommandWord(String command) {
        for (TaskType type : TaskType.values()) {
            if (command.startsWith(type.getCommandWord())) {
                return type.getCommandWord();
            }
        }
        return "unknown";
    }

    /**
     * Extracts the task type from a full command string.
     *
     * @param command The complete command string entered by the user.
     * @return The {@link TaskType} corresponding to the command.
     * @throws InvalidCommandException If the task type is not recognized.
     */
    private static TaskType extractTaskType(String command) {
        for (TaskType type : TaskType.values()) {
            if (command.startsWith(type.getCommandWord())) {
                return type;
            }
        }
        throw new InvalidCommandException("Invalid task type in command: " + command);
    }

    // Helper method to handle parsing errors
    private static void parseCommandErrors(String command) {
        if (command.trim().isEmpty()) {
            throw new EmptyCommandException();
        }
        throw new InvalidCommandException();
    }

    /**
     * Extracts the task index from a full command string.
     * Assumes that the command is in the format: {@code <commandStart> <index>}.
     *
     * @param fullCommand  The complete command string entered by the user.
     * @param commandStart The starting keyword of the command (e.g., "delete", "mark").
     * @return The zero-based index of the task.
     * @throws InvalidCommandException If the index is not a valid integer.
     */
    private static int extractTaskIndex(String fullCommand, String commandStart) {
        String indexStr = fullCommand.substring(commandStart.length() + 1).trim();
        try {
            return Integer.parseInt(indexStr) - 1; // Convert to zero-based index
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid index: " + indexStr + " . Please provide a valid number.");
        }
    }
}
