package main.java.storage;

import main.java.exception.TaskIndexOutOfBoundsException;
import main.java.task.Task;
import main.java.parser.TaskParser;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Prase the command, create a new  {@link Task} to add to {@link TaskList}.
     *
     * Supported task types:
     * - todo <desc>
     * - deadline <desc> /by <by>
     * - event <desc> /from <from> /to <to>
     */

    public void deleteFromIndex(int zeroBasedIndex) {
        if (zeroBasedIndex < 0 || zeroBasedIndex >= size()) {
            throw new TaskIndexOutOfBoundsException(zeroBasedIndex + 1);
        } else {
            tasks.remove(zeroBasedIndex);
        }
    }

    public Task getTask(int zeroBasedIndex) {
        if (zeroBasedIndex < 0 || zeroBasedIndex >= size()) {
            throw new IndexOutOfBoundsException("Task index out of bounds.");
        } else {
            return tasks.get(zeroBasedIndex);
        }
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i));
            if (i != tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
