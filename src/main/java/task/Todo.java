package main.java.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public static Todo createTaskFromCommandArgs(String args) {
        String desc = args.trim();
        if (desc.isEmpty()) {
            throw new IllegalArgumentException("Invalid command. Usage: todo <desc>");
        }
        return new Todo(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
