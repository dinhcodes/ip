package main.java;

import java.util.Scanner;

public class Ello {
    private static final TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();

        String command = scanner.nextLine().trim();

        while (!command.equals("bye")) {
            processCommand(command);
            command = scanner.nextLine().trim();
        }

        bye();
    }

    private static void processCommand(String command) {
        switch (command) {
            case "list":
                taskList.listTasks();
                break;
            case "":
                emptyCommand();
                break;
            default:
                processTaskCommands(command);
                break;
        }
    }

    private static void unknownCommand() {
        String unknownCommandMessage = "I'm sorry, but I don't know what that means.";
        System.out.println(Utils.wrapWithLine(unknownCommandMessage));
    }

    private static void emptyCommand() {
        String emptyCommandMessage = "The command cannot be empty.";
        System.out.println(Utils.wrapWithLine(emptyCommandMessage));
    }

    private static void processTaskCommands(String command) {
        try {
            if (command.startsWith("mark")) {
                markTask(command);
                return;
            }

            if (command.startsWith("unmark")) {
                unmarkTask(command);
                return;
            }

            addTask(command);
        } catch (IllegalArgumentException e) {
            unknownCommand();
        }
    }

    private static void addTask(String command) {
        Task added = taskList.addFromCommand(command);
        String addTaskString = String.format(
                "Got it. I've added this task:\n%s\n%s",
                added,
                enumerateTasks());
        System.out.println(Utils.wrapWithLine(addTaskString));
    }

    private static void unmarkTask(String command) {
        Task t = getTaskFromTaskCommand(command, "unmark");
        t.markAsUndone();
        String msg = "OK, I've marked this task as not done yet:\n  " + t;
        System.out.println(Utils.wrapWithLine(msg));
    }

    private static void markTask(String command) {
        Task t = getTaskFromTaskCommand(command, "mark");
        t.markAsDone();
        String msg = "Nice! I've marked this task as done:\n  " + t;
        System.out.println(Utils.wrapWithLine(msg));
        return;
    }

    private static Task getTaskFromTaskCommand(String command, String commandType) {
        String indexText = Utils.extractTask(command, commandType).trim();
        int oneBased;
        try {
            oneBased = Integer.parseInt(indexText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task index.");
        }
        return taskList.getOneBasedIndex(oneBased);
    }

    private static String enumerateTasks() {
        return String.format("Now you have %d tasks in the list.", taskList.size());
    }

    private static void greet() {
        String greetMessage = String.format("Hello! I am %s\nWhat can I do for you?", AppConstants.NAME);
        System.out.println(Utils.wrapWithLine(greetMessage));
    }

    private static void bye() {
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println(Utils.wrapWithLine(byeMessage));
    }

}
