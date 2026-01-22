package main.java.task;

import main.java.exception.InvalidCommandException;

public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline createTaskFromCommandArgs(String args) {

        String byToken = "/by";
        int i = args.indexOf(byToken);
        if (i == -1) {
            throw new InvalidCommandException("Deadline lacks a /by date. Usage: deadline <desc> /by <by>");
        }

        String desc = args.substring(0, i).trim();
        String by = args.substring(i + byToken.length()).trim();

        if (desc.isEmpty()) {
            throw new InvalidCommandException("Deadline description cannot be empty. Usage: deadline <desc> /by <by>");
        }
        if (by.isEmpty()) {
            throw new InvalidCommandException("Deadline /by date cannot be empty. Usage: deadline <desc> /by <by>");
        }

        return new Deadline(desc, by);
    }

    @Override
    protected String detailsSuffix() {
        return String.format(" (by: %s)", by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

}
