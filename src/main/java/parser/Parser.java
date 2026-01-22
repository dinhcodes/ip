package main.java.parser;
import main.java.command.*;
import main.java.exception.EmptyCommandException;
import main.java.exception.InvalidCommandException;
import main.java.util.Utils;

public class Parser {
    public static Command parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        }
        if (fullCommand.equals("list")) {
            return new ListCommand();
        }
        if (fullCommand.startsWith("mark ")) {
            int index = Utils.extractIndex(fullCommand, "mark");
            return new MarkCommand(index, true);
        }
        if (fullCommand.startsWith("unmark ")) {
            int index = Utils.extractIndex(fullCommand, "unmark");
            return new MarkCommand(index, false);
        }
        if (fullCommand.startsWith("todo ") || fullCommand.startsWith("deadline ") || fullCommand.startsWith("event ")) {
            return new AddTaskCommand(fullCommand);
        }
        parseFullCommandErrors(fullCommand);
        return null;
    }

    // Helper method to handle parsing errors
    private static void parseFullCommandErrors(String fullCommand) {
        if (fullCommand.isEmpty() || fullCommand.trim().isEmpty()) {
            throw new EmptyCommandException();
        }
        throw new InvalidCommandException();
    }
}