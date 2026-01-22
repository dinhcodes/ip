package main.java.parser;
import main.java.command.Command;
import main.java.command.AddTaskCommand;
import main.java.command.ExitCommand;
import main.java.command.DeleteCommand;
import main.java.command.ListCommand;
import main.java.command.MarkCommand;
import main.java.exception.EmptyCommandException;
import main.java.exception.InvalidCommandException;
import main.java.task.Task;
import main.java.util.Utils;

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
            int zeroBasedTaskIndex = Utils.extractTaskIndex(command, "mark");
            return new MarkCommand(zeroBasedTaskIndex, true);
        }
        if (command.startsWith("unmark ")) {
            int zeroBasedTaskIndex = Utils.extractTaskIndex(command, "unmark");
            return new MarkCommand(zeroBasedTaskIndex, false);
        }
        if (command.startsWith("todo ") || command.startsWith("deadline ") || command.startsWith("event ")) {
            Task addedTask = TaskParser.parseAndCreateTask(command);
            return new AddTaskCommand(addedTask);
        }
        if (command.startsWith("delete ")) {
            int oneBasedIndex = Utils.extractTaskIndex(command, "delete");
            return new DeleteCommand(oneBasedIndex);
        }
        parseCommandErrors(command);
        return null;
    }

    // Helper method to handle parsing errors
    private static void parseCommandErrors(String command) {
        if (command.isEmpty() || command.trim().isEmpty()) {
            throw new EmptyCommandException();
        }
        throw new InvalidCommandException();
    }
}