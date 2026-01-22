package main.java.task;

import main.java.exception.InvalidCommandException;

public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Event createTaskFromCommandArgs(String args) {
        String fromToken = "/from";
        String toToken = "/to";

        int fi = args.indexOf(fromToken);
        int ti = args.indexOf(toToken);
        if (fi == -1 || ti == -1 || ti < fi) {
            throw new InvalidCommandException("Event /from or /to fields are not present. Usage: event <desc> /from <from> /to <to>");
        }

        String desc = args.substring(0, fi).trim();
        String from = args.substring(fi + fromToken.length(), ti).trim();
        String to = args.substring(ti + toToken.length()).trim();
        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new InvalidCommandException("Event description, /from, or /to fields are empty. Usage: event <desc> /from <from> /to <to>");
        }
        return new Event(desc, from, to);
    }

    @Override
    protected String detailsSuffix() {
        return String.format(" (from: %s to: %s)", from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
