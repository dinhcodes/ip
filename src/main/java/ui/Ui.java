package main.java.ui;

import main.java.util.AppConstants;
import main.java.util.Utils;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(Utils.wrapWithLine(message));
    }

    public void showGreeting() {
        String greetingMessage = "Hello! I'm " + AppConstants.NAME + "\n" +
                "What can I do for you?";
        showMessage(greetingMessage);
    }

    public void showGoodbye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        showMessage(goodbyeMessage);
    }

    public String countTasks(int count) {
        assert(count >= 0) : "Task count should not be negative.";
        if (count == 1) {
            return "Now you have 1 task in the list.";
        }
        return "Now you have " + count + " tasks in the list.";
    }

    public void showError(String errorMessage) {
        showMessage(errorMessage);
    }
}
