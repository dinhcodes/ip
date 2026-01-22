package main.java;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline createTaskFromCommandArgs(String args) {
        String byToken = "/by";

        int i = args.indexOf(byToken);
        if (i == -1) {
            throw new IllegalArgumentException("Invalid command. Usage: deadline <desc> /by <by>");
        }

        String desc = args.substring(0, i).trim();
        String by = args.substring(i + byToken.length()).trim();
        if (desc.isEmpty() || by.isEmpty()) {
            throw new IllegalArgumentException("Invalid command. Usage: deadline <desc> /by <by>");
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
