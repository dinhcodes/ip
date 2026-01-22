package main.java;

public class Ello {
    public static String NAME = "Ello";
    public static String LINE = "____________________________________________________________";
    public static String LOGO =
            " _______  ___      ___      _______  " + "\n"
                    + "|       ||   |    |   |    |       |" + "\n"
                    + "|    ___||   |    |   |    |   _   |" + "\n"
                    + "|   |___ |   |    |   |    |  | |  |" + "\n"
                    + "|    ___||   |___ |   |___ |  |_|  |" + "\n"
                    + "|   |___ |       ||       ||       |" + "\n"
                    + "|_______||_______||_______||_______|";

    public static void main(String[] args) {
        greet();
        bye();
    }

    public static void greet() {
        String greetMessage = String.format("%s\n Hello! I am %s\n What can I do for you?", LINE, NAME);
        System.out.println(greetMessage);
    }

    public static void bye() {
        String byeMessage = String.format("%s\n Bye. Hope to see you again soon!\n%s", LINE, LINE);
        System.out.println(byeMessage);
    }
}
