import java.util.Scanner;
import java.util.ArrayList;

public class Simon {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Simon.greeting();
        Simon.processCommands();
    }

    private static void processCommands() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                Simon.bye();
                break;
            }

            if (command.equals("")) {
                continue;
            }
            
            if (command.equals("list")) {
                listTasks();
                continue;
            }

            if (command.startsWith("mark")) {
                markTask(command);
                continue;
            }

            if (command.startsWith("unmark")) {
                unmarkTask(command);
                continue;
            }
            Simon.addTask(command);
        }
    }

    private static void unmarkTask(String command) {
        Integer taskIndex = getTaskIndex(command);
        Task taskToUnmark = tasks.get(taskIndex);
        taskToUnmark.unmark();
        formatMessage("OK, I've marked this task as not done yet:\n  " + taskToUnmark.getTaskStatusAndName());
    }

    private static void markTask(String command) {
        Integer taskIndex = getTaskIndex(command);
        Task taskToMark = tasks.get(taskIndex);
        taskToMark.mark();
        formatMessage("Nice! I've marked this task as done:\n  " + taskToMark.getTaskStatusAndName());
    }

    private static Integer getTaskIndex(String command) {
        String[] commandParts = command.split(" ");
        if (commandParts.length < 2) {
            throw new IllegalArgumentException("Task index is missing.");
        }
        
        try {
            int index = Integer.parseInt(commandParts[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new IllegalArgumentException("Task number out of range: " + (index + 1));
            }
            return index;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Invalid task number: '" + commandParts[1] + "' is not a valid number"
            );
        }
    }

    private static void formatMessage(String string) {
        String border = "____________________________________________________________";
        String margin = " ";
        System.out.println(margin + border);
        String[] lines = string.split("\n");
        for (String line : lines) {
            System.out.println(margin + line);
        }
        System.out.println(margin + border);
    }

    private static void greeting() {
        String greetingMessage = "Hello! I'm Simon\nWhat can I do for you?";
        formatMessage(greetingMessage);
    }

    private static void addTask(String taskName) {
        Task newTask = new Task(taskName);
        tasks.add(newTask);
        formatMessage("added: " + taskName);
    }

    private static void listTasks() {
        StringBuilder taskList = new StringBuilder("");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int numbering = i + 1;
            createTaskBulletPoint(taskList, numbering, task);
        }
        formatMessage(taskList.toString().trim());
    }

    private static void createTaskBulletPoint(StringBuilder taskList, int numbering, Task task) {
        taskList.append((numbering + ". " + task.getTaskStatus() + " " + task.getTaskName() + "\n"));
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                Simon.bye();
                break;
            }

            formatMessage(command);
        }
    }

    private static void bye() {
        formatMessage("Bye. Hope to see you again soon!");
    }
}
