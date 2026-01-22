package main.java.storage;

import main.java.task.Deadline;
import main.java.task.Event;
import main.java.task.Task;
import main.java.task.Todo;
import main.java.util.Utils;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(Utils.wrapWithLine("No hay tareas todavía."));
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            String taskString = String.format("%d. %s", i, tasks.get(i - 1));
            sb.append(taskString);
            if (i != tasks.size()) {
                sb.append("\n");
            }
        }
        System.out.println(Utils.wrapWithLine(sb.toString()));
    }

    /**
     * Prase the command, create a new  {@link Task} to add to {@link TaskList}.
     *
     * Supported task types:
     * - todo <desc>
     * - deadline <desc> /by <by>
     * - event <desc> /from <from> /to <to>
     */
    public Task addFromCommand(String command) {
        Task task = categorizeTask(command);
        add(task);
        return task;
    }

    /**
     * Create a {@link Task} based on the command prefix.
     */
    private Task categorizeTask(String command) {
        if (command.startsWith("todo")) {
            String args = Utils.extractTask(command, "todo").trim();
            return Todo.createTaskFromCommandArgs(args);
        }

        if (command.startsWith("deadline")) {
            String args = Utils.extractTask(command, "deadline").trim();
            return Deadline.createTaskFromCommandArgs(args);
        }

        if (command.startsWith("event")) {
            String args = Utils.extractTask(command, "event").trim();
            return Event.createTaskFromCommandArgs(args);
        }

        throw new IllegalArgumentException("Invalid command.");
    }

    public Task getOneBasedIndex(int oneBasedIndex) {
        int zeroBasedIndex = oneBasedIndex - 1;
        if (zeroBasedIndex < 0 || zeroBasedIndex >= size()) {
            throw new IndexOutOfBoundsException("Task index out of bounds.");
        } else {
            return tasks.get(zeroBasedIndex);
        }
    }

    public Task getTask(int zeroBasedIndex) {
        return getOneBasedIndex(zeroBasedIndex + 1);
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
