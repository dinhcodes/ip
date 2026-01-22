package main.java;

public class Utils {
    private Utils() {
    }

    public static String wrapWithLine(String message) {
        String LINE = "____________________________________________________________";
        return LINE + "\n" + message + "\n" + LINE;
    }
}
