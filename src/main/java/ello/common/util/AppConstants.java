package ello.common.util;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A utility class that holds constant values used throughout the application.
 */
public class AppConstants {
    private AppConstants() {
    }

    /** The application name. */
    public static final String NAME = "Ello";

    /** Default path for saving tasks to JSON. */
    public static final Path DEFAULT_SAVE_PATH = Paths.get("data", "ello.json");
}
