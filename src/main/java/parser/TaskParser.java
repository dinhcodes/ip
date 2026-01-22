package main.java.parser;

import main.java.exception.InvalidCommandException;
import main.java.task.Deadline;
import main.java.task.Event;
import main.java.task.Task;
import main.java.task.Todo;
import main.java.util.Utils;

public class TaskParser {
    private TaskParser() {}

    public static Task parseAndCreateTask(String command) {
        if (command.startsWith("todo ")) {
            String args = Utils.extractTask(command, "todo").trim();
            return Todo.createTaskFromCommandArgs(args);
        }

        if (command.startsWith("deadline ")) {
            String args = Utils.extractTask(command, "deadline").trim();
            return Deadline.createTaskFromCommandArgs(args);
        }

        if (command.startsWith("event ")) {
            String args = Utils.extractTask(command, "event").trim();
            return Event.createTaskFromCommandArgs(args);
        }
        throw new InvalidCommandException();
    }
}
