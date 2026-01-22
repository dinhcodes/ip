package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Ello {
    private static ArrayList<Task> listOfTasks = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            processCommand(command);
            command = scanner.nextLine();
        }

        bye();
    }

    private static void processCommand(String command) {
        switch (command) {
            case "list":
                listTasks();
                break;
            default:
                processTaskCommand(command);
                break;
        }
    }
    
    private static void processTaskCommand(String command) {
        if (command.startsWith("mark ")) {
            int n = parseIndex(Utils.extractTask(command, "mark"));
            Task t = listOfTasks.get(n - 1).markDone();
            String msg = "Nice! I've marked this task as done:\n  " + t.toString();
            System.out.println(Utils.wrapWithLine(msg));
            return;
        }

        if (command.startsWith("unmark ")) {
            int n = parseIndex(Utils.extractTask(command, "unmark"));
            Task t = listOfTasks.get(n - 1).markUndone();
            String msg = "OK, I've marked this task as not done yet:\n  " + t.toString();
            System.out.println(Utils.wrapWithLine(msg));
            return;
        }

        addTask(command);
    }

    private static int parseIndex(String indexString) {
        try {
            return Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task index: " + indexString);
        }
    }

    private static void unknownCommand() {
        String unknownCommandMessage = String.format("I'm sorry, but I don't know what that means.");
        System.out.println(Utils.wrapWithLine(unknownCommandMessage));
    }

    private static void addTask(String command) {
        listOfTasks.add(new Task(command));
        String addTaskString = String.format("added: %s", command);
        System.out.println(Utils.wrapWithLine(addTaskString));
    }

    private static void listTasks() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 1; i <= listOfTasks.size(); i++) {
            String taskString = String.format("%d. %s", i, listOfTasks.get(i - 1));
            sb.append(taskString);
            if (i != listOfTasks.size()) {
                sb.append("\n");
            }
        }
        System.out.println(Utils.wrapWithLine(sb.toString()));
    }

    private Task getByIndex(int index) {
        int i = index - 1;
        if (i < 0 || i >= listOfTasks.size()) {
            throw new IndexOutOfBoundsException("Task index out of bounds");
        }
        return listOfTasks.get(i);
    }

    private static void greet() {
        String greetMessage = String.format("Hello! I am %s\nWhat can I do for you?", AppConstants.NAME);
        System.out.println(Utils.wrapWithLine(greetMessage));
    }

    private static void bye() {
        String byeMessage = String.format("Bye. Hope to see you again soon!");
        System.out.println(Utils.wrapWithLine(byeMessage));
    }
}
