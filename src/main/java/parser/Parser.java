package main.java.parser;
import main.java.command.*;
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
        if (fullCommand.startsWith("todo") || fullCommand.startsWith("deadline") || fullCommand.startsWith("event")) {
            return new AddTaskCommand(fullCommand);
        }
        throw new IllegalArgumentException("I'm sorry, but I don't know what that means.");
    }
}