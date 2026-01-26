package main.java.task;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum TaskType {
    TODO("todo", new String[]{}, Todo::create),
    DEADLINE("deadline", new String[]{"by"}, Deadline::create),
    EVENT("event", new String[]{"from", "to"}, Event::create);

    private final String commandWord;
    private final String[] markers;
    private final TaskCreator taskCreator;

    TaskType(String commandWord, String[] markers, TaskCreator taskCreator) {
        this.commandWord = commandWord;
        this.markers = markers;
        this.taskCreator = taskCreator;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String[] getMarkers() {
        return markers;
    }

    public TaskCreator getTaskCreator() {
        return taskCreator;
    }

    public String buildSyntax() {
        String base = "Syntax: " + commandWord + " <desc>";
        if (markers == null || markers.length == 0) {
            return base;
        }

        String markersPart = Arrays.stream(markers)
                .map(m -> "/" + m + " <value>")
                .collect(Collectors.joining(" "));

        return base + " " + markersPart;
    }

    @Override
    public String toString() {
        return commandWord;
    }
}
