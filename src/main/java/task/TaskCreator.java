package main.java.task;

import java.util.HashMap;

@FunctionalInterface
public interface TaskCreator {
    Task create(String taskDescription, HashMap<String, String> markerToDescMap);
}