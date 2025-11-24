import java.util.Scanner;

public class Simon {
    public static void main(String[] args) {
        Simon.greeting();
        Simon.echo();
    }

    private static void greeting() {
        String logo = " ____________________________________________________________\n" +
                " Hello! I'm Simon\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                Simon.bye();
                break;
            }

            System.out.println(command);
        }
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
