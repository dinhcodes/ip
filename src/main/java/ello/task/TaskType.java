package ello.task;

import ello.task.impl.Deadline;
import ello.task.impl.Event;
import ello.task.impl.Todo;

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
     * Constructor for {@code TaskType} enum. Every {@code TaskType} must have a command word, markers, and a ello.task creator.
     * @param commandWord {@code String} that represents command word for the {@link TaskType}.
     * @param markers {@code String[]} that represents markers associated with the {@link TaskType}.
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
     * Gets the {@link TaskCreator} associated with the {@link TaskType}.
     * @return {@link TaskCreator} that creates tasks of this type.
     */
    public TaskCreator getTaskCreator() {
        return taskCreator;
    }

    /**
     * Builds the syntax string for the can .
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

    /**
     * Finds a TaskType by its command word.
     *
     * @param commandWord The command word to search for.
     * @return The matching TaskType.
     * @throws IllegalArgumentException if no matching TaskType is found.
     */
    public static TaskType fromCommandWord(String commandWord) {
        for (TaskType type : values()) {
            if (type.commandWord.equals(commandWord)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown task type: " + commandWord);
    }

    @Override
    public String toString() {
        return commandWord;
    }
}
