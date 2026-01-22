package main.java;

public class Utils {
    private Utils() {
    }

    public static String wrapWithLine(String message) {
        return AppConstants.LINE + "\n" + message + "\n" + AppConstants.LINE;
    }
}
