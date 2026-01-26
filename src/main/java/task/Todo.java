package main.java.task;

import java.util.HashMap;

public class Todo extends Task {
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    public static Todo create(String taskDescription, HashMap<String, String> markerToDescMap) {
        return new Todo(taskDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
