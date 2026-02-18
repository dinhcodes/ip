package ello;

import ello.common.util.AppConstants;
import ello.logic.Logic;
import ello.storage.TaskStorage;
import ello.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main entry point of the Ello application.
 */
public class Ello extends Application {
    /**
     * The storage component responsible for task persistence.
     */
    private TaskStorage taskStorage;

    /**
     * The UI component of the application.
     */
    private Ui ui;

    /**
     * The logic component that handles command execution.
     */
    private Logic logic;

    /**
     * Initializes the application by setting up the TaskStorage, Logic, and UI components.
     * Loads existing tasks from storage.
     */
    @Override
    public void init() {
        this.taskStorage = new TaskStorage(AppConstants.DEFAULT_SAVE_PATH);
        this.logic = new Logic(taskStorage);
        this.ui = new Ui();
    }

    @Override
    public void start(Stage stage) {
        ui.startUi(stage, logic);
    }
}
