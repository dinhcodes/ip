import java.util.ArrayList;
import java.util.Scanner;

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

            if (command.equals("list")) {
                Simon.listTasks();
                continue;
            }

            Simon.addTask(command);
        }
    }

    private static void formatMessage(String string) {
        String border = "____________________________________________________________";
        String margin = " ";
        System.out.println(margin + border);
        System.out.println(margin + string);
        System.out.println(margin + border);
    }

    private static void greeting() {
        String greetingMessage = "Hello! I'm Simon\n  What can I do for you?";
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
            taskList.append((i + 1) + ". " + tasks.get(i).getTaskName() + "\n");
        }
        formatMessage(taskList.toString().trim());
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
