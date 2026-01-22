package main.java;

import java.util.Scanner;

public class Ello {
    public static String NAME = "Ello";
    public static String LINE = "____________________________________________________________";
    public static String LOGO =
            " _______  ___      ___      _______ \n"
                    + "|       ||   |    |   |    |       |\n"
                    + "|    ___||   |    |   |    |   _   |\n"
                    + "|   |___ |   |    |   |    |  | |  |\n"
                    + "|    ___||   |___ |   |___ |  |_|  |\n"
                    + "|   |___ |       ||       ||       |\n"
                    + "|_______||_______||_______||_______|";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            echoCommand(command);
            command = scanner.nextLine();
        }

        bye();
    }

    private static void echoCommand(String command) {
        String echoCommand = Utils.wrapWithLine(command);
        System.out.println(echoCommand);
    }

    private static void greet() {
        String greetMessage = String.format("Hello! I am %s\nWhat can I do for you?", NAME);
        System.out.println(Utils.wrapWithLine(greetMessage));
    }

    private static void bye() {
        String byeMessage = String.format("Bye. Hope to see you again soon!");
        System.out.println(Utils.wrapWithLine(byeMessage));
    }

}
