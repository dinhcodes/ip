package main.java.task;

import main.java.exception.InvalidCommandException;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public static Todo createTaskFromCommandArgs(String args) {
        String desc = args.trim();
        if (desc.isEmpty()) {
            throw new InvalidCommandException("Todo description cannot be empty. Usage: todo <desc>");
        }
        return new Todo(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
