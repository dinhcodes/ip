package main.java.task;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 */
public enum TaskType {
    TODO("todo", new String[]{}, Todo::create),
    DEADLINE("deadline", new String[]{"by"}, Deadline::create),
    EVENT("event", new String[]{"from", "to"}, Event::create);

    private final String commandWord;
    private final String[] markers;
    private final TaskCreator taskCreator;

    /**
     * Constructor for TaskType enum. Every task type must have a command word, markers, and a task creator.
     * @param commandWord {@code String} that represents command word for the task type.
     * @param markers {@code String[]} that represents markers associated with the task type.
     * @param taskCreator {@link TaskCreator} that creates tasks of this type.
     */
    TaskType(String commandWord, String[] markers, TaskCreator taskCreator) {
        this.commandWord = commandWord;
        this.markers = markers;
        this.taskCreator = taskCreator;
    }

    /**
     * Gets the command word associated with the task type.
     * @return {@code String} that represents the command word.
     */
    public String getCommandWord() {
        return commandWord;
    }

    /**
     * Gets the markers associated with the task type.
     * @return {@code String[]} that represents the markers.
     */
    public String[] getMarkers() {
        return markers;
    }

    /**
     * Gets the task creator associated with the task type.
     * @return {@link TaskCreator} that creates tasks of this type.
     */
    public TaskCreator getTaskCreator() {
        return taskCreator;
    }

    /**
     * Builds the syntax string for the task type.
     * @return {@code String} that represents the syntax for the task type.
     */
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
