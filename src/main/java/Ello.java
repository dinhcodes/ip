package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Ello {
    private static ArrayList<String> listOfTasks = new ArrayList<String>();

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
                addTask(command);
                break;
        }
    }

    private static void unknownCommand() {
        String unknownCommandMessage = String.format("I'm sorry, but I don't know what that means.");
        System.out.println(Utils.wrapWithLine(unknownCommandMessage));
    }

    private static void addTask(String command) {
        listOfTasks.add(command);
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

    private static void greet() {
        String greetMessage = String.format("Hello! I am %s\nWhat can I do for you?", AppConstants.NAME);
        System.out.println(Utils.wrapWithLine(greetMessage));
    }

    private static void bye() {
        String byeMessage = String.format("Bye. Hope to see you again soon!");
        System.out.println(Utils.wrapWithLine(byeMessage));
    }

}
