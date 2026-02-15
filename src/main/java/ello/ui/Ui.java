package ello.ui;

import java.util.Scanner;

import ello.ui.util.UiConstants;

/**
 * Represents the User Interface (UI) component of the Ello application.
 * Responsible for handling user interactions, displaying messages, and reading user input.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Reads a command from the user input.
     *
     * @return The command entered by the user as a trimmed string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a message to the user, wrapped with lines for better readability.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(UiConstants.wrapWithLine(message));
    }

    /**
     * Displays a greeting message to the user.
     */
    public void showGreeting() {
        showMessage(UiConstants.WELCOME_MESSAGE);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        showMessage(UiConstants.GOODBYE_MESSAGE);
    }

    /**
     * Generates a message indicating the current count of tasks in the list.
     *
     * @param count The number of tasks.
     * @return A message indicating the task count.
     */
    public String countTasks(int count) {
        assert (count >= 0) : "Task count should not be negative.";
        if (count == 1) {
            return "Now you have 1 Task in the list.";
        }
        return "Now you have " + count + " tasks in the list.";
    }

    /**
     * Displays the result of loading tasks from storage.
     *
     * @param count The number of tasks loaded.
     */
    public void showLoadResult(int count) {
        if (count > 0) {
            System.out.println("Loaded " + count + " task(s) from storage.");
        }
        System.out.println("No existing tasks found. Starting with an empty task list.");
    }

    public void showError(String errorMessage) {
        showMessage(errorMessage);
    }
}
